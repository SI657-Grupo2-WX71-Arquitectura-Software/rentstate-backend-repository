package com.renstate.message_service.service.impl;

import com.renstate.message_service.api.dto.MessageRequest;
import com.renstate.message_service.api.dto.ResponseUserResource;
import com.renstate.message_service.domain.model.entity.Message;
import com.renstate.message_service.domain.persistence.MessageRepository;
import com.renstate.message_service.service.UserServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {
    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserServiceClient userServiceClient;

    @Mock
    private ModelMapper modelMapper;

    private MessageServiceImpl messageService;

    private MessageRequest messageRequest;
    private Message message;
    private ResponseUserResource user;

    @BeforeEach
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        messageService = new MessageServiceImpl(modelMapper);

        Field repositoryField = MessageServiceImpl.class.getDeclaredField("messageRepository");
        repositoryField.setAccessible(true);
        repositoryField.set(messageService, messageRepository);

        Field userServiceField = MessageServiceImpl.class.getDeclaredField("userServiceClient");
        userServiceField.setAccessible(true);
        userServiceField.set(messageService, userServiceClient);

        messageRequest = new MessageRequest();
        messageRequest.setSender("sender");
        messageRequest.setReceiver("receiver");

        message = new Message();
        message.setSender("sender");
        message.setReceiver("receiver");

        user = new ResponseUserResource();
        user.setUsername("username");
    }

    @Test
    public void saveMessageTest() {
        when(userServiceClient.getUserByUsername(messageRequest.getSender())).thenReturn(user);
        when(userServiceClient.getUserByUsername(messageRequest.getReceiver())).thenReturn(user);
        when(modelMapper.map(any(MessageRequest.class), any())).thenReturn(message);
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        Message result = messageService.saveMessage(messageRequest);

        assertEquals(message, result);
    }
}