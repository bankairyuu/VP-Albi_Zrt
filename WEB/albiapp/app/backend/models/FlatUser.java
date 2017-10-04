package backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FlatUser {
    @Id
    @GeneratedValue
    public Long id;

    public String name;
    public String bankAccountNumber;
    public String phoneNumber;
    public String email;
    public String nickname;

    public FlatUser(String name, String bankAccountNumber, String phoneNumber, String email, String nickname) {
        this.name = name;
        this.bankAccountNumber = bankAccountNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nickname = nickname;
    }

    public FlatUser() {
    }
}