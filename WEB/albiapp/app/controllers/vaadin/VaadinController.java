package controllers.vaadin;

import play.Configuration;
import play.cache.SyncCacheApi;
import play.db.jpa.Transactional;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import util.vaadinintegration.PlayDeploymentConfiguration;
import util.vaadinintegration.VaadinBodyParser;
import util.vaadinintegration.VaadinPlayRequestHandler;
import vaadin.MainUI;

import javax.inject.Inject;

@Transactional
public class VaadinController extends Controller {

    @Inject
    SyncCacheApi syncCacheApi;

    @Inject
    Configuration config;

    @Inject
    controllers.Assets assets;

    @BodyParser.Of(VaadinBodyParser.class)
    public Result vaadinUI(Object args) {
        PlayDeploymentConfiguration deploymentConfiguration = getAdminUIDeploymentConfig();
        VaadinPlayRequestHandler vaadinPlayRequestHandler;


        vaadinPlayRequestHandler = new VaadinPlayRequestHandler(deploymentConfiguration, syncCacheApi);

        return vaadinPlayRequestHandler.handleRequest(ctx());
    }

    private PlayDeploymentConfiguration getAdminUIDeploymentConfig() {
        PlayDeploymentConfiguration deploymentConfiguration = new PlayDeploymentConfiguration("vaadin", MainUI.class.getCanonicalName(), "vaadin", config );

        return deploymentConfiguration;
    }

    public play.api.mvc.Action<play.api.mvc.AnyContent> assets(controllers.Assets.Asset file) {
        return assets.versioned("/VAADIN", file);
    }

}
