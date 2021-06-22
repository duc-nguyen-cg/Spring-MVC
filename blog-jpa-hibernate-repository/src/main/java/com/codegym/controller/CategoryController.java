package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.repository.IBlogRepository;
import com.codegym.service.blog.IBlogService;
import com.codegym.service.category.CategoryService;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IBlogService blogService;


    @GetMapping
    public ModelAndView showAll(){
        Iterable<Category> categories = categoryService.findAll();
        ModelAndView mav = new ModelAndView("/category/list", "categories", categories);
        return mav;
    }

    @GetMapping("/{id}")
    public ModelAndView showEach(@RequestParam(required = false, name = "search") String search, @PathVariable("id") Long id, Pageable pageable){
        Optional<Category> category = categoryService.findById(id);
        Page<Blog> blogs;
        ModelAndView mav;
        if (category.isPresent() && search == null) {
            blogs = blogService.findAllByCategory(category.get(), pageable);
            mav = new ModelAndView("/category/view", "blogs", blogs);
        } else if (category.isPresent() && search != null){
            blogs = blogService.findAllByAuthorContainingAndCategory(search, category.get(), pageable);
            mav = new ModelAndView("/category/view", "blogs", blogs);
        } else {
             mav = new ModelAndView("error404");
        }
        return mav;
    }


    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView mav = new ModelAndView("/category/create", "category", new Category());
        return mav;
    }

    @PostMapping("/create")
    public ModelAndView addNewCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView mav = new ModelAndView("/category/create");
        mav.addObject("category", new Category());
        mav.addObject("message", "New category was added");
        return mav;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Long id){
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()){
            ModelAndView mav = new ModelAndView("/category/edit", "category", category.get());
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("error404");
            return mav;
        }
    }


    @PostMapping("/edit")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView mav = new ModelAndView("category/edit");
        mav.addObject("category", category);
        mav.addObject("message", "Changes was saved");
        return mav;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id){
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()){
            ModelAndView mav = new ModelAndView("/category/delete", "category", category.get());
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("error404");
            return mav;
        }
    }

    @PostMapping("/delete")
    public String deleteBlog(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes){
        categoryService.remove(category.getId());
        redirectAttributes.addFlashAttribute("message", "One category was deleted");
        return "redirect:/category";
    }
}
