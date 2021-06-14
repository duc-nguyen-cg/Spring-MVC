package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CondimentController {

    @GetMapping("/")
    public String showCondiment(){
        return "index";
    }


    @PostMapping("/")
    public String save(@RequestParam(value = "condiments", required = false) String[] condiments, Model model){
        if (condiments == null){
            model.addAttribute("message", "Choose at least one condiment");
            return "index";
        } else {
            model.addAttribute("condiments", condiments);
            return "save";
        }
    }
}
