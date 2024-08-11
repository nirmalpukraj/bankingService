package com.demo.transferService.dto.response;

import lombok.Data;

@Data
public class ErrorResponseDTO extends ResponseDTO {
    private String message;
}