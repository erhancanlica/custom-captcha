package tr.edu.duzce.mf.bm470.captcha.dao;

import tr.edu.duzce.mf.bm470.captcha.model.Captcha;
import tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaDto;

import java.util.List;

public interface CaptchaDao {

    List<Captcha> findAll();

    Captcha findById(Long id);

    void save(Captcha captcha);

    void delete(Captcha captcha);

    List<Long> findAllCaptchaIds();
}
