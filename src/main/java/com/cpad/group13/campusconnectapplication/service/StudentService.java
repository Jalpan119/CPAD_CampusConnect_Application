package com.cpad.group13.campusconnectapplication.service;

import com.cpad.group13.campusconnectapplication.model.Student;
import com.cpad.group13.campusconnectapplication.repository.StudentRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository repo;

    public Student saveStudent(Student student) {
        return repo.save(student);
    }

    public Iterable<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student getStudentByEmail(String emailId) {
        return repo.findByEmailId(emailId);
    }

    public Optional<Student> getStudentById(Integer userId) {
        return repo.findById(userId);
    }

}
