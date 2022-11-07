package com.student.ust.service;

import com.student.ust.entity.Student;
import com.student.ust.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Student service.
 */
@Service
public class StudentService {

    /**
     * The Student repository.
     */
    @Autowired
    StudentRepository studentRepository;

    /**
     * Gets student by id.
     *
     * @param id the id
     * @return the student by id
     */
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    /**
     * Save student.
     *
     * @param student the student
     */
    public void saveStudent(Student student) {
        student.setCreatedDate(LocalDateTime.now());
        student.setModifyDate(student.getCreatedDate());
        studentRepository.save(student);
    }


    /**
     * Get all student list.
     *
     * @return the list
     */
    public List<Student>  getAllStudent(){
        return studentRepository.findAll();
    }

    /**
     * Delete student.
     *
     * @param id the id
     */
    public void deleteStudent(Integer id){
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Student student){
        Student updateStudent = studentRepository.findById(student.getStudentId()).orElseThrow(() -> new NoSuchElementException());
        updateStudent.setAge(student.getAge());
        updateStudent.setRollNo(student.getRollNo());
        updateStudent.setModifyDate(LocalDateTime.now());
        studentRepository.save(updateStudent);
        return updateStudent;
    }
}
