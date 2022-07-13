package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.model.Student;;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class StudentController {
	@Autowired
    private StudentRepository studentRepository;

	@Autowired
    private CourseRepository courseRepository;
	
	@RequestMapping("/login")
	public String login() {
    	return "login";
    }	
	
	@RequestMapping("/students")
	public String index(Model model) {
		List<Student> students = (List<Student>) studentRepository.findAll();
		model.addAttribute("students", students);
    	return "students";
    }

    @RequestMapping(value = "add")
    public String addStudent(Model model){
    	model.addAttribute("student", new Student());
        return "addStudent";
    }	

    @RequestMapping(value = "/edit/{id}")
    public String editStudent(@PathVariable("id") Long studentId, Model model){
    	model.addAttribute("student", studentRepository.findById(studentId));
        return "editStudent";
    }	    
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Student student){
		studentRepository.save(student);
    	return "redirect:/students";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long studentId, Model model) {
		studentRepository.deleteById(studentId);
        return "redirect:/students";
    }    
    
    @RequestMapping(value = "addStudentCourse/{id}", method = RequestMethod.GET)
    public String addCourse(@PathVariable("id") Long studentId, Model model){

    		model.addAttribute("courses", courseRepository.findAll());
    		model.addAttribute("student", studentRepository.findById(studentId).get());
    		return "addStudentCourse";
    }
    
    
    @RequestMapping(value="/student/{id}/courses", method=RequestMethod.GET)
	public String studentsAddCourse(@RequestParam(value="action", required=true) String action, @PathVariable Long id, @RequestParam Long courseId, Model model) {
    	Optional<Course> course = courseRepository.findById(courseId);
		Optional<Student> student = studentRepository.findById(id);

		if (student.isPresent() && action.equalsIgnoreCase("save")) {
			if (!student.get().hasCourse(course.get())) {
				student.get().getCourses().add(course.get());
			}
			studentRepository.save(student.get());
			model.addAttribute("student", courseRepository.findById(id));
			model.addAttribute("courses", courseRepository.findAll());
			return "redirect:/students";
		}

		model.addAttribute("developers", studentRepository.findAll());
		return "redirect:/students";
	}    
    
    @RequestMapping(value = "getstudents", method = RequestMethod.GET)
    public @ResponseBody List<Student> getStudents() {
            return (List<Student>)studentRepository.findAll();
    }      
}
