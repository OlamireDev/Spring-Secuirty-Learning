package com.olamieDev.LearningSpringSecurity.controller;

import com.olamieDev.LearningSpringSecurity.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api/v1/student")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "George Orwell"),
            new Student(2, "Chi Baby"),
            new Student(3, "Anna Smith")
    );

    @GetMapping("")
    public static List<Student> getSTUDENTS() {
        return STUDENTS;
    }
    @PutMapping("/{id}")
    public void ediStudent(@RequestBody Student student, @PathVariable("id") int id){
        for (Student s: STUDENTS) {
            if(s.getStudentId() == id){
                System.out.println("gonna edit "+ s.getStudentName() +" to "+student.getStudentName());
            }
        }
    }
    @PostMapping("")
    public void newStudent(@RequestBody Student student){
        System.out.println(student.getStudentName()+" is gonna has id"+ STUDENTS.size()+1);
    }
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") int id){
        if(id > 0 && id <=STUDENTS.size()){
            System.out.println("deleteing student with id: "+id);
        }
        else{
            System.out.println("student doesnt exist");
        }
    }
}
