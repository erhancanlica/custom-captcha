package tr.edu.duzce.mf.bm470.captcha.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotNull(message = "ad kısmı boş bırakılamaz")
    @Size(min = 2, max = 20, message = "2-20 harf arasında olmalıdır")
    private String name;

    @Column(name = "status")
    private boolean status;

    @Column(name = "category")
    @NotNull(message = "kategori kısmı boş bırakılamaz")
    private String category;

    @OneToMany(mappedBy = "captcha",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ImageWrapper> imageWrappers = new HashSet<>();
}
