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
import tr.edu.duzce.mf.bm470.captcha.service.AdminService;
import tr.edu.duzce.mf.bm470.captcha.service.CaptchaService;
import tr.edu.duzce.mf.bm470.captcha.service.ImageService;

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

          List<Captcha> listCaptcha = captchaService.findAll();
//        List<ImageWrapper> imageWrappers = imageService.findAll();


            List<CaptchaDto> captchaDtos = listCaptcha.stream().map(mapCaptcha -> {
                    mapCaptcha.getImageWrappers().forEach(imageWrapper -> {
                        try {
                            imageWrapper.setBase(imageWrapper.getImgUtility());
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    });

                    return CaptchaDto.builder()
                    .captchaId(mapCaptcha.getId())
                    .captchaName(mapCaptcha.getName())
                    .captchaCategory(mapCaptcha.getCategory())
                    .imageWrapper(mapCaptcha.getImageWrappers())
                    .build(); })
                    .collect(Collectors.toList());


//
//         List<CaptchaDto> captchaDto = imageWrappers.stream().map((ImageWrapper mapWrapper) -> CaptchaDto.builder()
//                .captchaId(mapWrapper.getCaptcha().getId())
//                .captchaName(mapWrapper.getCaptcha().getName())
//                .captchaCategory(mapWrapper.getCaptcha().getCategory())
//                .imageWrapper(mapWrapper.getBase())
//                .build())
//                .collect(Collectors.toList());

//        captchaDto.addAll(imageDto);

        return captchaDtos;
    }
}
