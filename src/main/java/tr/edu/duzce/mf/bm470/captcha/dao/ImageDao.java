package tr.edu.duzce.mf.bm470.captcha.dao;

import tr.edu.duzce.mf.bm470.captcha.model.ImageWrapper;

import java.util.List;

public interface ImageDao {
    void save(ImageWrapper imageWrapper);
    List<ImageWrapper> findAll();
}
