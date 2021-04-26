package tr.edu.duzce.mf.bm470.captcha.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaptchaDto implements Serializable {

    private long captchaId;

    @NotNull(message = "ad kısmı boş bırakılamaz")
    @Size(min = 2, max = 20, message = "2-20 harf arasında olmalıdır")
    private String captchaName;

    @NotNull(message = "ad kısmı boş bırakılamaz")
    @Size(min = 2, max = 20, message = "2-20 harf arasında olmalıdır")
    private String captchaCategory;

    private boolean status;

    private List<ImageWrapperDto> imageWrapper;

}
