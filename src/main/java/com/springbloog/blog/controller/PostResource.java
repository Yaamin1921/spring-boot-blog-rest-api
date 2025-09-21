package com.springbloog.blog.controller;

import com.springbloog.blog.entity.Post;
import com.springbloog.blog.payload.PostDto;
import com.springbloog.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostResource {
    //constructor based dependency injection
    private PostService postService;  //here we are making loose coupling by adding interface (not tight coupling via adding class)
    @Autowired    //if class is spring bean and have only   constructor then can use @Autowire
    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    private ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<List<PostDto>> dummyCheck(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    private ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @PutMapping("/{id}")
    private ResponseEntity<PostDto> updatePostId(@RequestBody PostDto postDto,@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.updatePost(postDto,id));
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<String> deletePostById(@PathVariable(name="id") Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post deleted successfully",HttpStatus.OK);
    }

}
