package com.andrbezr2016.springtask.controller;

import com.andrbezr2016.springtask.model.User;
import com.andrbezr2016.springtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String indexForm() {
        return "index";
    }

    @GetMapping("/data")
    public String dataForm(Model model) {
        model.addAttribute("user", new User());
        return "data";
    }

    @PostMapping("/data")
    public String dataSubmit(@ModelAttribute User user, BindingResult bindingResult) {
        // Ошибки с вводом
        if (bindingResult.hasErrors()) {
            System.out.println("Errors");
        }
        userService.addUser(user);
        return "redirect:/data";
    }

    @GetMapping("/find")
    public String findForm(Model model) {
        model.addAttribute("user", new User());
        return "find";
    }

    @PostMapping("/find")
    public String findSubmit(Model model, @ModelAttribute User user, @RequestHeader(value = "User-Agent") String userAgent) {
        List<User> users = userService.getUsers();
        List<User> foundedUsers = userService.findUser(users, user);
        if (foundedUsers.isEmpty()) {
            // Когда ничего не найдено.
            return "";
        }
        model.addAttribute("foundedUsers", foundedUsers);
        model.addAttribute("time", new Date());
        model.addAttribute("userAgent", userAgent);
        return "find-result";
    }
}
