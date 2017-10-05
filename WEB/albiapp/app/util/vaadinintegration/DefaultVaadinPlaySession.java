package util.vaadinintegration;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;


public class DefaultVaadinPlaySession implements VaadinPlaySession {

    private final String sessionId;
    private final Map<String, Object> attributeMap = Maps.newHashMap();

    private boolean newSession = true;
    private Integer maxInactiveInterval = 1800;

    public DefaultVaadinPlaySession(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public Long getCreationTime() {
        return System.currentTimeMillis();
    }

    @Override
    public Long getLastAccessedTime() {
        return 0L; //TODO
    }

    @Override
    public Integer getMaxInactiveInterval() {
        return this.maxInactiveInterval;
    }

    @Override
    public void setMaxInactiveInterval(Integer value) {
        this.maxInactiveInterval = value;
    }

    @Override
    public boolean isNew() {
        return newSession;
    }

    @Override
    public Object getAttribute(String name) {
        return attributeMap.get(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributeMap.put(name, value);
    }

    @Override
    public void removeAttribute(String name) {
        attributeMap.remove(name);
    }


    @Override
    public Set<String> getAttributeNames() {
        return attributeMap.keySet();
    }

    @Override
    public void onBeforeStore() {
        newSession = false;
    }
}
