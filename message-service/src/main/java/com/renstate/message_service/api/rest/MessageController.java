package com.renstate.message_service.api.rest;

import com.renstate.message_service.api.dto.MessageRequest;
import com.renstate.message_service.domain.model.entity.Message;
import com.renstate.message_service.service.MessageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@Tag(name = "Message Controller", description = "message")
@RestController
@RequestMapping(value = "/api/v1/message", produces = "application/json")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    public Message sendMessage(@RequestBody MessageRequest message) {
        return messageService.saveMessage(message);
    }

    @GetMapping("/receiver/{username}")
    public List<Message> getMessagesForReceiver(@PathVariable String username) {
        return messageService.getMessagesForReceiver(username);
    }

}
