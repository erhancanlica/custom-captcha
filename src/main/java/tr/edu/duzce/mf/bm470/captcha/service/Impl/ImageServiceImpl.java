package tr.edu.duzce.mf.bm470.captcha.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.duzce.mf.bm470.captcha.dao.ImageDao;
import tr.edu.duzce.mf.bm470.captcha.model.ImageWrapper;
import tr.edu.duzce.mf.bm470.captcha.service.ImageService;

import java.util.List;

@Transactional
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public void save(ImageWrapper imageWrapper) {
        imageDao.save(imageWrapper);
    }

    @Override
    public List<ImageWrapper> findAll() {
        return imageDao.findAll();
    }
}
