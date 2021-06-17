package com.codegym.controller;

import com.codegym.model.Comment;
import com.codegym.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/main")
public class CommentController {
    @Autowired
    private ICommentService iCommentService;
    private int[] rateList = {1,2,3,4,5};

    @GetMapping("")
    public String showMain(Model model){
        List<Comment> commentList = iCommentService.findAllWithinDay();
        model.addAttribute("comment", new Comment());
        model.addAttribute("rateList", rateList);
        model.addAttribute("commentList", commentList);
        return "view";
    }

    @PostMapping("")
    public String getComment(@ModelAttribute Comment comment, Model model, RedirectAttributes redirectAttributes){
        model.addAttribute("comment", comment);
        iCommentService.save(comment);
        redirectAttributes.addFlashAttribute("rateList", rateList);
        redirectAttributes.addFlashAttribute("commentList", iCommentService.findAllWithinDay());
        redirectAttributes.addFlashAttribute("comment", new Comment());
        return "redirect:/main";
    }

    @GetMapping("{id}")
    public String like(@PathVariable Long id, Model model,RedirectAttributes redirectAttributes){
        Comment comment = iCommentService.findById(id);
        comment.setLike(comment.getLike()+1);
        iCommentService.save(comment);
        redirectAttributes.addFlashAttribute("rateList", rateList);
        redirectAttributes.addFlashAttribute("commentList", iCommentService.findAllWithinDay());
        redirectAttributes.addFlashAttribute("comment", new Comment());
        return "redirect:/main";
    }
}