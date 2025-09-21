package com.springbloog.blog.service;

import com.springbloog.blog.entity.Post;
import com.springbloog.blog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto postDto,Long id);
    void deletePostById(Long id);

}
