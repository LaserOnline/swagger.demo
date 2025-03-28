package com.spring.boot.swagger.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MessageRequest {
    @Schema(description = "request message", example = "hello world")
    @NotBlank(message = "message empty")
    private String message;
}
