package com.department2.Student3.service;

import com.department2.Student3.Department;
import com.department2.Student3.Student;
import com.department2.Student3.repository.DepartmentRepository;
import com.department2.Student3.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    public List<Student> getStudentsByDepartment(Long departmentId) {
        return studentRepository.findByDepartmentId(departmentId);
    }

    public Student createStudent(Long departmentId, Student student) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        student.setDepartment(department);
        return studentRepository.save(student);
    }

    public Student updateStudent(Student existingStudent, Student updatedStudent) {
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setSex(updatedStudent.getSex());
        existingStudent.setBloodgroup(updatedStudent.getBloodgroup());
        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }
}
