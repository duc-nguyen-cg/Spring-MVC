package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConvertController {

    @GetMapping("/")
    public String displayForm(){
        return "index";
    }


    @PostMapping("/")
    public ModelAndView convert(@RequestParam(required = false, name = "usd") String usd){
        ModelAndView mav = new ModelAndView("index");
        try {
            float vnd = 23000 * (Float.parseFloat(usd));
            mav.addObject("vnd", vnd);
        } catch (NumberFormatException e){
            mav.addObject("message", "Illegal Input");
        }
        mav.addObject("usd", usd);
        return mav;
    }
}
