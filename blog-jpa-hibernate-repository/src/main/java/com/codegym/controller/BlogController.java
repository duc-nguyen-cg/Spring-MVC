package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.blog.IBlogService;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }


    @GetMapping
    public ModelAndView showAll(@RequestParam("search") Optional<String> search, @PageableDefault(size = 5) Pageable pageable){
        Page<Blog> blogs;
        if (search.isPresent()){
            blogs = blogService.findAllByContentContaining(search.get(), pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }
        ModelAndView mav = new ModelAndView("blog/list", "blogs", blogs);
        return mav;
    }


    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView mav = new ModelAndView("blog/create");
        mav.addObject("blog", new Blog());
        mav.addObject("category", new Category());
        return mav;
    }


    @PostMapping("/create")
    public ModelAndView addNewBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);
        ModelAndView mav = new ModelAndView("blog/create");
        mav.addObject("blog", new Blog());
        mav.addObject("message", "New blog was added");
        return mav;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Long id){
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()){
            ModelAndView mav = new ModelAndView("blog/edit", "blog", blog.get());
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("error404");
            return mav;
        }
    }


    @PostMapping("/edit")
    public ModelAndView saveBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);
        ModelAndView mav = new ModelAndView("blog/edit");
        mav.addObject("blog", blog);
        mav.addObject("message", "Changes was saved");
        return mav;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id){
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()){
            ModelAndView mav = new ModelAndView("blog/delete", "blog", blog.get());
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("error404");
            return mav;
        }
    }


    @PostMapping("/delete")
    public String deleteBlog(@ModelAttribute("blog") Blog blog, RedirectAttributes redirectAttributes){
        blogService.remove(blog.getId());
        redirectAttributes.addFlashAttribute("message", "One blog was deleted");
        return "redirect:/blog";
    }


    @GetMapping("/view/{id}")
    public ModelAndView showEach(@PathVariable("id") Long id){
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()){
            ModelAndView mav = new ModelAndView("blog/view", "blog", blog);
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("error404");
            return mav;
        }
    }
}
