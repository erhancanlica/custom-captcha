package tr.edu.duzce.mf.bm470.captcha.model;

import lombok.*;
import javax.persistence.*;

@Table(name = "imageWrapper")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageWrapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @Column(name = "name")
    private String name;

    @Column(name = "isValid")
    private boolean isValid;

    @Lob
    @Column(name = "data")
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "captchaId", nullable = false)
    private Captcha captcha;
}
