package tr.edu.duzce.mf.bm470.captcha.service;

import tr.edu.duzce.mf.bm470.captcha.model.Captcha;
import tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaDto;

import java.util.List;

public interface CaptchaService {

    void saveCaptcha(Captcha captcha);

    List<CaptchaDto> findAll();

    CaptchaDto getCaptcha();
}
