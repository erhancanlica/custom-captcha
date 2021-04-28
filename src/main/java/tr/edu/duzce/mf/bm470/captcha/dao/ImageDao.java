package tr.edu.duzce.mf.bm470.captcha.dao;

import tr.edu.duzce.mf.bm470.captcha.model.Captcha;
import tr.edu.duzce.mf.bm470.captcha.model.ImageWrapper;
import tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaDto;

import java.util.List;

public interface ImageDao {

    void save(ImageWrapper imageWrapper);

    void merge(ImageWrapper imageWrapper);



    List<ImageWrapper> findByCaptchaId(Long id);


}
