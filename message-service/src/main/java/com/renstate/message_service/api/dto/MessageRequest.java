package com.renstate.message_service.api.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageRequest {

    @NotNull
    @NotEmpty
    private String sender;

    @NotNull
    @NotEmpty
    private String receiver;

    @NotNull
    @NotEmpty
    private String content;


    private long timestamp;




}
