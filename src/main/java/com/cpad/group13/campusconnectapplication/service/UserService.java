package com.cpad.group13.campusconnectapplication.service;

import com.cpad.group13.campusconnectapplication.model.UserEntityOAuth;
import com.cpad.group13.campusconnectapplication.oauth2.CustomOAuth2User;
import com.cpad.group13.campusconnectapplication.repository.UserEntityOAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserEntityOAuthRepository repo;

    public void processOAuthPostLogin(CustomOAuth2User oauthUser) {
        Optional<UserEntityOAuth> existUser = repo.findById(oauthUser.getEmail());

        if (existUser.isEmpty()) {
            UserEntityOAuth newUser = new UserEntityOAuth();
            newUser.setUserId(oauthUser.getEmail());
            newUser.setUserName(oauthUser.getName());
            newUser.setEnabled(true);

            repo.save(newUser);
        }

    }

}
