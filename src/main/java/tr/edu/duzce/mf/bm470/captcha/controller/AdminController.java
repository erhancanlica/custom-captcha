package tr.edu.duzce.mf.bm470.captcha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tr.edu.duzce.mf.bm470.captcha.model.Captcha;
import tr.edu.duzce.mf.bm470.captcha.model.ImageWrapper;
import tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaDto;
import tr.edu.duzce.mf.bm470.captcha.service.AdminService;
import tr.edu.duzce.mf.bm470.captcha.service.CaptchaService;
import tr.edu.duzce.mf.bm470.captcha.service.ImageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CaptchaService captchaService;

    @GetMapping("/listCaptcha")
    public ModelAndView getByUsers(){
        ModelAndView modelAndView = new ModelAndView("admin/listCaptcha");
        List<CaptchaDto> captchaDtos = adminService.findAll();
            modelAndView.addObject("captchas", captchaDtos);
            return modelAndView;
    }

    @GetMapping("/createCaptcha")
    public ModelAndView getCaptchaForm(@RequestParam(value = "lengthErr", required = false) String lengthErr){
        ModelAndView modelAndView = new ModelAndView("admin/createCaptcha");
        return modelAndView;
    }

    @PostMapping("/createCaptcha")
    public ModelAndView saveImage(HttpServletRequest request,
                                   @ModelAttribute CaptchaDto captchaDto, @RequestParam("trueImages") MultipartFile[] trueImages, @RequestParam("falseImages") MultipartFile[] falseImages) throws Exception {


        ModelAndView modelAndView = new ModelAndView("admin/listCaptcha");

        Captcha captcha = new Captcha();
        captcha.setName(captchaDto.getCaptchaName());
        captcha.setCategory(captchaDto.getCaptchaCategory());
        captchaService.saveCaptcha(captcha);


        if (trueImages != null && trueImages.length == 6) {
            int i = 0;
            for (MultipartFile aFile : trueImages){
                i++;
                System.out.println("Saving file: " + aFile.getOriginalFilename());

                ImageWrapper imageWrapper = new ImageWrapper();
                imageWrapper.setName("file"+i);
                imageWrapper.setData(aFile.getBytes());
                imageWrapper.setCaptcha(captcha);

                imageService.saveImage(imageWrapper);

            }
        } else {

        }

        return modelAndView;
    }



    @GetMapping("/findById")
    public ModelAndView findById(HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView("admin/listCaptcha");
//        CaptchaDto captchaDto = adminService.findById(id);
//        modelAndView.addObject("captcha", captchaDto);

        return null;
    }

}
