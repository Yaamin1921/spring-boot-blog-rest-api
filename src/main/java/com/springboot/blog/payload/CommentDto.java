package com.springboot.blog.payload;

import com.springboot.blog.entity.Post;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private long id;
    private String name;
    private String email;
    private String body;
   // private Post post;
}
