package com.example.demo.service.image;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Image;

public interface ImageServiceI {

    Image addImage(MultipartFile file ,Long articalId);
    void updateImage(MultipartFile file,Long id);
    void deleteImage(Long id);
    Image getImageById(Long id);
}
