package tr.edu.duzce.mf.bm470.captcha.service;

import tr.edu.duzce.mf.bm470.captcha.model.Admins;
import tr.edu.duzce.mf.bm470.captcha.model.Captcha;
import tr.edu.duzce.mf.bm470.captcha.model.ImageWrapper;
import tr.edu.duzce.mf.bm470.captcha.model.Users;
import tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaDto;

import java.util.List;

public interface AdminService {

    List<Users> getByUsers();

    Admins findByUser(Users user);

    List<CaptchaDto> findAll();

    CaptchaDto findById(Long id);
}
