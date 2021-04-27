package tr.edu.duzce.mf.bm470.captcha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tr.edu.duzce.mf.bm470.captcha.model.Captcha;
import tr.edu.duzce.mf.bm470.captcha.model.ImageWrapper;
import tr.edu.duzce.mf.bm470.captcha.model.Users;
import tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaDto;
import tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaToken;
import tr.edu.duzce.mf.bm470.captcha.model.dto.GeneralResponse;
import tr.edu.duzce.mf.bm470.captcha.model.dto.ImageWrapperDto;
import tr.edu.duzce.mf.bm470.captcha.service.CaptchaService;
import tr.edu.duzce.mf.bm470.captcha.service.ImageService;
import tr.edu.duzce.mf.bm470.captcha.service.Impl.ImageServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Controller
public class SessionController {

    private static final String MY_LOGIN_VIEW = "login/loginAdmin";

    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private ImageService imageService;

    @GetMapping("/loginAdmin")
    public ModelAndView index(@RequestParam(value = "error", required = false) final String error,
                              @RequestParam(value = "logout", required = false) final String logout){
        ModelAndView modelAndView = new ModelAndView(MY_LOGIN_VIEW);
        if (nonNull(error)) {
            modelAndView.addObject("error", "Kullanıcı adı veya şifre hatalı");
        }
        if (nonNull(logout)) {
            modelAndView.addObject("msg", "Başarıyla çıkış yaptın");
        }
        return modelAndView;
    }

    @GetMapping("/loginUser")
    public ModelAndView loginUser(@RequestParam(value = "error", required = false) final String error,
                                  @RequestParam(value = "logout", required = false) final String logout,
                                  HttpSession session){
        String randomid= UUID.randomUUID().toString();
        session.setAttribute("capthcaToken", CaptchaToken.builder().token(randomid).build());
        ModelAndView modelAndView = new ModelAndView("login/loginUser");
        if (nonNull(error)) {
            modelAndView.addObject("error", "Kullanıcı adı veya şifre hatalı");
        }
        if (nonNull(logout)) {
            modelAndView.addObject("msg", "Başarıyla çıkış yaptın");
        }
        modelAndView.addObject("captcha",captchaService.getCaptcha());
        return modelAndView;
    }

    @PostMapping(value = "/validate",consumes = "application/json")
    @ResponseBody
    public GeneralResponse validate(@RequestBody List<ImageWrapper> imageWrappers, HttpSession session){

        GeneralResponse response=imageService.validate(imageWrappers);
        if (response.getResult()==0){
            CaptchaToken captchaToken=(CaptchaToken) session.getAttribute("capthcaToken");
            captchaToken.setValidate(true);
            session.setAttribute("capthcaToken",captchaToken);
        }

        return response;
    }


    @PostMapping(value = "/login")
    @ResponseBody
    public GeneralResponse isCaptcha(@RequestBody Users user, HttpSession httpSession){
        CaptchaToken captchaToken=(CaptchaToken) httpSession.getAttribute("capthcaToken");
        if (captchaToken.isValidate()){
            ///işlem yap
        }
        else {
            /// işlem yapma
        }
        GeneralResponse generalResponse=new GeneralResponse();

        return generalResponse;
    }

}
