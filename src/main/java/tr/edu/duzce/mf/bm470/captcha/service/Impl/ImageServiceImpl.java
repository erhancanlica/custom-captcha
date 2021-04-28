package tr.edu.duzce.mf.bm470.captcha.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.duzce.mf.bm470.captcha.dao.ImageDao;
import tr.edu.duzce.mf.bm470.captcha.model.ImageWrapper;
import tr.edu.duzce.mf.bm470.captcha.model.dto.GeneralResponse;
import tr.edu.duzce.mf.bm470.captcha.model.dto.ImageWrapperDto;
import tr.edu.duzce.mf.bm470.captcha.service.ImageService;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
@Transactional
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public GeneralResponse save(ImageWrapper imageWrapper) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {

            imageDao.save(imageWrapper);
            generalResponse.setMessage("Ekleme İşlemi Başarılı...");
            generalResponse.setResult(1);
        }catch (Exception e){
            generalResponse.setMessage("Ekleme İşlemi Başarısız...");
            generalResponse.setResult(0);
        }
        return generalResponse;
    }

    @Override
    public GeneralResponse merge(ImageWrapper imageWrapper) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            imageDao.merge(imageWrapper);
            generalResponse.setMessage("Güncelleme İşlemi Başarılı...");
            generalResponse.setResult(0);

        }catch (Exception e){
            generalResponse.setMessage("Güncelleme İşlemi Başarısız...");
            generalResponse.setResult(1);
        }
        return generalResponse;
    }

    @Override
    public GeneralResponse validate(List<ImageWrapperDto> imageWrappers) {

        GeneralResponse generalResponse= GeneralResponse
                .builder()
                .result(1)
                .message("Yanlış Captcha seçimi yaptınız.").build();

        try{
            if (nonNull(imageWrappers) && imageWrappers.size()==6){
                List<ImageWrapper> trueImageList =imageDao.findByCaptchaId(imageWrappers.get(0).getCaptchaId());

                boolean valid = imageWrappers.stream()
                        .map(ImageWrapperDto::getId)
                        .collect(Collectors.toList()).stream().allMatch(id -> trueImageList.stream()
                                .map(ImageWrapper::getId)
                                .collect(Collectors.toList())
                                .contains(id));
               if (valid){
                   generalResponse.setMessage("Captcha seçme işlemi başarılı.");
                   generalResponse.setResult(0);
               }

            }
        }
        catch (Exception e){
            generalResponse.setMessage("Bilinmeyen bir hata oluştu.");
            generalResponse.setResult(1);
        }


        return generalResponse;
    }

}
