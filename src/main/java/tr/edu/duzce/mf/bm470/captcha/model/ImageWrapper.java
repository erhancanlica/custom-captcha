package tr.edu.duzce.mf.bm470.captcha.model;

import lombok.*;
import org.apache.commons.codec.binary.Base64;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;

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

    @Lob
    @Column(name = "data")
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "captchaId", nullable = false)
    private Captcha captcha;
}
