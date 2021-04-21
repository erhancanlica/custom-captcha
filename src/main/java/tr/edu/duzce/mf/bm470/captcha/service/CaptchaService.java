package tr.edu.duzce.mf.bm470.captcha.service;

import tr.edu.duzce.mf.bm470.captcha.model.Captcha;

import java.util.List;

public interface CaptchaService {

    void saveCaptcha(Captcha captcha);

    List<Captcha> findAll();
}
