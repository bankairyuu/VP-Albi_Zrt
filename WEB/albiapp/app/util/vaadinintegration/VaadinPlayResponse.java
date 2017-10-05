package util.vaadinintegration;

import com.google.common.collect.Maps;
import com.vaadin.server.VaadinResponse;
import com.vaadin.server.VaadinService;
import play.mvc.Http;

import javax.servlet.http.Cookie;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;


public class VaadinPlayResponse implements VaadinResponse {

    //copy-pasted from vaadinPortletResponse
    static final DateFormat HTTP_DATE_FORMAT = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
    static {
        HTTP_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
    }


    private final VaadinPlayService vaadinPlayService;

    private int statusCode = Http.Status.OK;
    private final Map<String, String> headersMap = Maps.newHashMap();
    private final Map<String, Cookie> cookiesMap = Maps.newHashMap();
    private final OutputStream outputStream = new ByteArrayOutputStream();
    private final PrintWriter printWriter = new PrintWriter(outputStream);

    public VaadinPlayResponse(VaadinPlayService vaadinPlayService) {
        this.vaadinPlayService = vaadinPlayService;
    }


    public void cleanUp() {
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        printWriter.close();
    }

    @Override
    public void setStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setContentType(String contentType) {
        setHeader(Http.HeaderNames.CONTENT_TYPE, contentType);
    }

    @Override
    public void setHeader(String name, String value) {
        headersMap.put(name, value);
    }

    @Override
    public void setDateHeader(String name, long timestamp) {
        //copy-pasted from vaadinPortletResponse
        setHeader(name, HTTP_DATE_FORMAT.format(new Date(timestamp)));
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return outputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return printWriter;
    }

    @Override
    public void setCacheTime(long milliseconds) {
        // copy-pasted from package protected VaadinServletResponse.doSetCacheTime(this, milliseconds)
        if (milliseconds <= 0) {
            setHeader("Cache-Control", "no-cache");
            setHeader("Pragma", "no-cache");
            setDateHeader("Expires", 0);
        } else {
            setHeader("Cache-Control", "max-age=" + milliseconds
                    / 1000);
            setDateHeader("Expires", System.currentTimeMillis()
                    + milliseconds);
            // Required to apply caching in some Tomcats
            setHeader("Pragma", "cache");
        }
    }

    @Override
    public void sendError(int errorCode, String message) throws IOException {
        setStatus(errorCode);
        getWriter().write(message);
    }

    @Override
    public VaadinService getService() {
        return vaadinPlayService;
    }

    @Override
    public void addCookie(Cookie cookie) {
        cookiesMap.put(cookie.getName(), cookie);
    }

    @Override
    public void setContentLength(int len) {
        //TODO
    }


    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getHeadersMap() {
        return headersMap;
    }

    public Map<String, Cookie> getCookiesMap() {
        return cookiesMap;
    }

    public byte[] getBytes() {
        return ((ByteArrayOutputStream)outputStream).toByteArray();
    }

    public String getContentType() {
        return headersMap.getOrDefault(Http.HeaderNames.CONTENT_TYPE, "text/html");
    }
}
