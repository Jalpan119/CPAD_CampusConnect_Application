package com.cpad.group13.campusconnectapplication.controller;

import com.cpad.group13.campusconnectapplication.model.ResponseSaveStudent;
import com.cpad.group13.campusconnectapplication.model.ResponseSaveTopic;
import com.cpad.group13.campusconnectapplication.model.Student;
import com.cpad.group13.campusconnectapplication.model.Topic;
import com.cpad.group13.campusconnectapplication.service.StudentService;
import com.cpad.group13.campusconnectapplication.service.TopicService;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserDetailsController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TopicService topicService;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Student> getAllUsers() {
        return studentService.getAllStudents();
    }

    @GetMapping(path="/getStudent")
    public @ResponseBody ResponseEntity<Student> getUser(@RequestParam String emailId) {
        try {
            Student student = studentService.getStudentByEmail(emailId);
            if (Objects.nonNull(student)) {
                log.info("Student: " + student);
                return new ResponseEntity<Student>(student, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @GetMapping(path="/getTopicsOfStudent/{id}")
    public @ResponseBody Iterable<Topic> getUser(@PathVariable Integer id) {
        Student student = new Student();
        student.setUserId(id);
        return topicService.getAllTopicsByStudentId(student);
    }

    @PostMapping(path="/saveTopic")
    public ResponseEntity<ResponseSaveTopic> saveTopicForStudent(@RequestBody Topic topic) {
        try {
            Topic topicObj = topicService.saveTopic(topic);
            ResponseSaveTopic topicResponse = new ResponseSaveTopic();
            topicResponse.setTopicId(topicObj.getTopicId());
            topicResponse.setTopicName(topicObj.getTopicName());
            topicResponse.setDescription(topicObj.getDescription());
            topicResponse.setStudentId(topicObj.getStudent().getUserId());
            return new ResponseEntity<ResponseSaveTopic>(topicResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path="/deleteTopic/{id}")
    public ResponseEntity<Topic> deleteTopic(@PathVariable Integer id) {
        try {
            topicService.deleteTopic(id);
            return new ResponseEntity<Topic>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
