package tr.edu.duzce.mf.bm470.captcha.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.edu.duzce.mf.bm470.captcha.model.ImageWrapper;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;


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
