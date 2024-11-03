package com.example.demo.service.artical;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Artical;
import com.example.demo.repo.ArticalRepo;
import com.example.demo.request.AddArticalRequest;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class AricalService implements ArticalServiceI{

    @Autowired
    private ArticalRepo aricalRepo;

    @Override
    public Artical addArtical(AddArticalRequest request) {
       if(request.getText() != null){
       return  Optional.of(request).filter(c-> !aricalRepo.existsByText(c.getText()))
        .map(c->{
            Artical artical=new Artical();
            artical.setText(c.getText());
            artical.setLocalDate(LocalDate.now());
            return aricalRepo.save(artical);
        }).orElseThrow(()-> new RuntimeException("This artical is exists"));
       }else{
        return null;
       }
    }

    @Override
    public void deleteArical(Long id) {
        // TODO Auto-generated method stub
        aricalRepo.findById(id).ifPresentOrElse(aricalRepo::delete, ()-> new RuntimeException("Arical not found"));
    }

    @Override
    public Artical updateArical(AddArticalRequest request,Long id) {
        return Optional.ofNullable(getArticalById(id))
        .map(old->{
            old.setText(request.getText());
            old.setLocalDate(LocalDate.now());
            return aricalRepo.save(old);
        }).orElseThrow(()-> new RuntimeException(" Artical Not found"));
    }

    @Override
    public List<Artical> articals() {
        // TODO Auto-generated method stub
        return aricalRepo.findAll();
    }

    @Override
    public Artical getArticalById(Long id) {
        // TODO Auto-generated method stub
        return aricalRepo.findById(id).get();
    }

}
