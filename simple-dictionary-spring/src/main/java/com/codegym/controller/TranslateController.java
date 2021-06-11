package com.codegym.controller;

import com.codegym.model.Dictionary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TranslateController {

    @GetMapping("/")
    public String showForm(){
        return "index";
    }


    @PostMapping("/")
    public ModelAndView translate(@RequestParam(required = false, name = "input") String input){
        ModelAndView mav = new ModelAndView("index");
        String output = Dictionary.translate(input);
        if (output == null){
            mav.addObject("message", "Not Found!");
        } else {
            mav.addObject("output", output);
        }
        mav.addObject("input", input);
        return mav;
    }
}
