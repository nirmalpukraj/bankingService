package com.demo.transferService.dto.response;

import com.demo.transferService.enumeration.Status;
import lombok.Data;

@Data
public class ResponseDTO {
    private Long RequestId;
    private Status status;

}
