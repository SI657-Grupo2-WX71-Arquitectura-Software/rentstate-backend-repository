package com.renstate.message_service.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageResponse {

    private Long id;
    private Long room_id;
    private String message;
    private Date date;
}
