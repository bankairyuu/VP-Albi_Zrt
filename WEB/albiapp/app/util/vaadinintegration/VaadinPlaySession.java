package util.vaadinintegration;

import java.util.Set;


public interface VaadinPlaySession {

    String getSessionId();

    Long getCreationTime();

    Long getLastAccessedTime();

    Integer getMaxInactiveInterval();

    void setMaxInactiveInterval(Integer value);

    boolean isNew();

    Object getAttribute(String name);

    void setAttribute(String name, Object value);

    void removeAttribute(String name);

    Set<String> getAttributeNames();

    default void onAfterRetrieve() {}

    default void onBeforeStore() {}

}
