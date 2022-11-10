package com.student.ust.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The type Student.
 */
@Entity
@Data
@Table(name = "student_details_mappedby_table")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int studentId;
    private int age;
    private long rollNo;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime modifyDate;



    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "student")
    //@JoinColumn(name = "book_id")
    private Set<Book> bookSet;
}
