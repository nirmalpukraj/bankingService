package com.demo.transferService.dto.request;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;

@Data
public class RequestDTO {
    @Digits(integer = 10,fraction = 0)
    @Positive
    private Long requestId;
}
