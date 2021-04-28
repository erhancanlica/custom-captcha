package tr.edu.duzce.mf.bm470.captcha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tr.edu.duzce.mf.bm470.captcha.model.Users;
import tr.edu.duzce.mf.bm470.captcha.utils.Constants;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/userIndex")
    public ModelAndView loginUser(HttpSession session){
        Users users = (Users) session.getAttribute(Constants.userInfoKey);
        ModelAndView modelAndView = new ModelAndView("user/userIndex");
        modelAndView.addObject("user",users);
        return modelAndView;
    }
}
