package com.renstate.message_service.shared;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.renstate.message_service.service")
public class FeignConfig {
}