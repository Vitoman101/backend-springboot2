package com.viktor.backend.controller;

import com.viktor.backend.exception.*;
import com.viktor.backend.model.*;
import com.viktor.backend.repository.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CourseController {
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private SectionRepository sectionRepository;
	
	@GetMapping("/courses")
	public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
	
	@GetMapping("/courses/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable(value = "id") Long courseId)
			throws ResourceNotFoundException {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id: " + courseId));
				return ResponseEntity.ok().body(course);
	}
	
	@PostMapping("/courses/{sectionId}")
	public Course createCourse(@PathVariable(value = "sectionId") Long sectionId,
								@Valid @RequestBody Course course) 
		throws ResourceNotFoundException {
		Section section = sectionRepository.findById(sectionId)
				.orElseThrow(() -> new ResourceNotFoundException("Section not found for this id: " + sectionId));
		course.setSection(section);
		return courseRepository.save(course);
	}
	
	@PutMapping("/courses/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable(value = "id") Long courseId,
			@Valid @RequestBody Course courseDetails) throws ResourceNotFoundException {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id: " + courseId));
		
		course.setTitle(courseDetails.getTitle());
		course.setCode(courseDetails.getCode());
		course.setSection(courseDetails.getSection());
		
	
		final Course updatedCourse = courseRepository.save(course);
		return ResponseEntity.ok(updatedCourse);
	}
	
	@DeleteMapping("/courses/{id}")
	public Map<String, Boolean> deleteCourse(@PathVariable(value = "id") Long courseId)
    		throws ResourceNotFoundException {
        Course course = courseRepository.findById(courseId)
       .orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + courseId));

        courseRepository.delete(course);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}