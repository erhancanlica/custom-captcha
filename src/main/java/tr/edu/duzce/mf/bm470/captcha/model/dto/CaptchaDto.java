package tr.edu.duzce.mf.bm470.captcha.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaptchaDto implements Serializable {
    private long captchaId;
    private String captchaName;
    private String captchaCategory;
    private boolean status;
    private List<ImageWrapperDto> imageWrapper;

}
