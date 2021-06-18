package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ModelAndView listCustomers(){
        List<Customer> customerList = customerService.findAll();
        ModelAndView mav = new ModelAndView("list");
        mav.addObject("customers", customerList);
        return mav;
    }


    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView mav = new ModelAndView("create");
        mav.addObject("customer", new Customer());
        return mav;
    }

    @PostMapping("/create")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView mav = new ModelAndView("create");
        mav.addObject("customer", new Customer());
        mav.addObject("message", "New customer was created successfully");
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        if (customer != null){
            ModelAndView mav = new ModelAndView("edit");
            mav.addObject("customer", customer);
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("error404");
            return mav;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("customer", customer);
        mav.addObject("message", "Customer was updated successfully");
        return mav;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        if (customer != null){
            ModelAndView mav = new ModelAndView("delete");
            mav.addObject("customer", customer);
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("error404");
            return mav;
        }
    }

    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer){
        customerService.remove(customer.getId());
        return "redirect:/customer";
    }

}
