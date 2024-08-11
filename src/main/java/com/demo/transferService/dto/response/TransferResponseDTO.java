package com.demo.transferService.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransferResponseDTO extends ResponseDTO {

    private String transactionReferenceNumber;
    private BigDecimal transferAmount;
    private LocalDateTime transactionTimestamp;
}
