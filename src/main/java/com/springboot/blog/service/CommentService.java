package com.springboot.blog.service;

import com.springboot.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long id,CommentDto commentDto);
    List<CommentDto> getCommentByPostId(Long postId,Long commentId);
}
