package ds.entity_utility_platform.entity;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin extends User{

    public Admin(String username, String password) {
        super(username, password);
    }
    public Admin() {

    }
}