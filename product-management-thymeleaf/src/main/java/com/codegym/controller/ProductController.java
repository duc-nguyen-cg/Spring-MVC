package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;


    @GetMapping("")
    public String showAll(@RequestParam(required = false) String search, Model model){
        List<Product> productList;
        if (search == null) {
            productList = this.productService.findAll();
        } else if (search.equals("")){
            return "redirect:/product";
        } else {
            model.addAttribute("search", search);
            productList = this.productService.findByName(search);
        }
        model.addAttribute("products", productList);
        return "list";
    }


    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView mav = new ModelAndView("create");
        mav.addObject("product", new Product());
        return mav;
    }


    @PostMapping("/save")
    public String saveProduct(Product product, RedirectAttributes redirectAttributes){
        String message;
        product.setId((int) (Math.random() * 10000));
        if (this.productService.add(product)) {
            message = "Added new product";
        } else {
            message = "Error occurred";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/product";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("product", this.productService.findById(id));
        return mav;
    }

    @PostMapping("/update")
    public String update(Product product, RedirectAttributes redirectAttributes){
        String message;
        if (this.productService.update(product.getId(), product)){
            message = "Updated chosen product";
        } else {
            message = "Error occurred";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/product";
    }


    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("delete");
        mav.addObject("product", this.productService.findById(id));
        return mav;
    }


    @PostMapping("/delete")
    public String delete(Product product, RedirectAttributes redirectAttributes){
        String message;
        if (this.productService.remove(product.getId())){
            message = "Removed a product";
        } else {
            message = "Error occurred";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/product";
    }


    @GetMapping("/{id}/view")
    public ModelAndView view(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("view");
        mav.addObject("product", this.productService.findById(id));
        return mav;
    }
}
