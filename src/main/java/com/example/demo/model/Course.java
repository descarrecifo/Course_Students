package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)	
	private long courseid;
	
    @Column(name="coursename")
	private String name; 
     
    @ManyToMany(mappedBy = "courses")    
    private Set<Student> students;

    public Course(String name) {
        this.name = name;
    }
}
