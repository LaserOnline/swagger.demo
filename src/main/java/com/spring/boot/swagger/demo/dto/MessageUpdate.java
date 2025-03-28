package com.spring.boot.swagger.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MessageUpdate {
    @Schema(description = "update message", example = "Updated message")
    @NotBlank(message = "message is empty")
    private String message;
}
