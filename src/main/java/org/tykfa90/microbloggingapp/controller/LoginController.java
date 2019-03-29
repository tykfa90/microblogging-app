package org.tykfa90.microbloggingapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = "/login")
public class LoginController {
    @GetMapping
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @GetMapping(path = "?logout")
    public ModelAndView logout(ModelAndView modelAndView) {
        modelAndView.setViewName("/login");
        return modelAndView;
    }
}
