package com.javaUdemy.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.javaUdemy.springboot.bean.Student;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("students")
public class StudentController {

	// http://localhost:8080/student
	@GetMapping("student")
	public ResponseEntity<Student> getStudent() {
		Student student = new Student(1, " Himanshu", "vishwakarma");
		// return new ResponseEntity<>(student, HttpStatus.OK);
		return ResponseEntity.ok(student);
	}

	// http://localhost:8080/students
	@GetMapping
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<>();
		students.add(new Student(1, "Himanshu", "vishwakarma"));
		students.add(new Student(2, "varun", "Mishra"));
		students.add(new Student(3, "harish", "tiwari"));
		students.add(new Student(4, "chandan", "yadav"));
		return students;
	}

	// Spring Boot REST API with path variable
	// {id}--URI templete variable
	// http://localhost:8080/students/1/himanshu/vishwakarma

	@GetMapping("{id}/{first-name}/{last-name}")
	public Student studentPathVariable(@PathVariable("id") int studentid, @PathVariable("first-name") String firstName,
			@PathVariable("last-name") String lastName) {
		return new Student(studentid, "fistName", "lastName");
	}

	// Spring boot REST API with request param
	// http://localhost:8080/students/query?id=1

	@GetMapping("query")
	public Student studentRequestVariable(@RequestParam int id) {
		return new Student(id, "himanshu", "vishwakarma");
	}

	// Spring boot REST API that handles HTTP POST request
	// PostMapping and @RequestBody
	
	@PostMapping("create")
	@ResponseStatus(HttpStatus.CREATED)
	public Student createStudent(@RequestBody Student student) {
		System.out.println(student.getId());
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		return student;
	}

	// Spring Boot REST API that handle HTTP PUT Request -updating existing resource

	@PutMapping("{id}/update")
	public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
		System.out.println(student.getFirstName());
		System.out.println(student.getLastName());
		return student;
	}

	// Spring Boot REST API that handle HTTP DELETE Request -deleting the existing
	// resource
	@DeleteMapping("{id}/delete")
	public ResponseEntity <String> deleteStudent(@PathVariable("id") int studentId) {
		System.out.println(studentId);
		return ResponseEntity.ok( "Student deleted sucessfully!");
	}
}