package com.example.postgredemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "voiture")
public class Voiture extends AuditModel {
    @Id
    @GeneratedValue(generator = "voiture_generator")
    @SequenceGenerator(
            name = "voiture_generator",
            sequenceName = "voiture_sequence",
            initialValue = 1
    )
    private Long id;

    @Column(columnDefinition = "text")
    private String matricule;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mark_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Mark mark;

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Mark getMark() {
        return mark;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}