package com.example.demo.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private Long id;

    private String comment;

    private LocalDate localDate;

    @ManyToOne
    @JoinColumn(name = "artical_id")
    @JsonIgnore
    private Artical artical;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;
}
