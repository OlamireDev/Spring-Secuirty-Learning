package com.olamieDev.LearningSpringSecurity.controller;

import com.olamieDev.LearningSpringSecurity.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "George Orwell"),
            new Student(2, "Chi Baby"),
            new Student(3, "Anna Smith")
    );


    @GetMapping("{student-id}")
    public Student getStudent(@PathVariable ("student-id") Integer studentId){
        return STUDENTS.stream().filter(student -> studentId==student.getStudentId()).findFirst().orElseThrow(() -> new
                IllegalStateException("Student "+ studentId +" Not found"));
    }
}
