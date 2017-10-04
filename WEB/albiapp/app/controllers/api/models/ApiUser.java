package controllers.api.models;


import backend.models.FlatUser;

public class ApiUser {
    private long id;
    private String name;
    private String bankAccountNumber;
    private String phoneNumber;
    private String email;
    private String nickname;

    public ApiUser(FlatUser flatUser) {
        this.id = flatUser.id;
        this.name = flatUser.name;
        this.bankAccountNumber = flatUser.bankAccountNumber;
        this.phoneNumber = flatUser.phoneNumber;
        this.email = flatUser.email;
        this.nickname = flatUser.nickname;
    }

    public ApiUser() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
