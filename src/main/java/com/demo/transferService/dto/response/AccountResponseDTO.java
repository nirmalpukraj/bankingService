package com.demo.transferService.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountResponseDTO extends ResponseDTO {
    private String accountNumber;
    private BigDecimal accountBalance;
}
