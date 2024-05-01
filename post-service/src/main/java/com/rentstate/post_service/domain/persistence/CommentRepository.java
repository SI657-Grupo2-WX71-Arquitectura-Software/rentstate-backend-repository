package com.rentstate.post_service.domain.persistence;

import com.rentstate.post_service.domain.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
