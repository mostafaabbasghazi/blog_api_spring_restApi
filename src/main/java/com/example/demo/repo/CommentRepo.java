package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Artical;
import com.example.demo.model.Comments;
@Repository
public interface CommentRepo extends JpaRepository<Comments, Long>{

    List<Comments> findByArtical(Artical articalId);

}
