package com.demo.transferService.controller;

import com.demo.transferService.dto.request.AccountRequestDTO;
import com.demo.transferService.dto.request.TransferRequestDTO;
import com.demo.transferService.dto.response.ResponseDTO;
import com.demo.transferService.facade.BankingFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("transfers")
@Slf4j
public class TransferController extends BaseController {

    public TransferController(BankingFacade<AccountRequestDTO> accountInformationFacade, BankingFacade<TransferRequestDTO> transferFundFacade) {
        super(accountInformationFacade, transferFundFacade);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> transferFunds(@NotNull @Valid @RequestBody TransferRequestDTO transferRequest){
        log.info("[REQUEST-ID : {}] : TRANSFER REQUEST RECEIVED.", transferRequest.getRequestId());
        return this.transferFundFacade.processRequest(transferRequest);
    }
}
