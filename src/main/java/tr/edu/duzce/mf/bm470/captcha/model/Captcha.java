package tr.edu.duzce.mf.bm470.captcha.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "captcha")
public class Captcha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @OneToMany(mappedBy = "captcha",fetch = FetchType.EAGER)
    private Set<ImageWrapper> imageWrappers = new HashSet<>();


}
