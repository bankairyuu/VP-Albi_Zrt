package controllers.api.models;


import backend.models.FlatUser;

public class ApiUser {
    public long ID;
    public String Name;
    public String UserName;
    public String Password;
    public String BankAccountNumber;
    public String PhoneNumber;
    public String Email;
    public String Nickname;

    public ApiUser(FlatUser flatUser) {
        this.ID = flatUser.id;
        this.UserName = flatUser.userName;
        this.Name = flatUser.name;
        this.Password = flatUser.password;
        this.BankAccountNumber = flatUser.bankAccountNumber;
        this.PhoneNumber = flatUser.phoneNumber;
        this.Email = flatUser.email;
        this.Nickname = flatUser.nickname;
    }

    public ApiUser() {

    }
}
