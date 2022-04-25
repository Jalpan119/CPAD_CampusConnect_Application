package com.cpad.group13.campusconnectapplication.controller;

import com.cpad.group13.campusconnectapplication.model.UserEntityOAuth;
import com.cpad.group13.campusconnectapplication.repository.UserEntityOAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetailsController {

    @Autowired
    private UserEntityOAuthRepository repo;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<UserEntityOAuth> getAllUsers() {
        return repo.findAll();
    }

    @GetMapping(path="/getUser")
    public @ResponseBody UserEntityOAuth getUser(@RequestParam String userid) {
        if (repo.findById(userid).isPresent()) {
            return repo.findById(userid).get();
        }
        return null;
    }
}
