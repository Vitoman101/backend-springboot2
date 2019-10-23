package com.viktor.backend.controller;

import com.viktor.backend.exception.*;
import com.viktor.backend.model.*;
import com.viktor.backend.repository.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@RequestMapping(path = "api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class SectionController {
	@Autowired
	private SectionRepository sectionRepository;
	
	@GetMapping("/sections")
	public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }
	
	@GetMapping("/sections/{id}")
	public ResponseEntity<Section> getSectionById(@PathVariable(value = "id") Long sectionId)
			throws ResourceNotFoundException {
		Section section = sectionRepository.findById(sectionId)
				.orElseThrow(() -> new ResourceNotFoundException("Section not found for this id: " + sectionId));
				return ResponseEntity.ok().body(section);
	}
	
	@PostMapping("/sections")
	public Section createSection(@Valid @RequestBody Section section) {
		return sectionRepository.save(section);
	}
	
	@PutMapping("/sections/{id}")
	public ResponseEntity<Section> updateSection(@PathVariable(value = "id") Long sectionId,
			@Valid @RequestBody Section sectionDetails) throws ResourceNotFoundException {
		Section section = sectionRepository.findById(sectionId)
				.orElseThrow(() -> new ResourceNotFoundException("Section not found for this id: " + sectionId));
		
		section.setTitle(sectionDetails.getTitle());
		section.setDescription(sectionDetails.getDescription());
		
		final Section updatedSection = sectionRepository.save(section);
		return ResponseEntity.ok(updatedSection);
	}
	
	@DeleteMapping("/sections/{id}")
	public Map<String, Boolean> deleteSection(@PathVariable(value = "id") Long sectionId)
    		throws ResourceNotFoundException {
        Section section = sectionRepository.findById(sectionId)
       .orElseThrow(() -> new ResourceNotFoundException("Section not found for this id :: " + sectionId));

        sectionRepository.delete(section);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}