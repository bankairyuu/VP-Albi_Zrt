package controllers.api;

import backend.models.FlatUser;
import backend.repositories.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import controllers.api.models.ApiUser;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class UserApi extends Controller {

    @Inject
    private UserRepository userRepository;

    @Inject
    private FormFactory formFactory;

    public Result list() {
        return ok(Json.toJson(userRepository.findAll().stream().map(ApiUser::new).collect(Collectors.toList())));
    }

    //@Secured
    public Result create() {

        ApiUser apiUser = Json.fromJson(request().body().asJson(), ApiUser.class);

        FlatUser flatUser = new FlatUser(apiUser.UserName, apiUser.Name, apiUser.Password, apiUser.BankAccountNumber, apiUser.PhoneNumber,
                apiUser.Email, apiUser.Nickname);

        userRepository.save(flatUser);

        return ok();
    }

    //@Secured
    public Result update() {
        ApiUser apiUser = Json.fromJson(request().body().asJson(), ApiUser.class);

        FlatUser flatUser = new FlatUser(apiUser.UserName, apiUser.Name, apiUser.Password, apiUser.BankAccountNumber, apiUser.PhoneNumber,
                apiUser.Email, apiUser.Nickname);

        flatUser.id = apiUser.ID;

        userRepository.save(flatUser);

        return ok();
    }

    //@Secured
    public Result delete(long userId) {
        FlatUser flatUser = userRepository.findById(userId);
        userRepository.delete(flatUser);

        return ok();
    }
}
