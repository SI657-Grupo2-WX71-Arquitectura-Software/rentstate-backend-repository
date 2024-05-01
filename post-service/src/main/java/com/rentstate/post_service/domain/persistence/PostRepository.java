package com.rentstate.post_service.domain.persistence;

import com.rentstate.post_service.domain.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{

}
