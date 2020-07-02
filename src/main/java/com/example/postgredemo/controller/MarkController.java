package com.example.postgredemo.controller;

import com.example.postgredemo.exception.ResourceNotFoundException;
import com.example.postgredemo.model.Mark;
import com.example.postgredemo.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class MarkController {

    @Autowired
    private MarkRepository markRepository;

    @GetMapping("/marks")
    public Page<Mark> getMarks(Pageable pageable) {
        return markRepository.findAll(pageable);
    }


    @PostMapping("/marks")
    public Mark createMark(@Valid @RequestBody Mark question) {
        return markRepository.save(question);
    }

    @PutMapping("/marks/{markId}")
    public Mark updateMark(@PathVariable Long markId,
                                   @Valid @RequestBody Mark markRequest) {
        return markRepository.findById(markId)
                .map(mark -> {
                    mark.setName(markRequest.getName());
                    mark.setDescription(markRequest.getDescription());
                    return markRepository.save(mark);
                }).orElseThrow(() -> new ResourceNotFoundException("Mark not found with id " + markId));
    }

    @DeleteMapping("/marks/{markId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long markId) {

        return markRepository.findById(markId)
                .map(question -> {
                    markRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Mark not found with id " + markId));

    }
}