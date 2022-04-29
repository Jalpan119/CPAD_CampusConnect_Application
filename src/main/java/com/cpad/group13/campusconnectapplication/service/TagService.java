package com.cpad.group13.campusconnectapplication.service;

import com.cpad.group13.campusconnectapplication.model.Student;
import com.cpad.group13.campusconnectapplication.model.Tag;
import com.cpad.group13.campusconnectapplication.repository.TagRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagRepository repo;

    public Optional<Tag> getTagById(Integer tagId) {
        return repo.findById(tagId);
    }

    public Iterable<Tag> getAllTagsByStudentId(Student student) {
        return repo.findAllByStudent(student);
    }

    public Tag saveTag(Tag tag) {
        return repo.save(tag);
    }

    public void deleteTag(Integer tagId) {
        repo.deleteById(tagId);
    }
}
