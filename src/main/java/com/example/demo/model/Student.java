package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "department")
	private String department;

	@Column(name = "email")
	private String email;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "student_course", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "courseid") })
	private Set<Course> courses = new HashSet<Course>(0);    

	public Student(String firstName, String lastName, String department, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.email = email;
	}
	public boolean hasCourse(Course course) {
		for (Course studentCourse: getCourses()) {
			if (studentCourse.getCourseid() == course.getCourseid()) {
				return true;
			}
		}
		return false;
	}	
}
