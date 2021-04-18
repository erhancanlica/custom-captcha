package tr.edu.duzce.mf.bm470.captcha.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaptchaDto {

    private long id;
    private String name;
    private String kategori;
    private MultipartFile[] images;


}
