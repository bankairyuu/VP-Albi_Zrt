package util.vaadinintegration;

import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.shared.communication.PushMode;
import play.Configuration;
import play.Play;

import java.util.Properties;

public class PlayDeploymentConfiguration implements DeploymentConfiguration {

    private final String name;
    private final String uiClassName;
    private final String uri;
    private final Configuration playConfig;

    private boolean xsrfProtectionEnabled = true;
    private boolean syncIdCheckEnabled = true;
    private int heartbeatInterval = 300;
    private boolean sendUrlsAsParameters = true;
    private boolean closeIdleSessions = false;
    private String widgetset = "com.vaadin.DefaultWidgetSet";
    private boolean standalone = true;
    private int sessionTimeout = 40000;
    private PushMode pushMode = PushMode.DISABLED;


    public PlayDeploymentConfiguration(String name, String uiClassName, String uri, Configuration playConfig) {
        this.name = name;
        this.uiClassName = uiClassName;
        this.uri = uri;
        this.playConfig = playConfig;
    }


    @Override
    public boolean isProductionMode() {
        return Play.application().isProd(); //TODO do not use Play.application()
    }

    @Override
    public boolean isXsrfProtectionEnabled() {
        return xsrfProtectionEnabled;
    }

    @Override
    public boolean isSyncIdCheckEnabled() {
        return syncIdCheckEnabled;
    }

    @Override
    public int getResourceCacheTime() {
        return -1; //unused
    }

    @Override
    public int getHeartbeatInterval() {
        return heartbeatInterval;
    }

    @Override
    public boolean isSendUrlsAsParameters() {
        return sendUrlsAsParameters;
    }

    @Override
    public boolean isCloseIdleSessions() {
        return closeIdleSessions;
    }

    @Override
    public PushMode getPushMode() {
        return pushMode;
    }

    @Override
    public Properties getInitParameters() {
        return new Properties();
    }

    @Override
    public String getApplicationOrSystemProperty(String propertyName, String defaultValue) {
        if( "UI".equals(propertyName) ) {
            return uiClassName;
        } else {
            return playConfig.getString(propertyName, defaultValue);
        }
    }

    @Override
    public String getUIClassName() {
        return uiClassName;
    }

    @Override
    public String getUIProviderClassName() {
        return null;
    }

    @Override
    public String getWidgetset(String defaultValue) {
        return defaultValue;
    }

    @Override
    public String getResourcesPath() {
        return null;
    }

    @Override
    public String getClassLoaderName() {
        return null;
    }

    public String getName() {
        return name;
    }

    public String getStaticFileLocation() {
        return ""; //TODO
    }

    public String getWidgetset() {
        return widgetset;
    }

    public boolean isStandalone() {
        return standalone;
    }
    
    public String getPath() {
        return "/"+uri;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    /*
     * Setters
     */

    public void setXsrfProtectionEnabled(boolean xsrfProtectionEnabled) {
        this.xsrfProtectionEnabled = xsrfProtectionEnabled;
    }

    public void setSyncIdCheckEnabled(boolean syncIdCheckEnabled) {
        this.syncIdCheckEnabled = syncIdCheckEnabled;
    }

    public void setHeartbeatInterval(int heartbeatInterval) {
        this.heartbeatInterval = heartbeatInterval;
    }

    public void setSendUrlsAsParameters(boolean sendUrlsAsParameters) {
        this.sendUrlsAsParameters = sendUrlsAsParameters;
    }

    public void setCloseIdleSessions(boolean closeIdleSessions) {
        this.closeIdleSessions = closeIdleSessions;
    }

    public void setWidgetset(String widgetset) {
        this.widgetset = widgetset;
    }

    public void setStandalone(boolean standalone) {
        this.standalone = standalone;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public void setPushMode(PushMode pushMode) {
        this.pushMode = pushMode;
    }
}
