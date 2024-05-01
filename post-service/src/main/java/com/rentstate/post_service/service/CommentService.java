package com.rentstate.post_service.service;

import com.rentstate.post_service.api.dto.CommentRequest;
import com.rentstate.post_service.api.dto.CommentResponse;

public interface CommentService {
    CommentResponse createComment(CommentRequest commentRequestDTO);
    CommentResponse getComment(Long id);
    CommentResponse updateComment(Long id, CommentRequest commentRequestDTO);
    void deleteComment(Long id);

}
