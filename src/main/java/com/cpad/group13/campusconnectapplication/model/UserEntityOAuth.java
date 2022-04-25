package com.cpad.group13.campusconnectapplication.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@RequiredArgsConstructor
public class UserEntityOAuth {

    @Id
    @Value("user_id")
    private String userId;

    @Value("user_name")
    private String userName;

    @Value("is_enabled")
    private boolean isEnabled;
}
