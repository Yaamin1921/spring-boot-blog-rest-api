package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentResource {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") Long id,
                                          @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(id, commentDto), HttpStatus.CREATED);
    }
    @GetMapping("post/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getComments(
            @PathVariable(name = "postId",required = true) Long postId){
      return new ResponseEntity<>(commentService.getCommentByPostId(postId),HttpStatus.OK);
    }
}
