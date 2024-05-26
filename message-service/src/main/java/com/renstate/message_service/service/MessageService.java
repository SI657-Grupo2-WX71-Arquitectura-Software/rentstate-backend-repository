package com.renstate.message_service.service;

import com.renstate.message_service.api.dto.MessageRequest;
import com.renstate.message_service.api.dto.MessageResponse;
import com.renstate.message_service.domain.model.entity.Message;

import java.util.List;

public interface MessageService {

    public Message saveMessage(MessageRequest messageRequest);

    public List<Message> getMessagesForReceiver(String receiver);

}
