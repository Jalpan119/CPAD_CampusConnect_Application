package com.cpad.group13.campusconnectapplication.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "university")
    private String university;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "degree")
    private String degree;

    @Column(name = "verified")
    private String verified;
}
