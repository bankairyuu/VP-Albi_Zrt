package util.vaadinintegration;

import akka.util.ByteString;
import play.api.http.HttpConfiguration;
import play.api.mvc.PlayBodyParsers;
import play.http.HttpErrorHandler;
import play.libs.F;
import play.libs.streams.Accumulator;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Locale;

public class VaadinBodyParser implements BodyParser<Object> {
    private final HttpErrorHandler errorHandler;
    private final HttpConfiguration httpConfiguration;
    private final PlayBodyParsers bodyParsers;

    @Inject
    public VaadinBodyParser(HttpErrorHandler errorHandler, HttpConfiguration httpConfiguration, PlayBodyParsers bodyParsers) {
        this.errorHandler = errorHandler;
        this.httpConfiguration = httpConfiguration;
        this.bodyParsers = bodyParsers;
    }

    @Override
    public Accumulator<ByteString, F.Either<Result, Object>> apply(Http.RequestHeader request) {
        String contentType = request.contentType().map(ct -> ct.toLowerCase(Locale.ENGLISH)).orElse(null);
        BodyParser parser;
        if (contentType == null) {
            parser = new Raw(bodyParsers);
        } else if (contentType.equals("text/plain")) {
            parser = new TolerantText(httpConfiguration, errorHandler);
        } else if (contentType.equals("application/x-www-form-urlencoded")) {
            parser = new FormUrlEncoded(httpConfiguration, errorHandler);
        } else {
            parser = new Raw(bodyParsers);
        }
        return parser.apply(request);
    }
}