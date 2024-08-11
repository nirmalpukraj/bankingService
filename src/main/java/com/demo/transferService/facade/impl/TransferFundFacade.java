package com.demo.transferService.facade.impl;

import com.demo.transferService.dto.request.TransferRequestDTO;
import com.demo.transferService.dto.response.ResponseDTO;
import com.demo.transferService.facade.BankingFacade;
import com.demo.transferService.service.transfer.TransferFundService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransferFundFacade implements BankingFacade<TransferRequestDTO> {
final TransferFundService transferFundService;
    @Override
    public ResponseEntity<ResponseDTO> processRequest(TransferRequestDTO transferRequest) {
        return this.transferFundService.processRequest(transferRequest);

    }
}
