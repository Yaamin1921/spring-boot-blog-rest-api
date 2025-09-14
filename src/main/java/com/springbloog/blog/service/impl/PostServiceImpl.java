package com.springbloog.blog.service.impl;

import com.springbloog.blog.entity.Post;
import com.springbloog.blog.payload.PostDto;
import com.springbloog.blog.repository.PostRepository;
import com.springbloog.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post response=postRepository.save(toPost(postDto));
        return toPostDto(response);
    }

    @Override
    public List<PostDto> getAllPosts() {

        var posts=postRepository.findAll();
        List<PostDto> postDtoList=posts.stream().map(PostServiceImpl::toPostDto).toList();
        return postDtoList;
    }


    private Post toPost(PostDto postDto){
        return Post.builder()
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .content(postDto.getContent())
                .build();
    }
    private static PostDto toPostDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .content(post.getContent())
                .build();
    }

}
