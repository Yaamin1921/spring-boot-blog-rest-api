package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    //@Autowired
    private ModelMapper mapper;    //use to one object to another object...other libraries like mapstruct also avail for same.

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

    @Override
    public List<CommentDto> getCommentByPostId(Long postId,Long commentId) {
        //retrieve comment by postId
        List<Comment> comments;
        if(null!=postId && null!=commentId){
            var post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
            comments=commentRepository.findByPostIdAndId(postId,commentId);
            if(!comments.isEmpty() && (!comments.get(0).getPost().getId().equals(postId))){
                throw  new  BlogAPIException(HttpStatus.BAD_REQUEST,"comment does not belong to given postId");
            }
        }else if(null!=commentId){
            comments=commentRepository.findById(commentId).map(List::of).orElse(List.of());
        }else if(null!=postId){
            var post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
            comments=commentRepository.findByPostId(postId);
        }else{
            comments=commentRepository.findAll();
        }

        var commentsList=comments.stream().map(comment -> mapToDto(comment)).toList();
        if(!commentsList.isEmpty())  //jpa never return null
        return commentsList;
        else return Collections.emptyList();
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        var comment=commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("comment","commentId",commentId));
        if(null!=postId) {
            var post = postRepository.findById(postId).orElseThrow(() ->
                    new ResourceNotFoundException("Post", "id", postId));
            if(!comment.getPost().getId().equals(post.getId())){
                throw new BlogAPIException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
            }
        }

        comment.setName(commentRequest.getName());
        comment.setBody(commentRequest.getBody());
        comment.setEmail(comment.getEmail());

        var updateComment=commentRepository.save(comment);
        return mapToDto(updateComment);
    }

    @Override
    public void deleteComment(Long commentId, Long postId) {

        if(null!=commentId) {
            var comment = commentRepository.findById(commentId).orElseThrow(() ->
                    new ResourceNotFoundException("comment", "commentId", commentId));
            if (null != postId) {
                var post = postRepository.findById(postId).orElseThrow(() ->
                        new ResourceNotFoundException("Post", "id", postId));
                if (!comment.getPost().getId().equals(post.getId())) {
                    throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
                }
            }
            commentRepository.deleteById(commentId);
        }
        else{
            if(null!=postId){
                var post = postRepository.findById(postId).orElseThrow(() ->
                        new ResourceNotFoundException("Post", "id", postId));
                commentRepository.deleteAllByPostId(postId);
            }
              /*else {
            commentRepository.deleteAll();
        }*/
        }
    }

    private CommentDto mapToDto(Comment comment){
        /*return CommentDto.builder()
                .body(comment.getBody())
                .email(comment.getEmail())
                .name(comment.getName())
                .id(comment.getId())
                .build();*/
        var commentDto=mapper.map(comment,CommentDto.class);
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
       /* return Comment.builder()
                .body(commentDto.getBody())
                .email(commentDto.getEmail())
                .name(commentDto.getName())
                .id(commentDto.getId())
                .build();*/
        var comment= mapper.map(commentDto,Comment.class);
        return comment;
    }
}
