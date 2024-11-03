package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.AddCommentRequest;
import com.example.demo.service.comment.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add/{userId}/{articalId}")
    public ResponseEntity addComment(@RequestBody AddCommentRequest request,
    @PathVariable Long userId,@PathVariable Long articalId){
        try {
            commentService.addComment(request, userId, articalId);
            return ResponseEntity.ok("succes");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("faid");
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Long id){
        try {
            commentService.deleteComment(id);
            return ResponseEntity.ok("succes");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }

    }

    @GetMapping("/get/{id}")
    public ResponseEntity getAllComment(@PathVariable Long id){
        try {
            return ResponseEntity.ok(commentService.getCommentsForArtical(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
    }
    
}
