package com.rentstate.post_service.api.rest;

import com.rentstate.post_service.api.dto.CommentRequest;
import com.rentstate.post_service.api.dto.CommentResponse;
import com.rentstate.post_service.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value ="/api/v1/comments", produces = "application/json")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest commentRequestDTO) {
        CommentResponse commentResponse = commentService.createComment(commentRequestDTO);
        return ResponseEntity.ok(commentResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable Long id) {
        CommentResponse commentResponse = commentService.getComment(id);
        return ResponseEntity.ok(commentResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long id, @RequestBody CommentRequest commentRequestDTO) {
        CommentResponse commentResponse = commentService.updateComment(id, commentRequestDTO);
        return ResponseEntity.ok(commentResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}