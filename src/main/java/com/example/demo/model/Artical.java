package com.example.demo.model;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Artical {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String text;
    private LocalDate localDate;

    @JsonIgnore
    @OneToMany(mappedBy="artical",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<Image> images;

    @JsonIgnore
    @OneToMany(mappedBy = "artical",cascade=CascadeType.ALL,orphanRemoval= true)
    private List<Comments> commentses;
 
}
