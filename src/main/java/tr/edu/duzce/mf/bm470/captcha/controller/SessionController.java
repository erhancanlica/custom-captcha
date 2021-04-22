package tr.edu.duzce.mf.bm470.captcha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaDto;
import tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaToken;
import tr.edu.duzce.mf.bm470.captcha.service.CaptchaService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Controller
public class SessionController {

    private static final String MY_LOGIN_VIEW = "login";

    @Autowired
    private CaptchaService captchaService;

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
        ModelAndView modelAndView = new ModelAndView("loginUser");
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
    public boolean validate(@RequestBody List<CaptchaDto> captchaDtos,HttpSession session){
        return true;
    }

}
