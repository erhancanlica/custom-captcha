package tr.edu.duzce.mf.bm470.captcha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import tr.edu.duzce.mf.bm470.captcha.model.Users;
import tr.edu.duzce.mf.bm470.captcha.service.AdminService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public ModelAndView getByUsers(){

        ModelAndView modelAndView = new ModelAndView("index");
        List<Users> users  = adminService.getByUsers();
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}
