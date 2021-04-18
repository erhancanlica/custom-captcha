package tr.edu.duzce.mf.bm470.captcha.model;

import lombok.*;
import org.apache.commons.codec.binary.Base64;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;

@Table(name = "imageWrapper")
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageWrapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private String name;

    @Lob
    private byte[] data;

    @Transient
    private String base;

    public String getImgUtility() throws UnsupportedEncodingException {

        byte[] encodeBase64 = Base64.encodeBase64(getData());
        String base64Encoded = new String(encodeBase64, "UTF-8");
        return base64Encoded;
    }
}
