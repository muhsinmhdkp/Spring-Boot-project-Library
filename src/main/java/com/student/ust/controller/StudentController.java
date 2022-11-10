package com.student.ust.controller;

import com.student.ust.entity.Student;
import com.student.ust.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.net.ResponseCache;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Student controller.
 */
@RestController
public class StudentController {
    /**
     * The Student service.
     */
    @Autowired
    StudentService studentService;

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/students/{id}")
        public ResponseEntity<Student>
                get(@PathVariable Integer id){
            try{
                Student student = studentService.getStudentById(id);
                return new ResponseEntity<Student>(student, HttpStatus.OK);
            }
            catch (NoSuchElementException e){
                return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
            }
        }

    @GetMapping("/students/{name}")
    public ResponseEntity<Student> get(@PathVariable String name){
        try{
            Student student = studentService.studentByName(name);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get response entity.
     *
     * @return the response entity
     */
    @GetMapping("/students")
    public ResponseEntity<List<Student>>get(){
        try{
            List<Student> allStudent = studentService.getAllStudent();
            return new ResponseEntity<List<Student>>(allStudent, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<List<Student>>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add.
     *
     * @param student the student
     */
    @PostMapping("/students")
    public void add
            (@RequestBody Student student){
    studentService.saveStudent(student);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @DeleteMapping("/students/{id}")
    public void delete(@PathVariable Integer id ){
        studentService.deleteStudent(id);
    }

    @PutMapping("/students")
    public ResponseEntity<Student> put(@RequestBody Student student){
        try{
            Student updateStudent = studentService.updateStudent(student);
            return new ResponseEntity<Student>(updateStudent, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

}



