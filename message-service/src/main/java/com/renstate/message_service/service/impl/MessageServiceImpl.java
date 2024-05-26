package com.renstate.message_service.service.impl;


import com.renstate.message_service.api.dto.MessageRequest;
import com.renstate.message_service.api.dto.ResponseUserResource;
import com.renstate.message_service.domain.model.entity.Message;
import com.renstate.message_service.domain.persistence.MessageRepository;
import com.renstate.message_service.service.MessageService;
import com.renstate.message_service.service.UserServiceClient;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private final ModelMapper modelMapper;

    public MessageServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Message saveMessage(MessageRequest message) {
        ResponseUserResource sender = userServiceClient.getUserByUsername(message.getSender());
        ResponseUserResource receiver = userServiceClient.getUserByUsername(message.getReceiver());

        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("Sender or receiver not found");
        }

        message.setTimestamp(System.currentTimeMillis());
        return messageRepository.save(modelMapper.map(message, Message.class));
    }

    @Override
    public List<Message> getMessagesForReceiver(String receiver) {
        ResponseUserResource user = userServiceClient.getUserByUsername(receiver);
        if (user == null) {
            throw new IllegalArgumentException("Receiver not found");
        }
        return messageRepository.findByReceiver(receiver);
    }
}
