package com.student.ust.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "book_details_mappedby_table")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int bookId;
    private String bookName;
    private String author;
    private long asbi;

    @ManyToOne
    @JoinColumn(name ="student_id")
    private Student student;

}
