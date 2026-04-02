package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

   @Autowired
    private PostRepository postRepository;
   @Autowired
    private ModelMapper mapper;
 /*   @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }*/

    @Override
    public PostDto createPost(PostDto postDto) {
        Post response=postRepository.save(toPost(postDto));
        return toPostDto(response);
    }

    @Override
    public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {

        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();


        Pageable pageable= PageRequest.of(pageNo,pageSize, sort);
        var postDto=postRepository.findAll(pageable);
        var posts=postDto.getContent();
        List<PostDto> content=posts.stream().map(post->toPostDto(post)).toList();

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(postDto.getNumber());
        postResponse.setPageSize(postDto.getSize());
        postResponse.setTotalPages(postDto.getTotalPages());
        postResponse.setTotalElements(postDto.getTotalElements());
        postResponse.setLast(postDto.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {

        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        return toPostDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto,Long id) {
     Post post= postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());

        Post updatedPost= postRepository.save(post);
        return toPostDto(updatedPost);
    }

    @Override
    public void deletePostById(Long id) {
        Post post =postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        postRepository.deleteById(id);
    }

    private Post toPost(PostDto postDto){
       /* return Post.builder()
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .content(postDto.getContent())
                .build();*/
        Post post=mapper.map(postDto,Post.class);
        return  post;
    }
    private  PostDto toPostDto(Post post){
       /* return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .content(post.getContent())
                .build();
                */
                 PostDto postDto=mapper.map(post,PostDto.class);
                 return postDto;
    }


}
