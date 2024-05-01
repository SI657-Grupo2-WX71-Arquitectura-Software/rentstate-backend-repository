package com.rentstate.post_service.api.dto;

import lombok.Data;

@Data
public class CommentResponse {

    private Long id;
    private String content;
    private Long postId;
    private Long userId;
}
