package com.viktor.backend.controller;

import com.viktor.backend.exception.*;
import com.viktor.backend.model.*;
import com.viktor.backend.repository.*;

import java.util.Date;
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
public class AdvertController {
	@Autowired
	private AdvertRepository advertRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping("/adverts")
	public List<Advert> getAllAdverts() {
        return advertRepository.findAll();
    }
	
	@GetMapping("/adverts/{id}")
	public ResponseEntity<Advert> getAdvertById(@PathVariable(value = "id") Long advertId)
			throws ResourceNotFoundException {
		Advert advert = advertRepository.findById(advertId)
				.orElseThrow(() -> new ResourceNotFoundException("Advert not found for this id: " + advertId));
				return ResponseEntity.ok().body(advert);
	}

    @PostMapping("/adverts/{userId}/{courseId}")
    public Advert createAdvert(@PathVariable(value = "userId") Long userId,
                               @PathVariable(value = "courseId") Long courseId,
                               @Valid @RequestBody Advert advert)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + userId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for this id: " + courseId));
        advert.setUser(user);
        advert.setCourse(course);
        return advertRepository.save(advert);
    }
	
	@PutMapping("/adverts/{id}")
	public ResponseEntity<Advert> updateAdvert(@PathVariable(value = "id") Long advertId,
			@Valid @RequestBody Advert advertDetails) throws ResourceNotFoundException {
		Advert advert = advertRepository.findById(advertId)
				.orElseThrow(() -> new ResourceNotFoundException("Advert not found for this id: " + advertId));
		
		advert.setTitle(advertDetails.getTitle());
		advert.setDescription(advertDetails.getDescription());
		advert.setPricePerUnit(advertDetails.getPricePerUnit());
		advert.setUnitOfMeasure(advertDetails.getUnitOfMeasure());
		advert.setDatePosted(advertDetails.getDatePosted());
	
		final Advert updatedAdvert = advertRepository.save(advert);
		return ResponseEntity.ok(updatedAdvert);
	}
	
	@DeleteMapping("/adverts/{id}")
	public Map<String, Boolean> deleteAdvert(@PathVariable(value = "id") Long advertId)
    		throws ResourceNotFoundException {
        Advert advert = advertRepository.findById(advertId)
       .orElseThrow(() -> new ResourceNotFoundException("Advert not found for this id :: " + advertId));

        advertRepository.delete(advert);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}