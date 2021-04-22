package tr.edu.duzce.mf.bm470.captcha.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.duzce.mf.bm470.captcha.dao.CaptchaDao;
import tr.edu.duzce.mf.bm470.captcha.model.Captcha;
import tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaDto;
import tr.edu.duzce.mf.bm470.captcha.model.dto.ImageWrapperDto;
import tr.edu.duzce.mf.bm470.captcha.service.CaptchaService;
import tr.edu.duzce.mf.bm470.captcha.utils.ImageUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<CaptchaDto> findAll() {
        List<Captcha> listCaptcha = captchaDao.findAll();
        List<CaptchaDto> captchaDtos = listCaptcha.stream().map(captcha -> {
            List<ImageWrapperDto> dtos = captcha.getImageWrappers().stream().map(image -> {
                ImageWrapperDto dto = null;
                try {
                    dto = ImageWrapperDto.builder()
                            .id(image.getId())
                            .name(image.getName())
                            .base(ImageUtils.getImgUtility(image.getData()))
                            .build();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return dto;
            }).collect(Collectors.toList());
            return CaptchaDto.builder()
                    .captchaCategory(captcha.getCategory())
                    .captchaId(captcha.getId())
                    .captchaName(captcha.getName())
                    .imageWrapper(dtos)
                    .status(dtos.size()== 6)
                    .build();
        }).collect(Collectors.toList());

        return captchaDtos;
    }

    @Override
    public CaptchaDto getCaptcha() {
        CaptchaDto captchaDto = findAll().get(0);
        return captchaDto;
    }
}
