package com.example.demo.service.artical;

import java.util.List;

import com.example.demo.model.Artical;
import com.example.demo.request.AddArticalRequest;

public interface ArticalServiceI {
    Artical addArtical(AddArticalRequest request);
    void deleteArical(Long id);
    Artical updateArical(AddArticalRequest request,Long id);
    List<Artical> articals();
    Artical getArticalById(Long id);
}
