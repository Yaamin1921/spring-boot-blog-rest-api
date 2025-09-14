package com.springbloog.blog.repository;

import com.springbloog.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//we don't need to add @ Repository annotation because it implements simpleRepositoyr which already have this annotation
public interface PostRepository extends JpaRepository<Post,Long> {
    Post save(Post post);
    List<Post> findAll();
}
