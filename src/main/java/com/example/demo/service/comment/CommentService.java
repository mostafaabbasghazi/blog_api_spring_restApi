package com.example.demo.service.comment;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Artical;
import com.example.demo.model.Comments;
import com.example.demo.repo.ArticalRepo;
import com.example.demo.repo.CommentRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.request.AddCommentRequest;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class CommentService implements CommentServiceI {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ArticalRepo articalRepo;

    @Override
    public Comments addComment(AddCommentRequest request, Long userId, Long articalId) {
        if(request.getComment() != null){
            Comments comments= new Comments();
            comments.setComment(request.getComment());
            comments.setArtical(articalRepo.findById(articalId).get());
            comments.setUser(userRepo.findById(userId).get());
            comments.setLocalDate(LocalDate.now());
            return commentRepo.save(comments);
        }else{
            return null;
        }
    }

    @Override
    public void deleteComment(Long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public List<Comments> getCommentsForArtical(Long articalId) {
        Artical artical=articalRepo.findById(articalId).get();
       List<Comments> comments= commentRepo.findByArtical(artical);
       if(comments != null){
        return comments;
       }else{
        return null;
       }
    }

}
