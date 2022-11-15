package com.student.ust.controller;

import com.student.ust.dto.StudentDto;
import com.student.ust.entity.Student;
import com.student.ust.exception.BuisnessException;
import com.student.ust.exception.InvalidEmailException;
import com.student.ust.exception.InvalidPasswordException;
import com.student.ust.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.NoSuchElementException;


/**
 * The type Student controller.
 */
@RestController
@Slf4j
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
        public ResponseEntity<StudentDto> get(@PathVariable Integer id){

            try{
                log.debug("inside debug The Book Id passed as Pathvariable ="+id);
                Student student = studentService.getStudentById(id);
                StudentDto studentDto = studentService.entityToDtoConverter(student);
                return new ResponseEntity<StudentDto>(studentDto, HttpStatus.OK);
            }
            catch (NoSuchElementException e){
                return new ResponseEntity<StudentDto>(HttpStatus.NOT_FOUND);
            }
        }

    /**
     * Gets request.
     *
     * @param studentId the student id Here id is requested using @RequestParam
     * @return the request
     */
    @GetMapping("/studentsreq")
        public ResponseEntity<Student> getRequest(@RequestParam(name = "id") Integer studentId){
        try{
            Student student = studentService.getStudentById(studentId);
            return  new ResponseEntity<Student>(student, HttpStatus.OK);
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
    public ResponseEntity<List<StudentDto>>get(){
        try{
            List<Student> allStudent = studentService.getAllStudent();
            List<StudentDto> allStudentList = studentService.entityToDtoListConverter(allStudent);
            return new ResponseEntity<List<StudentDto>>(allStudentList, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<List<StudentDto>>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Add response entity.
     *
     * @param student the student
     * @return the response entity
     * @throws BuisnessException the buisness exception
     */
    @PostMapping("/students")
        public ResponseEntity<Student> add (@RequestBody Student student) throws BuisnessException {

            try {
                    studentService.saveStudent(student);
                    return new ResponseEntity<Student>(HttpStatus.OK);
                }
            catch (BuisnessException e){
                return new ResponseEntity<Student>(HttpStatus.PRECONDITION_FAILED);
            }
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

    /**
     * Put response entity.
     *
     * @param student the student
     * @return the response entity
     */
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



/**@GetMapping("/students/{name}")
public ResponseEntity<Student> get(@PathVariable String name){
try{
Student student = studentService.studentByName(name);
return new ResponseEntity<Student>(student, HttpStatus.OK);
}
catch (NoSuchElementException e){
return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
}
}


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
 **/

/**
 * Get response entity.
 *
 * @return the response entity
 */

