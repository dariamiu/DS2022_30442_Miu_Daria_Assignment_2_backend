package ds.entity_utility_platform.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name ="user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_user;

    @NotEmpty(message = "The username field is empty")
    @Column
    private String username;


    @NotEmpty(message = "The password field is empty")
    @Column
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}