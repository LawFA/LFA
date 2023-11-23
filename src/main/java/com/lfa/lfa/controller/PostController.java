// PostController.java
package com.lfa.lfa.controller;

import com.lfa.lfa.domain.Post;
import com.lfa.lfa.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public Optional<Post> getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @GetMapping("/category/{categoryId}")
    public List<Post> getPostsByCategoryId(@PathVariable Long categoryId) {
        return postService.getPostsByCategoryId(categoryId);
    }

    @PostMapping
    public Post addPost(@RequestBody Post post) {
        return postService.addPost(post);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody Post updatedPost) {
        return postService.updatePost(postId, updatedPost);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}