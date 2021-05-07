package tr.edu.duzce.mf.bm470.captcha.service;

import tr.edu.duzce.mf.bm470.captcha.model.Captcha;
import tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaDto;
import tr.edu.duzce.mf.bm470.captcha.model.dto.GeneralResponse;

import java.util.List;

public interface CaptchaService {

    GeneralResponse save(Captcha captcha);

    List<CaptchaDto> findAll();

    CaptchaDto getCaptcha();

    CaptchaDto findById(Long id);

    GeneralResponse delete(long captchaId);

    List<Long> findAllCaptchaIds();
}
