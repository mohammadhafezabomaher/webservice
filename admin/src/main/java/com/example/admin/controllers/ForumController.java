package com.example.admin.controllers;

import com.example.admin.entities.Forum;
import com.example.admin.services.IForumService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/forum")
public class ForumController {
    IForumService forumService;
    @PostMapping("/add")
    public ResponseEntity<Forum> addForum(@RequestBody Forum f){

        Forum fo = forumService.addForum(f);
        return  ResponseEntity.ok(fo);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Forum> getForumById(@PathVariable("id") Long id){
        Forum f= forumService.getForumById(id);
        return  ResponseEntity.ok(f);
    }
    @GetMapping("/getall")
    public ResponseEntity<List<Forum>>getAllForum(){

        List<Forum> forums=  forumService.getAllForum();
        return ResponseEntity.ok(forums);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteForum(@PathVariable("id") Long id){
        forumService.deleteForum(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update")
    public ResponseEntity<Forum> updateForum(@RequestBody Forum f){

        Forum fo= forumService.updateForum(f);
        return  ResponseEntity.ok(fo);
    }
}
