package com.cpad.group13.campusconnectapplication.repository;

import com.cpad.group13.campusconnectapplication.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByEmailId(String emailId);
}