package com.example.demo.service.comment;

import java.util.List;

import com.example.demo.model.Comments;
import com.example.demo.request.AddCommentRequest;

public interface CommentServiceI {
    Comments addComment(AddCommentRequest request ,Long userId,Long articalId);
    void deleteComment(Long id);
    List<Comments> getCommentsForArtical(Long articalId);
}
