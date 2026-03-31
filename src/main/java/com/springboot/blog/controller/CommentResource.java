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

    //api to get comments by postId,commentId, commentId and postId  and all comments.
    @GetMapping("/post/comments")
    public ResponseEntity<List<CommentDto>> getComments(
            @RequestParam(name = "postId",required = false) Long postId,
            @RequestParam(name ="commentId",required = false) Long commentId){
      return new ResponseEntity<>(commentService.getCommentByPostId(postId,commentId),HttpStatus.OK);
    }
}
