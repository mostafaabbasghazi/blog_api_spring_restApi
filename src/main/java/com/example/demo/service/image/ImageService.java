package com.example.demo.service.image;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Image;
import com.example.demo.repo.ArticalRepo;
import com.example.demo.repo.ImageRepo;
import com.example.demo.service.artical.AricalService;

@Service
public class ImageService implements ImageServiceI{

    @Autowired
    private ImageRepo imageRepo;
    @Autowired
    private AricalService aricalService;

    @Override
    public Image addImage(MultipartFile file,Long articalId) {
        return Optional.of(file).filter(f-> !imageRepo.existsByName(f.getOriginalFilename()) )
        .map(c->{
            Image image =new Image();
            image.setName(c.getOriginalFilename());
            image.setFileType(c.getContentType());
            try {
                image.setImage(new SerialBlob(c.getBytes()));
            } catch (SerialException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            image.setArtical(aricalService.getArticalById(articalId));
            image.setLocalDate(LocalDate.now());

            return imageRepo.save(image);
        }).orElseThrow(()-> new RuntimeException("erorr faild"));
    }

    @Override
    public void updateImage(MultipartFile file, Long id) {
        Image image =getImageById(id);
        try {
            image.setName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepo.save(image);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void deleteImage(Long id) {
        // TODO Auto-generated method stub
        imageRepo.deleteById(id);
    }

    @Override
    public Image getImageById(Long id) {
        return imageRepo.findById(id).orElseThrow(()-> new RuntimeException("Not Found"));
    }

}
