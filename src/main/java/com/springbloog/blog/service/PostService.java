package com.springbloog.blog.service;

import com.springbloog.blog.entity.Post;
import com.springbloog.blog.payload.PostDto;
import com.springbloog.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto postDto,Long id);
    void deletePostById(Long id);

}
