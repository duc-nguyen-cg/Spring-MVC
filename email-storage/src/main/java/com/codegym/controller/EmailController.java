package com.codegym.controller;

import com.codegym.model.Email;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmailController {
    private  Email myEmail = new Email();

    @GetMapping("/")
    public ModelAndView showInfo(){
        ModelAndView mav = new ModelAndView("info", "email", myEmail);
        return mav;
    }


    @GetMapping("/edit")
    public ModelAndView showEditForm(){
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("email", myEmail);
        mav.addObject("languages", Email.languages);
        mav.addObject("pagesizes", Email.pagesizes);
        return mav;
    }


    @PostMapping("/edit")
    public String update(@ModelAttribute(name = "email") Email updatedEmail){
        myEmail = updatedEmail;
        return "redirect:/";
    }
}
