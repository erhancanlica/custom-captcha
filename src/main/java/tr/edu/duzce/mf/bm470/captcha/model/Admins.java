package tr.edu.duzce.mf.bm470.captcha.model;

import lombok.*;
import javax.persistence.*;

@Table(name = "admin")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID", nullable = false)
    private Users user;

    private String adminName;
}
