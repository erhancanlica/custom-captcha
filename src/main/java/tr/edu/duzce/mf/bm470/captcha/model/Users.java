package tr.edu.duzce.mf.bm470.captcha.model;

import tr.edu.duzce.mf.bm470.captcha.model.enums.UserType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_type", nullable = false)
    private UserType userType;
}
