package com.codegym.controller;

import com.codegym.model.Calculator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalculatorController {

    @GetMapping("/")
    public ModelAndView displayCalculator(){
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("operations", Calculator.operations);
        return mav;
    }


    @PostMapping("/")
    public ModelAndView calculate(@RequestParam(name = "first") String first,
                                  @RequestParam(name = "second") String second,
                                  @RequestParam(name = "operation") String operation, Model model){
        ModelAndView mav = new ModelAndView("index");
        try {
            float firstOperand = Float.parseFloat(first);
            float secondOperand = Float.parseFloat(second);
            float result = Calculator.calculate(firstOperand, secondOperand, operation).floatValue();
            mav.addObject("result", result);
        } catch (NumberFormatException | NullPointerException e){
            mav.addObject("message", "Error!");
        } finally {
            mav.addObject("first", first);
            mav.addObject("second", second);
            mav.addObject("chosenOpr", operation);
            mav.addObject("operations", Calculator.operations);
        }
        return mav;
    }
}
