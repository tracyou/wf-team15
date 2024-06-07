package app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"USER\"")
public class User {
    @Id
    public
    long id;
    public String name;
    public String eMail;
    private String hashedPassWord;
    public String role;

    public User(long id, String name, String eMail, String hashedPassWord, String role) {
        this.id = id;
        this.name = name;
        this.eMail = eMail;
        this.hashedPassWord = hashedPassWord;
        this.role = role;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eMail='" + eMail + '\'' +
                ", hashedPassWord='" + hashedPassWord + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

