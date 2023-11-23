// PostService.java
package com.lfa.lfa.service;

import com.lfa.lfa.domain.Post;
import com.lfa.lfa.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }

    public List<Post> getPostsByCategoryId(Long categoryId) {
        return postRepository.findByCategoryId(categoryId);
    }

    public Post addPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, Post updatedPost) {
        updatedPost.setId(postId);
        updatedPost.setCreatedAt(LocalDateTime.now());
        return postRepository.save(updatedPost);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
