package tr.edu.duzce.mf.bm470.captcha.service;

import tr.edu.duzce.mf.bm470.captcha.model.ImageWrapper;
import java.util.List;

public interface ImageService {
    void saveImage(ImageWrapper imageWrapper);

    List<ImageWrapper> findAll();

}
