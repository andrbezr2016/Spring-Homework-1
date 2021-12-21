package com.andrbezr2016.springtask.controller;

import com.andrbezr2016.springtask.model.Message;
import com.andrbezr2016.springtask.model.User;
import com.andrbezr2016.springtask.service.MyEmailService;
import com.andrbezr2016.springtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final MyEmailService emailService;

    @Autowired
    public UserController(UserService userService, MyEmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
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
    public String dataSubmit(Model model, @ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "data";
        }
        userService.addUser(user);
        return "redirect:/data";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        userService.fileUpload(file);
        return "redirect:/data";
    }

    @GetMapping("/find")
    public String findForm(Model model) {
        model.addAttribute("user", new User());
        return "find";
    }

    @PostMapping("/find")
    public String findSubmit(Model model, @ModelAttribute User user, @RequestHeader(value = "User-Agent") String userAgent) {
        List<User> foundedUsers = userService.findUser(user);
        if (foundedUsers.isEmpty()) {
            return "not-found";
        }
        model.addAttribute("foundedUsers", foundedUsers);
        model.addAttribute("time", new Date());
        model.addAttribute("userAgent", userAgent);
        return "find-result";
    }

    @GetMapping("/send")
    public String sendForm(Model model, @RequestParam(value = "toEmail") String to) {
        model.addAttribute("message", new Message(to));
        return "send";
    }

    @PostMapping("/send")
    public String sendSubmit(@ModelAttribute Message message) {
        emailService.sendMessage(message.getTo(), message.getSubject(), message.getText());
        return "send";
    }
}
