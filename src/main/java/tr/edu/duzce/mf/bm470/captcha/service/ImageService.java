package tr.edu.duzce.mf.bm470.captcha.service;

import tr.edu.duzce.mf.bm470.captcha.model.ImageWrapper;
import tr.edu.duzce.mf.bm470.captcha.model.dto.GeneralResponse;


public interface ImageService {

    GeneralResponse save(ImageWrapper imageWrapper);

    GeneralResponse merge(ImageWrapper imageWrapper);
}
