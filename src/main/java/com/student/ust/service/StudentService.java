package com.student.ust.service;

import com.student.ust.entity.Student;
import com.student.ust.exception.InvalidEmailException;
import com.student.ust.exception.InvalidPasswordException;
import com.student.ust.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public Student getStudentById(Integer id) throws NoSuchElementException {
        return studentRepository.findById(id).orElseThrow(()-> new NoSuchElementException ());
    }

    /**
     * Save student.
     *
     * @param student the student
     */
    public void saveStudent(Student student) throws InvalidEmailException,InvalidPasswordException{

        student.setCreatedDate(LocalDateTime.now());
        student.setModifyDate(student.getCreatedDate());


        String email = student.getEmail();
        String regexEmail = "^([A-Za-z0-9_.-]+)@([a-z]+)\\.([a-z]+)$";
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);

        String password = student.getPassword();
        String regexPassword =  "^(?=(?:.*\\d){3,})(?=\\S+$)(?=.*[@#$%^&+=])(?=(?:.*[A-Za-z]){3,})(?=.*[A-Z]).{8,}$";
        //String regexPassword =  "^(?=.*\\d)(?=\\S+$)(?=.*[@#$%^&+=])(?=.*[a-z])(?=.*[A-Z]).{8,10}$";
        Pattern pattern1 = Pattern.compile(regexPassword);
        Matcher matcher1 = pattern1.matcher(password);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        String encodedpassword = bCryptPasswordEncoder.encode(student.getPassword());


        if (matcher.matches()){
            if(matcher1.matches()) {
                student.setPassword(encodedpassword);
                studentRepository.save(student);
            }
            else {
                throw new InvalidPasswordException();
            }
        }
        else if (matcher1.matches()) {
            if (matcher.matches()) {
                student.setPassword(encodedpassword);
                studentRepository.save(student);
            }
            else {
                throw new InvalidEmailException();
            }
        }
        else  if (matcher.matches()==false){
            throw new InvalidEmailException();
        }
        else {
            throw new InvalidPasswordException();
        }
        }




    /**
     * public void saveStudent(Student student) {
     *
     *         student.setCreatedDate(LocalDateTime.now());
     *         student.setModifyDate(student.getCreatedDate());
     *         studentRepository.save(student);
     *     }
     */

    /**
     * Get all student list.
     *
     * @return the list
     */
    public List<Student>  getAllStudent(){
        //System.out.println(studentRepository.findByName("muhsin").getModifyDate());
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

    /**
     * public boolean validateEmail(String email) {
     *         String regex = "^([A-Za-z0-9_.-]+)@([a-z]+)\\\\.([a-z]+)$";
     *         Pattern pattern = Pattern.compile(regex);
     *         Matcher matcher = pattern.matcher(email);
     *         return matcher.matches();
     *     }
     */



    /**public Student studentByName(String name) {
        System.out.println("Hi");
        //List<Student> namelist = studentRepository.findByNameStartingWith("muh");
        //Iterator itr =namelist.iterator();
        //while (itr.hasNext()){
          //  System.out.println(itr.next());
        //}

        return studentRepository.findByName(name);
    }
     **/
}
