package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    /*public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository=commentRepository;
    }*/

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment=mapToEntity(commentDto);

        //retrieve post entity by id
        var post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
        //set post to comment
        comment.setPost(post);
        Comment newComment=commentRepository.save(comment);

        return mapToDto(newComment);
    }



    private CommentDto mapToDto(Comment comment){
        return CommentDto.builder()
                .body(comment.getBody())
                .email(comment.getEmail())
                .name(comment.getName())
                .id(comment.getId())
                .build();
    }
    private Comment mapToEntity(CommentDto commentDto){
        return Comment.builder()
                .body(commentDto.getBody())
                .email(commentDto.getEmail())
                .name(commentDto.getName())
                .id(commentDto.getId())
                .build();
    }
}
