package util.vaadinintegration;

import com.vaadin.server.*;
import com.vaadin.server.communication.ServletBootstrapHandler;
import com.vaadin.server.communication.ServletUIInitHandler;
import com.vaadin.ui.UI;
import play.Play;

import java.io.File;
import java.io.InputStream;
import java.util.List;


public class VaadinPlayService extends VaadinService {

    private final PlayDeploymentConfiguration deploymentConfiguration;

    /**
     * Creates a new vaadin service based on a deployment configuration
     *
     * @param deploymentConfiguration the deployment configuration for the service
     */
    public VaadinPlayService(PlayDeploymentConfiguration deploymentConfiguration) {
        super(deploymentConfiguration);
        this.deploymentConfiguration = deploymentConfiguration;
        if( getClassLoader() == null ) { //TODO do not use Play.application()
            setClassLoader(Play.application().classloader());
        }
    }

    @Override
    public String getStaticFileLocation(VaadinRequest request) {
        return deploymentConfiguration.getStaticFileLocation();
    }

    @Override
    public String getConfiguredWidgetset(VaadinRequest request) {
        return deploymentConfiguration.getWidgetset();
    }

    @Override
    public String getConfiguredTheme(VaadinRequest request) {
        return Constants.DEFAULT_THEME_NAME;
    }

    @Override
    public boolean isStandalone(VaadinRequest request) {
        return deploymentConfiguration.isStandalone();
    }

    @Override
    public String getMimeType(String resourceName) {
        return null; //TODO
    }

    @Override
    public File getBaseDirectory() {
        return null;//TODO
    }

    @Override
    protected boolean requestCanCreateSession(VaadinRequest request) {
        return ServletUIInitHandler.isUIInitRequest(request) || isOtherRequest(request);
    }

    private boolean isOtherRequest(VaadinRequest request) {
        return !ServletPortletHelper.isAppRequest(request) &&
                !ServletUIInitHandler.isUIInitRequest(request) &&
                !ServletPortletHelper.isFileUploadRequest(request) &&
                !ServletPortletHelper.isHeartbeatRequest(request) &&
                !ServletPortletHelper.isPublishedFileRequest(request) &&
                !ServletPortletHelper.isUIDLRequest(request) &&
                !ServletPortletHelper.isPushRequest(request);
    }

    @Override
    public String getServiceName() {
        return deploymentConfiguration.getName();
    }



    @Override
    public InputStream getThemeResourceAsStream(UI uI, String themeName, String resource) {
        return null; //TODO
    }

    @Override
    public String getMainDivId(VaadinSession session, VaadinRequest request, Class<? extends UI> uiClass) {
        String path = deploymentConfiguration.getPath().substring(1);
        String appId;
        if( "".equals(path)  ) {
            appId = "ROOT";
        } else {
            appId = path;
        }

        int hashCode = appId.replaceAll("[^a-zA-Z0-9]", "").hashCode();

        return appId + "-" + Math.abs(hashCode);
    }

    @Override
    protected List<RequestHandler> createRequestHandlers() throws ServiceException {
        List<RequestHandler> requestHandlers = super.createRequestHandlers();

        requestHandlers.add(0, new ServletBootstrapHandler() {
            @Override
            protected String getContextRootPath(BootstrapContext context) {
                return context.getRequest().getContextPath() + "/"; //TODO
            }
        });
        requestHandlers.add(new ServletUIInitHandler());

        return requestHandlers;
    }

    @Override
    public boolean ensurePushAvailable() {
        //PlayPushConnection doesn't have any dependencies so it's always available
        return true;
    }
}
