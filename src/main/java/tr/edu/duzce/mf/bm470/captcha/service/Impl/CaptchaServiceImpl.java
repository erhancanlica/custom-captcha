package tr.edu.duzce.mf.bm470.captcha.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.duzce.mf.bm470.captcha.dao.CaptchaDao;
import tr.edu.duzce.mf.bm470.captcha.model.Captcha;
import tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaDto;
import tr.edu.duzce.mf.bm470.captcha.model.dto.GeneralResponse;
import tr.edu.duzce.mf.bm470.captcha.model.dto.ImageWrapperDto;
import tr.edu.duzce.mf.bm470.captcha.service.CaptchaService;
import tr.edu.duzce.mf.bm470.captcha.utils.ImageUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private CaptchaDao captchaDao;

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
                            .valid(image.isValid())
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
                    .status(dtos.size()== 9)
                    .build();
        }).collect(Collectors.toList());

        return captchaDtos;
    }

    @Override
    public CaptchaDto findById(Long id) {
        Captcha captcha = captchaDao.findById(id);

        List<ImageWrapperDto> imageWrapper = captcha.getImageWrappers().stream().map(image -> {
            ImageWrapperDto imageWrapperDto = null;
            try{
                imageWrapperDto = ImageWrapperDto.builder()
                        .id(image.getId())
                        .name(image.getName())
                        .base(ImageUtils.getImgUtility(image.getData()))
                        .valid(image.isValid())
                        .build();
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }

            return  imageWrapperDto;
        }).collect(Collectors.toList());

        return CaptchaDto.builder()
                .captchaCategory(captcha.getCategory())
                .captchaId(captcha.getId())
                .captchaName(captcha.getName())
                .imageWrapper(imageWrapper)
                .status(imageWrapper.size() == 6)
                .build();
    }

    @Override
    public CaptchaDto getCaptcha() {
        long startTime = System.currentTimeMillis();
        Random rand = new Random();
        List<Long> captchaIds = findAllCaptchaIds();
        CaptchaDto captchaDto = findById(captchaIds.get(rand.nextInt(captchaIds.size())));
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        log.info("calisma süresi : {}",duration);
        return captchaDto;
    }

    @Override
    public GeneralResponse save(Captcha captcha) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            captchaDao.save(captcha);
            generalResponse.setResult(0);
        }catch (Exception e){
            generalResponse.setMessage("Ekleme İşlemi Başarısız...");
            generalResponse.setResult(1);
        }

        return  generalResponse;

    }

    @Override
    public GeneralResponse delete(long captchaId) {
        GeneralResponse generalResponse = new GeneralResponse();
        Captcha captcha = Captcha.builder()
                .id(captchaId)
                .build();
        try {
            captchaDao.delete(captcha);
            generalResponse.setMessage("Silme İşlemi Başarılı...");
            generalResponse.setResult(0);
        }catch (Exception e) {
            generalResponse.setMessage("Silme İşlemi Başarısız...");
            generalResponse.setResult(1);
        }
        return generalResponse;
    }

    @Override
    public List<Long> findAllCaptchaIds() {
        return captchaDao.findAllCaptchaIds();
    }
}









