package tr.edu.duzce.mf.bm470.captcha.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.duzce.mf.bm470.captcha.dao.AdminDao;
import tr.edu.duzce.mf.bm470.captcha.dao.Impl.AdminDaoImpl;
import tr.edu.duzce.mf.bm470.captcha.model.Admins;
import tr.edu.duzce.mf.bm470.captcha.model.Captcha;
import tr.edu.duzce.mf.bm470.captcha.model.ImageWrapper;
import tr.edu.duzce.mf.bm470.captcha.model.Users;
import tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaDto;
import tr.edu.duzce.mf.bm470.captcha.model.dto.ImageWrapperDto;
import tr.edu.duzce.mf.bm470.captcha.service.AdminService;
import tr.edu.duzce.mf.bm470.captcha.service.CaptchaService;
import tr.edu.duzce.mf.bm470.captcha.service.ImageService;
import tr.edu.duzce.mf.bm470.captcha.utils.ImageUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private ImageService imageService;

    public List<Users> getByUsers() {
        List<Users> users = adminDao.getByUsers();
        return users;
    }

    @Override
    public Admins findByUser(Users user) {
        return adminDao.findByUser(user);
    }

    @Override
    public List<CaptchaDto> findAll() {
       return captchaService.findAll();
    }

    @Override
    public CaptchaDto findById(Long id) {
        return captchaService.findById(id);
    }

    @Override
    public void deleteCaptcha(long captchaId) {
//        imageService.deleteImage(captchaId);

        Captcha captcha = Captcha.builder()
                .id(captchaId)
                .build();
        captchaService.deleteCaptcha(captcha);
    }
}
