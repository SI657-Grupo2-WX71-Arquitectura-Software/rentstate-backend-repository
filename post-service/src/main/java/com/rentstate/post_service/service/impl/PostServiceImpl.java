package com.rentstate.post_service.service.impl;

import com.rentstate.post_service.api.dto.PostRequest;
import com.rentstate.post_service.api.dto.PostResponse;
import com.rentstate.post_service.domain.model.entity.Post;
import com.rentstate.post_service.domain.persistence.PostRepository;
import com.rentstate.post_service.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostResponse createPost(PostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setPrice(postRequest.getPrice());
        post.setPropertyId(postRequest.getPropertyId());
        post.setUserId(postRequest.getUserId());
        post = postRepository.save(post);
        return convertToPostResponse(post);
    }

    @Override
    public PostResponse getPost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.map(this::convertToPostResponse).orElse(null);
    }

    @Override
    public PostResponse updatePost(Long id, PostRequest postRequest) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setTitle(postRequest.getTitle());
            post.setContent(postRequest.getContent());
            post.setPrice(postRequest.getPrice());
            post.setPropertyId(postRequest.getPropertyId());
            post.setUserId(postRequest.getUserId());
            post = postRepository.save(post);
            return convertToPostResponse(post);
        }
        return null;
    }
    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
    @Override
    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::convertToPostResponse)
                .collect(Collectors.toList());
    }

    private PostResponse convertToPostResponse(Post post) {
        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setTitle(post.getTitle());
        postResponse.setContent(post.getContent());
        postResponse.setPrice(post.getPrice());
        postResponse.setPropertyId(post.getPropertyId());
        postResponse.setUserId(post.getUserId());
        return postResponse;
    }
}
