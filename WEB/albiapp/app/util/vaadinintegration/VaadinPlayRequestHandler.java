package util.vaadinintegration;

import com.vaadin.server.ServiceException;
import com.vaadin.util.CurrentInstance;
import play.cache.SyncCacheApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.servlet.http.Cookie;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.UUID;


public class VaadinPlayRequestHandler {

    public static final String SESSION_KEY = "vaadinPlaySession";


    private final PlayDeploymentConfiguration deploymentConfiguration;
    private final SyncCacheApi syncCacheApi; //TODO try AsyncCacheApi

    protected final VaadinPlayService vaadinPlayService;

    public VaadinPlayRequestHandler(PlayDeploymentConfiguration deploymentConfiguration, SyncCacheApi syncCacheApi) {
        this.deploymentConfiguration = deploymentConfiguration;
        this.syncCacheApi = syncCacheApi;
        CurrentInstance.clearAll();

        vaadinPlayService = createVaadinPlayService(deploymentConfiguration);
        try {
            vaadinPlayService.init();
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        vaadinPlayService.setCurrentInstances(null, null);

        playPluginInitialized();

        CurrentInstance.clearAll();
    }

    protected void playPluginInitialized() {
    }

    public Result handleRequest(Http.Context ctx) {
        String confPathWithSlash = deploymentConfiguration.getPath()+"/";
        if( !ctx.request().path().startsWith(confPathWithSlash) ) {
            return Controller.redirect(confPathWithSlash); //TODO + request.queryString()
        } else {

            String sessionId = ctx.session().get(SESSION_KEY);
            if( sessionId == null ) {
                sessionId = UUID.randomUUID().toString();
                ctx.session().put(SESSION_KEY, sessionId);
            }
            VaadinPlaySession vaadinPlaySession = retrieveVaadinPlaySession(sessionId);
            vaadinPlaySession.onAfterRetrieve();

            CurrentInstance.clearAll();

            VaadinPlayRequest vaadinPlayRequest = createVaadinPlayRequest(ctx.request(), vaadinPlaySession);
            VaadinPlayResponse vaadinPlayResponse = createVaadinPlayResponse();

            //TODO ensureCookiesEnabled
            try {
                vaadinPlayService.handleRequest(vaadinPlayRequest, vaadinPlayResponse);
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            } finally {
                vaadinPlayResponse.cleanUp();
                vaadinPlaySession.onBeforeStore();
                storeVaadinPlaySession(sessionId, vaadinPlaySession, deploymentConfiguration.getSessionTimeout());
            }

            for (Map.Entry<String, String> header : vaadinPlayResponse.getHeadersMap().entrySet()) {
                if( !Http.HeaderNames.CONTENT_TYPE.equals(header.getKey()) ) { //content-type header is ignored
                    ctx.response().setHeader(header.getKey(), header.getValue());
                }
            }

            for (Cookie cookie : vaadinPlayResponse.getCookiesMap().values()) {
                ctx.response().setCookie(
                        Http.Cookie.builder(cookie.getName(), cookie.getValue())
                            .withDomain(cookie.getDomain())
                            .withMaxAge(Duration.of(cookie.getMaxAge(), ChronoUnit.SECONDS))
                            .withPath(cookie.getPath())
                            .withSecure(cookie.getSecure())
                            .build()
                );
            }

            return Results
                    .status(vaadinPlayResponse.getStatusCode(), vaadinPlayResponse.getBytes())
                    .as(vaadinPlayResponse.getContentType());
        }
    }

    protected VaadinPlayResponse createVaadinPlayResponse() {
        return new VaadinPlayResponse(vaadinPlayService);
    }

    protected VaadinPlayRequest createVaadinPlayRequest(Http.Request request, VaadinPlaySession vaadinPlaySession) {
        return new VaadinPlayRequest(request, vaadinPlayService, deploymentConfiguration.getPath(), vaadinPlaySession);
    }


    protected VaadinPlayService createVaadinPlayService(PlayDeploymentConfiguration deploymentConfiguration) {
        return new VaadinPlayService(deploymentConfiguration);
    }

    protected VaadinPlaySession retrieveVaadinPlaySession(String sessionId) {
        VaadinPlaySession vaadinPlaySession = (VaadinPlaySession) syncCacheApi.get(sessionId);
        if( vaadinPlaySession == null ) {
            vaadinPlaySession = new DefaultVaadinPlaySession(sessionId);
        }
        return vaadinPlaySession;
    }

    protected void storeVaadinPlaySession(String sessionId, VaadinPlaySession vaadinPlaySession, int sessionTimeout) {
       syncCacheApi.set(sessionId, vaadinPlaySession, sessionTimeout);
    }

    public VaadinPlayService getVaadinPlayService() {
        return vaadinPlayService;
    }
}
