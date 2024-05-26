package com.renstate.message_service.domain.persistence;

import com.renstate.message_service.domain.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByReceiver(String receiver);
}