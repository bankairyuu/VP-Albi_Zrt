package util.vaadinintegration;

import akka.util.ByteString;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WrappedSession;
import play.mvc.Http;

import javax.servlet.http.Cookie;
import java.io.*;
import java.security.Principal;
import java.util.*;


public class VaadinPlayRequest implements VaadinRequest {

    private final Http.Request request;
    private final VaadinPlayService vaadinService;
    private final String uri;
    private final VaadinPlaySession session;

    public VaadinPlayRequest(Http.Request request, VaadinPlayService vaadinService, String uri, VaadinPlaySession session) {
        this.request = request;
        this.vaadinService = vaadinService;
        this.uri = uri;
        this.session = session;
    }


    @Override
    public String getParameter(String parameter) {
        Map<String, String[]> parameterMap = getParameterMap();

        if( parameterMap.containsKey(parameter) ) {
            String[] valueArray = parameterMap.get(parameter);
            if( valueArray.length > 0 ) {
                return valueArray[0];
            }
        }

        return null;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> getParameters = request.queryString();
        Map<String, String[]> bodyParameters;
        if( request.body() != null ) {
            bodyParameters = request.body().asFormUrlEncoded();
        } else {
            bodyParameters = null;
        }

        Map<String, String[]> result = Maps.newHashMap();

        for (Map.Entry<String, String[]> entry : getParameters.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }

        if( bodyParameters != null ) {
            for (Map.Entry<String, String[]> entry : bodyParameters.entrySet()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    @Override
    public int getContentLength() {
        return Integer.parseInt(getHeader(Http.HeaderNames.CONTENT_LENGTH));
    }

    private InputStream inputStream = null;

    @Override
    public InputStream getInputStream() throws IOException {
        if( inputStream == null ) {
            String bodyText = request.body().asText();
            if (bodyText != null) {
                inputStream = new ByteArrayInputStream(bodyText.getBytes());
            } else {
                //TODO: transforming json to string and string to byteArrayInputStream isn't the right solution. A custom BodyParser will be better.
                JsonNode jsonNode = request.body().asJson();
                if( jsonNode != null ) {
                    inputStream = new ByteArrayInputStream(jsonNode.toString().getBytes());
                } else {
                    Http.RawBuffer rawBuffer = request.body().asRaw();
                    if( rawBuffer != null ) {
                        ByteString bytes = rawBuffer.asBytes();
                        if (bytes != null) {
                            inputStream = new ByteArrayInputStream(bytes.toArray());
                        } else {
                            File file = rawBuffer.asFile();
                            if (file != null) {
                                inputStream = new FileInputStream(file);
                            } else {
                                throw new RuntimeException("VaadinPlayRequest: failed to get InputStream from raw request");
                            }
                        }
                    } else {
                        throw new RuntimeException("VaadinPlayRequest: failed to get InputStream from request: unknown request body type");
                    }
                }
            }


        }
        return inputStream;
    }

    @Override
    public Object getAttribute(String name) {
        return session.getAttribute(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        session.setAttribute(name, value);
    }

    @Override
    public String getPathInfo() {
        return request.path().substring(getContextPath().length());
    }

    @Override
    public String getContextPath() {
        return uri;
    }

    @Override
    public WrappedSession getWrappedSession() {
        return getWrappedSession(true);
    }

    @Override
    public WrappedSession getWrappedSession(boolean allowSessionCreation) {
        if( !session.isNew() || allowSessionCreation ) {
            return new WrappedPlaySession(session);
        } else {
            return null;
        }
    }

    @Override
    public String getContentType() {
        return getHeader(Http.HeaderNames.CONTENT_TYPE);
    }

    @Override
    public Locale getLocale() {
        return null; //TODO request.headers.get(HeaderNames.ACCEPT_LANGUAGE).orNull
    }

    @Override
    public String getRemoteAddr() {
        return request.remoteAddress();
    }

    @Override
    public boolean isSecure() {
        return request.secure();
    }

    @Override
    public String getHeader(String headerName) {
        return request.getHeader(headerName);
    }

    @Override
    public VaadinService getService() {
        return vaadinService;
    }

    @Override
    public Cookie[] getCookies() {
        List<Cookie> result = Lists.newArrayList();
        request.cookies().forEach( cookie -> {
            Cookie resultCookie = new Cookie(cookie.name(), cookie.value());
            resultCookie.setDomain(cookie.domain());
            resultCookie.setMaxAge(cookie.maxAge());
            resultCookie.setPath(cookie.path());
            resultCookie.setSecure(cookie.secure());
        });
        return result.toArray(new Cookie[result.size()]);
    }

    @Override
    public String getAuthType() {
        return "";
    }

    @Override
    public String getRemoteUser() {
        return "";
    }

    @Override
    public Principal getUserPrincipal() {
        return null;
    }

    @Override
    public boolean isUserInRole(String role) {
        return false;
    }

    @Override
    public void removeAttribute(String name) {
        session.removeAttribute(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return new Vector<>(session.getAttributeNames()).elements();
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return null; //TODO
    }

    @Override
    public String getRemoteHost() {
        return request.host().split(":")[0];
    }

    @Override
    public int getRemotePort() {
        String[] hostParts = request.host().split(":");
        return hostParts.length > 1 ? Integer.parseInt(hostParts[1]) : -1;
    }

    @Override
    public String getCharacterEncoding() {
        return ""; //TODO
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public String getMethod() {
        return request.method();
    }

    @Override
    public long getDateHeader(String name) {
        return 0; //TODO
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return new Vector<>(request.headers().keySet()).elements();
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        String[] resultArray = request.headers().get(name);
        if( resultArray == null ) {
            return null;
        } else {
            return new Vector<String>(Lists.newArrayList(resultArray)).elements();
        }
    }
}
