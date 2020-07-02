package com.example.postgredemo.repository;

import com.example.postgredemo.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {
    List<Voiture> findByMarkId(Long markId);
}
