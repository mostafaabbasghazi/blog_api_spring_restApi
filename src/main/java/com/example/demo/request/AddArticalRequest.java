package com.example.demo.request;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.Image;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class AddArticalRequest {
    private String text;
}
