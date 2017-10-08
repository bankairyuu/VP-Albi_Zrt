package controllers.api;

import backend.models.FlatUser;
import backend.repositories.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import controllers.api.models.ApiExpense;
import controllers.api.models.ApiUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "/users", description = "Users", consumes="application/json, application/xml")
public class UserApi extends Controller {

    @Inject
    private UserRepository userRepository;

    @ApiOperation(value = "List users", response = ApiExpense.class, responseContainer = "List")
    public Result list() {
        return ok(Json.toJson(userRepository.findAll().stream().map(ApiUser::new).collect(Collectors.toList())));
    }

    @ApiOperation(value = "Create user")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", allowMultiple = true, dataType = "controllers.api.models.ApiUser", value = "Api expense", required = true)
    })
    //@Secured
    public Result create() {

        ApiUser apiUser = Json.fromJson(request().body().asJson(), ApiUser.class);

        FlatUser flatUser = new FlatUser(apiUser.UserName, apiUser.Name, apiUser.Password, apiUser.BankAccountNumber, apiUser.PhoneNumber,
                apiUser.Email, apiUser.Nickname);

        userRepository.save(flatUser);

        return ok();
    }

    @ApiOperation(value = "Update user")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", allowMultiple = true, dataType = "controllers.api.models.ApiUser", value = "Api expense", required = true)
    })
    //@Secured
    public Result update() {
        ApiUser apiUser = Json.fromJson(request().body().asJson(), ApiUser.class);

        FlatUser flatUser = new FlatUser(apiUser.UserName, apiUser.Name, apiUser.Password, apiUser.BankAccountNumber, apiUser.PhoneNumber,
                apiUser.Email, apiUser.Nickname);

        flatUser.id = apiUser.ID;

        userRepository.save(flatUser);

        return ok();
    }

    @ApiOperation(value = "Delete user")
    //@Secured
    public Result delete(long userId) {
        FlatUser flatUser = userRepository.findById(userId);
        userRepository.delete(flatUser);

        return ok();
    }
}
