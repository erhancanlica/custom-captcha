package tr.edu.duzce.mf.bm470.captcha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tr.edu.duzce.mf.bm470.captcha.model.Captcha;
import tr.edu.duzce.mf.bm470.captcha.model.ImageWrapper;
import tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaDto;
import tr.edu.duzce.mf.bm470.captcha.model.dto.GeneralResponse;
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
    public ModelAndView getByUsers(final Model model){
        final ModelAndView modelAndView = new ModelAndView("admin/listCaptcha");
        List<CaptchaDto> captchaDtos = adminService.findAll();
            modelAndView.addObject("captchas", captchaDtos);
            modelAndView.addObject("captcha", new CaptchaDto());
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


        if (trueImages != null && falseImages != null) {
            int i = 0;
            for (MultipartFile aFile : trueImages){
                i++;
                System.out.println("Saving file: " + aFile.getOriginalFilename());

                ImageWrapper trueImage = new ImageWrapper();
                trueImage.setName("file"+i);
                trueImage.setData(aFile.getBytes());
                trueImage.setCaptcha(captcha);
                trueImage.setValid(true);
                imageService.saveImage(trueImage);
            }
            for (MultipartFile aFile : falseImages){
                i++;
                System.out.println("Saving file: " + aFile.getOriginalFilename());

                ImageWrapper falseImage = new ImageWrapper();
                falseImage.setName("file"+i);
                falseImage.setData(aFile.getBytes());
                falseImage.setCaptcha(captcha);
                falseImage.setValid(false);
                imageService.saveImage(falseImage);
            }
        } else {

        }

        return modelAndView;
    }

    @PostMapping("/findById/{captchaId}")
    public ModelAndView findById(@PathVariable long captchaId, HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView("imageContent");
        CaptchaDto captchaDto = adminService.findById(captchaId);
        modelAndView.addObject("captcha", captchaDto);
        return modelAndView;
    }

    @DeleteMapping("/deleteCaptcha/{captchaId}")
    @ResponseBody
    public void deleteCaptcha(@PathVariable long captchaId, HttpSession httpSession){

        adminService.deleteCaptcha(captchaId);
    }

    @PutMapping("/setCaptcha/{imageId}")
    @ResponseBody
    public void setCatpcha(@PathVariable long imageId, @RequestParam MultipartFile file, HttpSession httpSession){
        System.out.println("ok");
    }

}
