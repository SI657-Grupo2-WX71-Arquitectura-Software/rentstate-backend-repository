package com.rentstate.post_service.service.impl;

import com.rentstate.post_service.api.dto.CommentRequest;
import com.rentstate.post_service.api.dto.CommentResponse;
import com.rentstate.post_service.domain.model.entity.Comment;
import com.rentstate.post_service.domain.persistence.CommentRepository;
import com.rentstate.post_service.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentResponse createComment(CommentRequest commentRequestDTO) {
        Comment comment = new Comment();
        comment.setContent(commentRequestDTO.getContent());
        comment.setPostId(commentRequestDTO.getPostId());
        comment.setUserId(commentRequestDTO.getUserId());
        comment = commentRepository.save(comment);
        return convertToCommentResponseDTO(comment);
    }

    @Override
    public CommentResponse getComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.map(this::convertToCommentResponseDTO).orElse(null);
    }

    @Override
    public CommentResponse updateComment(Long id, CommentRequest commentRequestDTO) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            comment.setContent(commentRequestDTO.getContent());
            comment.setPostId(commentRequestDTO.getPostId());
            comment.setUserId(commentRequestDTO.getUserId());
            comment = commentRepository.save(comment);
            return convertToCommentResponseDTO(comment);
        }
        return null;
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    private CommentResponse convertToCommentResponseDTO(Comment comment) {
        CommentResponse commentResponseDTO = new CommentResponse();
        commentResponseDTO.setId(comment.getId());
        commentResponseDTO.setContent(comment.getContent());
        commentResponseDTO.setPostId(comment.getPostId());
        commentResponseDTO.setUserId(comment.getUserId());
        return commentResponseDTO;
    }
}
