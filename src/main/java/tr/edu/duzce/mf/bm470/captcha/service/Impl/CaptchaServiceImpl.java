package tr.edu.duzce.mf.bm470.captcha.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.duzce.mf.bm470.captcha.dao.CaptchaDao;
import tr.edu.duzce.mf.bm470.captcha.model.Captcha;
import tr.edu.duzce.mf.bm470.captcha.service.CaptchaService;

import java.util.List;

@Transactional
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private CaptchaDao captchaDao;

    @Override
    public void saveCaptcha(Captcha captcha) {
        captchaDao.saveCaptcha(captcha);

    }

    @Override
    public List<Captcha> findAll() {
        return captchaDao.findAll();
    }
}
