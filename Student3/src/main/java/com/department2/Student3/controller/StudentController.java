package com.department2.Student3.controller;

import com.department2.Student3.Student;
import com.department2.Student3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




// StudentController.java
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/departments/{departmentId}/students/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long departmentId, @PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/departments/{departmentId}/students")
    public ResponseEntity<List<Student>> getStudentsByDepartment(@PathVariable Long departmentId) {
        List<Student> students = studentService.getStudentsByDepartment(departmentId);
        return ResponseEntity.ok(students);
    }

    @PostMapping("/departments/{departmentId}/students")
    public ResponseEntity<Student> createStudent(@PathVariable Long departmentId, @RequestBody Student student) {
        Student createdStudent = studentService.createStudent(departmentId, student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("/departments/{departmentId}/students/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long departmentId,
                                                 @PathVariable Long studentId,
                                                 @RequestBody Student updatedStudent) {
        Student existingStudent = studentService.getStudentById(studentId);
        if (existingStudent == null) {
            return ResponseEntity.notFound().build();
        }
        Student updated = studentService.updateStudent(existingStudent, updatedStudent);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/departments/{departmentId}/students/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long departmentId, @PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}