package com.demo.transferService.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class AccountRequestDTO extends RequestDTO {
    @NotNull(message = "AccountEntity number cannot be null")
    @Length(min = 13, max = 13)
    private String accountNumber;

}
