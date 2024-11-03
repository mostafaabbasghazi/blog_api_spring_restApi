package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.AddArticalRequest;
import com.example.demo.service.artical.AricalService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/art")
public class ArticalController {
    @Autowired
    private final AricalService articalService;

    @GetMapping("/all")
    public ResponseEntity getAllArticals(){
        try {
            return ResponseEntity.ok(articalService.articals());
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Faild");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getArticalById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(articalService.getArticalById(id));
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }

    @PostMapping("/add")
    public ResponseEntity addArtical(@RequestBody AddArticalRequest request){
        try {
            return ResponseEntity.ok(articalService.addArtical(request));
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Faild");
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateArtical(@RequestBody AddArticalRequest request,@PathParam("id") Long id){
        try {
            return ResponseEntity.ok(articalService.updateArical(request, id));
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("artical not found");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteArtical(@PathParam("id") Long id){
        try {
            articalService.deleteArical(id);
            return ResponseEntity.ok("ok");
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }

}
