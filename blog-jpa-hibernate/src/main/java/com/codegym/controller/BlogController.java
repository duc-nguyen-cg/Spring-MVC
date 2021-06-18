package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @GetMapping
    public ModelAndView showAll(){
        List<Blog> blogs = blogService.findAll();
        ModelAndView mav = new ModelAndView("list", "blogs", blogs);
        return mav;
    }


    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView mav = new ModelAndView("create", "blog", new Blog());
        return mav;
    }


    @PostMapping("/create")
    public ModelAndView addNewBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);
        ModelAndView mav = new ModelAndView("create");
        mav.addObject("blog", new Blog());
        mav.addObject("message", "New blog was added");
        return mav;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") Long id){
        Blog blog = blogService.findById(id);
        if (blog != null){
            ModelAndView mav = new ModelAndView("edit", "blog", blog);
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("error404");
            return mav;
        }
    }

    @PostMapping("/edit")
    public ModelAndView saveBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("blog", blog);
        mav.addObject("message", "Changes was saved");
        return mav;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id){
        Blog blog = blogService.findById(id);
        if (blog != null){
            ModelAndView mav = new ModelAndView("delete", "blog", blog);
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
        return "redirect:/";
    }


    @GetMapping("/view/{id}")
    public ModelAndView showEach(@PathVariable("id") Long id){
        Blog blog = blogService.findById(id);
        ModelAndView mav = new ModelAndView("view", "blog", blog);
        return mav;
    }
}
