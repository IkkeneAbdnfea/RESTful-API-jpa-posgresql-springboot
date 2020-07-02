package com.example.postgredemo.controller;


import com.example.postgredemo.exception.ResourceNotFoundException;
import com.example.postgredemo.model.Voiture;
import com.example.postgredemo.repository.VoitureRepository;
import com.example.postgredemo.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class VoitureController {

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private MarkRepository markRepository;

    @GetMapping("/marks/{markId}/voitures")
    public List<Voiture> getVoituresByQuestionId(@PathVariable Long markId) {
        return voitureRepository.findByMarkId(markId);
    }

    @PostMapping("/marks/{markId}/voitures")
    public Voiture addVoiture(@PathVariable Long markId,
                            @Valid @RequestBody Voiture voiture) {
        return markRepository.findById(markId)
                .map(mark -> {
                    voiture.setMark(mark);
                    return voitureRepository.save(voiture);
                }).orElseThrow(() -> new ResourceNotFoundException("Mark not found with id " + markId));
    }

    @PutMapping("/marks/{markId}/voitures/{voitureId}")
    public Voiture updateVoiture(@PathVariable Long markId,
                               @PathVariable Long voitureId,
                               @Valid @RequestBody Voiture voitureRequest) {
        if(!markRepository.existsById(markId)) {
            throw new ResourceNotFoundException("Mark not found with id " + markId);
        }

        return voitureRepository.findById(voitureId)
                .map(voiture -> {
                    voiture.setDescription(voitureRequest.getDescription());
                    voiture.setMatricule(voitureRequest.getMatricule());
                    return voitureRepository.save(voiture);
                }).orElseThrow(() -> new ResourceNotFoundException("Voiture not found with id " + voitureId));
    }

    @DeleteMapping("/marks/{markId}/voitures/{voitureId}")
    public ResponseEntity<?> deleteVoiture(@PathVariable Long markId,
                                          @PathVariable Long voitureId) {
        if(!markRepository.existsById(markId)) {
            throw new ResourceNotFoundException("Mark not found with id " + markId);
        }

        return voitureRepository.findById(voitureId)
                .map(voiture -> {
                    voitureRepository.delete(voiture);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Voiture not found with id " + voitureId));

    }
}