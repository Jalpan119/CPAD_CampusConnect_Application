package com.cpad.group13.campusconnectapplication.repository;

import com.cpad.group13.campusconnectapplication.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByEmailId(String emailId);
}