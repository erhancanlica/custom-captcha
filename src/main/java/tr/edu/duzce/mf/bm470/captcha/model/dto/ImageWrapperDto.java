package tr.edu.duzce.mf.bm470.captcha.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.edu.duzce.mf.bm470.captcha.model.Captcha;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageWrapperDto {
    private long id;
    private String name;
    private String base;
    private boolean valid;
    private long captchaId;


}
