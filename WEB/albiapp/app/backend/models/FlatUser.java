package backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FlatUser {
    @Id
    @GeneratedValue
    public Long id;

    public String userName;
    public String bankAccountNumber;
    public String phoneNumber;
    public String email;
    public String nickname;
    public String password;

    public FlatUser(String userName, String password, String bankAccountNumber, String phoneNumber, String email, String nickname) {
        this.userName = userName;
        this.password = password;
        this.bankAccountNumber = bankAccountNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nickname = nickname;
    }

    public FlatUser() {
    }
}