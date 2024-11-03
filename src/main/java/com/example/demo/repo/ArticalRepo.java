package com.example.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Artical;
@Repository
public interface ArticalRepo extends  JpaRepository<Artical,Long> {

    Boolean existsByText(String text);

}
