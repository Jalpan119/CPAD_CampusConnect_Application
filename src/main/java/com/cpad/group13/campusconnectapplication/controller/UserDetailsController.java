package com.cpad.group13.campusconnectapplication.controller;

import com.cpad.group13.campusconnectapplication.model.ResponseSaveStudent;
import com.cpad.group13.campusconnectapplication.model.Student;
import com.cpad.group13.campusconnectapplication.service.StudentService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserDetailsController {

    @Autowired
    private StudentService studentService;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Student> getAllUsers() {
        return studentService.getAllStudents();
    }

    @GetMapping(path="/getStudent")
    public @ResponseBody Student getUser(@RequestParam String emailId) {
        Student student = studentService.getStudentByEmail(emailId);
        return student;
    }

    @PostMapping(path="/saveStudent")
    public ResponseEntity<ResponseSaveStudent> saveStudent(@RequestBody Student student) {
        try {
            Student existUser = studentService.getStudentByEmail(student.getEmailId());
            if (Objects.nonNull(existUser)) {
                student.setUserId(existUser.getUserId());
                Student studentObj = studentService.saveStudent(student);
                ResponseSaveStudent resp = new ResponseSaveStudent(studentObj.getUserId(),
                        studentObj.getFirstName(), studentObj.getVerified());
                return new ResponseEntity<ResponseSaveStudent>(resp, HttpStatus.OK);
            } else {
                Student studentObj = studentService.saveStudent(student);
                ResponseSaveStudent resp = new ResponseSaveStudent(studentObj.getUserId(),
                        studentObj.getFirstName(), studentObj.getVerified());
                return new ResponseEntity<ResponseSaveStudent>(resp, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
