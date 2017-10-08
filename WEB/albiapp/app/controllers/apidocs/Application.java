package controllers.apidocs;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.swagger.apidocs;

import static play.mvc.Results.ok;


public class Application extends Controller {
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(apidocs.render(request().host()));
    }
}
