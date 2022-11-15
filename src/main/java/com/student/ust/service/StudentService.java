package com.student.ust.service;

import com.student.ust.dto.StudentDto;
import com.student.ust.entity.Student;
import com.student.ust.exception.BuisnessException;
import com.student.ust.exception.InvalidEmailException;
import com.student.ust.exception.InvalidPasswordException;
import com.student.ust.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import static com.student.ust.Util.USTUtil.*;

/**
 * The type Student service.
 */
@Service
@Slf4j
public class StudentService {


    /**
     * The Student repository.
     */
    @Autowired
    StudentRepository studentRepository;

    /**
     * The Model mapper.
     */
    @Autowired
    ModelMapper modelMapper;

    /**
     * View student details.
     *
     * @param student the student
     */
    public void viewStudentDetails(Student student){

    }

    /**
     * Gets student by id.
     *
     * @param id the id
     * @return the student by id
     * @throws NoSuchElementException the no such element exception
     */
    public Student getStudentById(Integer id) throws NoSuchElementException {
        log.debug("Student Details"+studentRepository.findById(id));
        return studentRepository.findById(id).orElseThrow(()-> new NoSuchElementException ());
    }

    /**
     * Entity to dto converter student dto.
     *
     * @param student the student
     * @return the student dto
     */
    public StudentDto entityToDtoConverter(Student student){
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        return studentDto;
    }

    /**
     * Entity to dto list converter list.
     *
     * @param studentList the student list
     * @return the list
     */
    public List<StudentDto> entityToDtoListConverter(List<Student> studentList){
        List<StudentDto> studentDtoList = modelMapper.map(studentList,new TypeToken<List<StudentDto>>(){}.getType());
        return studentDtoList;
    }


    /**
     * Save student.
     *
     * @param student the student
     * @throws BuisnessException the buisness exception
     */
    public void saveStudent(Student student) throws BuisnessException {

        boolean validEmail = validateEmail(student);
        boolean validPassword = validatePassword(student);

        if (validEmail && validPassword) {
            student.setCreatedDate(LocalDateTime.now());
            student.setModifyDate(student.getCreatedDate());
            String password = student.getPassword();
            student.setPassword(hashPassword(password));
            studentRepository.save(student);
        } else if (!validEmail) {
            throw new InvalidEmailException();
        } else {
            throw new InvalidPasswordException();
        }
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

    /**
     * Update student student.
     *
     * @param student the student
     * @return the student
     */
    public Student updateStudent(Student student){
        Student updateStudent = studentRepository.findById(student.getStudentId()).orElseThrow(() -> new NoSuchElementException());
        updateStudent.setAge(student.getAge());
        updateStudent.setRollNo(student.getRollNo());
        updateStudent.setModifyDate(LocalDateTime.now());
        studentRepository.save(updateStudent);
        return updateStudent;
    }
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


     String email = student.getEmail();
     String regexEmail = "^([A-Za-z0-9_.-]+)@([a-z]+)\\.([a-z]+)$";
     Pattern pattern = Pattern.compile(regexEmail);
     Matcher matcher = pattern.matcher(email);

     String password = student.getPassword();
     String regexPassword =  "^(?=(?:.*\\d){3,})(?=\\S+$)(?=.*[@#$%^&+=])(?=(?:.*[A-Za-z]){3,})(?=.*[A-Z]).{8,}$";
     Pattern pattern1 = Pattern.compile(regexPassword);
     Matcher matcher1 = pattern1.matcher(password);



     BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
     String encodedpassword = bCryptPasswordEncoder.encode(student.getPassword());





     if (matcher.matches()){
     if(matcher1.matches()) {
     student.setPassword(hashPassword(password));
     studentRepository.save(student);
     }
     else {
     throw new InvalidPasswordException();
     }
     }
     else if (matcher1.matches()) {
     if (matcher.matches()) {
     student.setPassword(hashPassword(password));
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
     **/



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

