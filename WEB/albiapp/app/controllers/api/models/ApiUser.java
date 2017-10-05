package controllers.api.models;


import backend.models.FlatUser;

public class ApiUser {
    public long id;
    public String userName;
    public String password;
    public String bankAccountNumber;
    public String phoneNumber;
    public String email;
    public String nickname;

    public ApiUser(FlatUser flatUser) {
        this.id = flatUser.id;
        this.userName = flatUser.userName;
        this.password = flatUser.password;
        this.bankAccountNumber = flatUser.bankAccountNumber;
        this.phoneNumber = flatUser.phoneNumber;
        this.email = flatUser.email;
        this.nickname = flatUser.nickname;
    }

    public ApiUser() {

    }
}
