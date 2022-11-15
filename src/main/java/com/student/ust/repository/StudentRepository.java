package com.student.ust.repository;

import com.student.ust.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.zip.ZipFile;

/**
 * The interface Student repository.
 */
@Repository
public interface StudentRepository extends
        JpaRepository<Student,Integer> {
   

    //List<Student> findByNameStartingWith(String muh);

    /**
     * Find by name student.
     *
     * @param name the name
     * @return the student
     */
    Student findByName(String name);


}
