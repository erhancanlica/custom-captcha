package tr.edu.duzce.mf.bm470.captcha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tr.edu.duzce.mf.bm470.captcha.model.Users;
import tr.edu.duzce.mf.bm470.captcha.model.dto.CaptchaToken;
import tr.edu.duzce.mf.bm470.captcha.model.dto.GeneralResponse;
import tr.edu.duzce.mf.bm470.captcha.model.dto.ImageWrapperDto;
import tr.edu.duzce.mf.bm470.captcha.service.CaptchaService;
import tr.edu.duzce.mf.bm470.captcha.service.ImageService;
import tr.edu.duzce.mf.bm470.captcha.service.UserService;
import tr.edu.duzce.mf.bm470.captcha.utils.Constants;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Controller
public class SessionController{

    private static final String MY_LOGIN_VIEW = "login/loginAdmin";

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @Resource
    private AuthenticationManager authManager;

    @GetMapping("/loginAdmin")
    public ModelAndView index(@RequestParam(value = "error", required = false) final String error,
                              @RequestParam(value = "logout", required = false) final String logout) {
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
                                  @RequestParam(value = "captchaErr", required = false) final String captchaErr,
                                  HttpSession session) {
        String randomid = UUID.randomUUID().toString();
        session.setAttribute("capthcaToken", CaptchaToken.builder().token(randomid).build());
        ModelAndView modelAndView = new ModelAndView("login/loginUser");
        if (nonNull(error)) {
            modelAndView.addObject("error", "Kullanıcı adı veya şifre hatalı");
        }
        if (nonNull(logout)) {
            modelAndView.addObject("msg", "Başarıyla çıkış yaptın");
        }
        if (nonNull(captchaErr)) {
            modelAndView.addObject("captchaErr", "Captcha Hatalı");
        }
        modelAndView.addObject("captcha", captchaService.getCaptcha());
        return modelAndView;
    }

    @PostMapping(value = "/validate", consumes = "application/json")
    @ResponseBody
    public GeneralResponse validate(@RequestBody List<ImageWrapperDto> imageWrappers, HttpSession session) {

        GeneralResponse response = imageService.validate(imageWrappers);
        if (response.getResult() == 0) {
            CaptchaToken captchaToken = (CaptchaToken) session.getAttribute("capthcaToken");
            captchaToken.setValidate(true);
            session.setAttribute("capthcaToken", captchaToken);
        }

        return response;
    }


    @PostMapping(value = "/userLogin")
    public ModelAndView login(HttpServletRequest httpServletRequest, @RequestParam String username, @RequestParam String password, HttpSession httpSession) {
        CaptchaToken captchaToken = (CaptchaToken) httpSession.getAttribute("capthcaToken");
        if (captchaToken.isValidate()) {
            UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(username, password);
            try {
                Authentication auth = authManager.authenticate(authReq);
                final User user = (User) auth.getPrincipal();
                final Users dbUser = userService.findByUserName(user.getUsername());
                SecurityContext sc = SecurityContextHolder.getContext();
                sc.setAuthentication(auth);
                httpSession.setAttribute(Constants.userInfoKey, dbUser);
                httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            } catch (Exception ex) {
                return new ModelAndView("redirect:/loginUser?error=true");
            }

            return new ModelAndView("redirect:user/userIndex");
        } else {
            return new ModelAndView("redirect:/loginUser?captchaErr=err");
        }
    }
}
