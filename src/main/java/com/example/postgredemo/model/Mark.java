package com.example.postgredemo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "mark")
public class Mark extends AuditModel {
    @Id
    @GeneratedValue(generator = "mark_generator")
    @SequenceGenerator(
            name = "mark_generator",
            sequenceName = "mark_sequence",
            initialValue = 1
    )
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}