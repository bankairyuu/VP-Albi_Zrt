package controllers.api;

import backend.models.FlatUser;
import backend.repositories.UserRepository;
import controllers.api.models.ApiUser;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserApi extends Controller {

    @Inject
    private UserRepository userRepository;

    ////@Secured
    /*@ApiOperation(value = "List rooms", httpMethod = "GET" , response = List.class,
            consumes = "application/json", produces = "application/json")*/

    @Transactional
    public Result apiUsers() {
        return ok(Json.toJson(userRepository.findAll().stream().map(ApiUser::new).collect(Collectors.toList())));
    }

/*    @Path("/Create/")
    @POST
    @Consumes("application/json")
    //@Secured
    @ApiOperation(value = "Create user", httpMethod = "POST" ,
            consumes = "application/json")
    public void createUser(ApiUser apiUser) {
        FlatUser flatUser = new FlatUser(apiUser.getName(), apiUser.getBankAccountNumber(), apiUser.getPhoneNumber(),
                apiUser.getEmail(), apiUser.getNickname());

        userRepository.save(flatUser);
    }

    @Path("/Update/")
    @POST
    @Consumes("application/json")
    //@Secured
    @ApiOperation(value = "Update user", httpMethod = "PUT" ,
            consumes = "application/json")
    public void updateUser(ApiUser apiUser) {
        FlatUser flatUser = userRepository.findById(apiUser.getId());
        flatUser.bankAccountNumber = apiUser.getBankAccountNumber();
        flatUser.email = apiUser.getEmail();
        flatUser.name = apiUser.getName();
        flatUser.nickname = apiUser.getNickname();
        flatUser.phoneNumber = apiUser.getPhoneNumber();

        userRepository.save(flatUser);
    }

    @Path("/Delete/{id}")
    @POST
    @Consumes("application/json")
    //@Secured
    @ApiOperation(value = "Delete user", httpMethod = "PUT" ,
            consumes = "application/json")
    public void deleteUser(@PathParam("id") long id) {
        FlatUser flatUser = userRepository.findById(id);
        userRepository.delete(flatUser);
    }*/
}
