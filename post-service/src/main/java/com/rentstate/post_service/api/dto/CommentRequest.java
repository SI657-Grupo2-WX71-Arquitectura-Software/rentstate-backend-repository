package com.rentstate.post_service.api.dto;

import lombok.Data;

@Data
public class CommentRequest {
    private String content;
    private Long postId;
    private Long userId;
}
