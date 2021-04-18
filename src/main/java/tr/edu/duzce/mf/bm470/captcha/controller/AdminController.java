package tr.edu.duzce.mf.bm470.captcha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tr.edu.duzce.mf.bm470.captcha.model.ImageWrapper;
import tr.edu.duzce.mf.bm470.captcha.service.AdminService;
import tr.edu.duzce.mf.bm470.captcha.service.ImageService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ImageService imageService;

    @GetMapping
    public ModelAndView getByUsers(){
        ModelAndView modelAndView = new ModelAndView("admin/index");
        modelAndView.addObject("images", imageService.findAll());
        return modelAndView;
    }

    @PostMapping("/fileUpload")
    public String handleFileUpload(HttpServletRequest request,
                                   @RequestParam("files") MultipartFile files[]) throws Exception {

        if (files != null && files.length > 0) {
            int i = 0;
            for (MultipartFile aFile : files){
                i++;
                System.out.println("Saving file: " + aFile.getOriginalFilename());

                ImageWrapper uploadFile = new ImageWrapper();
                uploadFile.setName("file"+i);
                uploadFile.setData(aFile.getBytes());
                imageService.save(uploadFile);
            }
        }

        return "Success";
    }

    @GetMapping("/createCaptcha")
    public ModelAndView getCaptchaForm(){
        ModelAndView modelAndView = new ModelAndView("admin/createCaptcha");
        return modelAndView;
    }

}
