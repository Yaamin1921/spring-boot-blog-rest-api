package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "myBlog",name = "Posts",
uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name="content",nullable = false)
    private String content;

    @OneToMany(mappedBy = "post",cascade=CascadeType.ALL,orphanRemoval = true)
    private Set<Comment> comments=new HashSet<>();


    /*   imp: Comment is owner side..
    * One to many relationship:One parent → Many children:
    * mappedBy:The relationship is managed by the post field in the Comment class:
    * cascade = CascadeType.ALL:Apply all operations (persist, merge, remove, etc.) from parent → child
    * orphanRemoval = true:If a child is removed from the collection → delete it from DB
    * post.getComments().remove(comment); the comment will deleted from db
    * without orphan removal Comment remains in DB (just loses association*/

}
