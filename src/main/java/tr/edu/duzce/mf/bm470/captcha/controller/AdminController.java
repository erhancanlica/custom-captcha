package tr.edu.duzce.mf.bm470.captcha.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tr.edu.duzce.mf.bm470.captcha.model.Captcha;
import tr.edu.duzce.mf.bm470.captcha.model.ImageWrapper;
import tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaDto;
import tr.edu.duzce.mf.bm470.captcha.model.dto.GeneralResponse;
import tr.edu.duzce.mf.bm470.captcha.model.dto.ImageWrapperDto;
import tr.edu.duzce.mf.bm470.captcha.service.CaptchaService;
import tr.edu.duzce.mf.bm470.captcha.service.ImageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CaptchaService captchaService;

    @GetMapping("/list")
    public ModelAndView getByUsers(final Model model) {
        final ModelAndView modelAndView = new ModelAndView("admin/list");
        List<CaptchaDto> captchaDtos = captchaService.findAll();
        modelAndView.addObject("captchas", captchaDtos);
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView getCaptchaForm(@RequestParam(value = "lengthErr", required = false) String lengthErr) {
        ModelAndView modelAndView = new ModelAndView("admin/create");
        if(StringUtils.isNotEmpty(lengthErr))
            modelAndView.addObject("lengthErr","Doğru resimlerin sayısı 6 ve yanlış resimlerin sayısı 3 olmalıdır." );
        return modelAndView;
    }


    @PostMapping("/create")
    public String save(@ModelAttribute CaptchaDto captchaDto, @RequestParam("trueImages") MultipartFile[] trueImages,
                       @RequestParam("falseImages") MultipartFile[] falseImages) throws Exception {

        if (captchaDto.getCaptchaName().equals("") || captchaDto.getCaptchaCategory().equals("")) {
            return "redirect:/admin/create";
        }

        if (trueImages.length != 6 || falseImages.length != 3) {
            return "redirect:/admin/create?lengthErr=true";
        }

        Captcha captcha = new Captcha();
        captcha.setName(captchaDto.getCaptchaName());
        captcha.setCategory(captchaDto.getCaptchaCategory());
        captcha.setStatus(true);
        captchaService.save(captcha);


        if (!Objects.equals(trueImages[0].getOriginalFilename(), "")) {
            int i = 0;
            for (MultipartFile aFile : trueImages) {
                i++;
                System.out.println("Saving file: " + aFile.getOriginalFilename());

                ImageWrapper trueImage = new ImageWrapper();
                trueImage.setName("file" + i);
                trueImage.setData(aFile.getBytes());
                trueImage.setCaptcha(captcha);
                trueImage.setValid(true);
                imageService.save(trueImage);
            }
        }

        if (!Objects.equals(falseImages[0].getOriginalFilename(), "")) {
            int i = 0;
            for (MultipartFile aFile : falseImages) {
                i++;
                System.out.println("Saving file: " + aFile.getOriginalFilename());

                ImageWrapper falseImage = new ImageWrapper();
                falseImage.setName("file" + i);
                falseImage.setData(aFile.getBytes());
                falseImage.setCaptcha(captcha);
                falseImage.setValid(false);
                imageService.save(falseImage);
            }
        }
        return "redirect:/admin/list";
    }


    @PostMapping("/findById/{captchaId}")
    public ModelAndView findById(@PathVariable long captchaId, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("content/image");
        CaptchaDto captchaDto = captchaService.findById(captchaId);
        modelAndView.addObject("captcha", captchaDto);
        return modelAndView;
    }

    @PutMapping("/merge")
    @ResponseBody
    public GeneralResponse merge(String image, @RequestParam(required = false) MultipartFile file, HttpSession httpSession) {

        ImageWrapper imageWrapper = null;

        try {
            ImageWrapperDto imageDto = new ObjectMapper().readValue(image, ImageWrapperDto.class);

            Captcha captcha = Captcha.builder()
                    .id(imageDto.getCaptchaId())
                    .build();

            imageWrapper = ImageWrapper.builder()
                    .id(imageDto.getId())
                    .isValid(imageDto.isValid())
                    .data(file.getBytes())
                    .captcha(captcha)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GeneralResponse generalResponse = imageService.merge(imageWrapper);
        return generalResponse;

    }

    @DeleteMapping("/delete/{captchaId}")
    @ResponseBody
    public GeneralResponse delete(@PathVariable long captchaId, HttpSession httpSession) {
        return captchaService.delete(captchaId);
    }
}
