package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Image;
@Repository
public interface ImageRepo extends JpaRepository<Image,Long> {

    public Boolean existsByName(String name);

}
