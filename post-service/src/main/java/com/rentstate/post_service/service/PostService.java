package com.rentstate.post_service.service;

import com.rentstate.post_service.api.dto.PostRequest;
import com.rentstate.post_service.api.dto.PostResponse;
import com.rentstate.post_service.domain.model.entity.Post;

import java.util.List;

public interface PostService {

    PostResponse createPost(PostRequest postRequest);
    PostResponse getPost(Long id);

    List<PostResponse> getAllPosts();
    PostResponse updatePost(Long id, PostRequest postRequest);
    void deletePost(Long id);
}
