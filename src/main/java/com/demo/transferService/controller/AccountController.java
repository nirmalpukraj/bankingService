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
@RequestMapping("accounts")
@Slf4j
public class AccountController extends BaseController {


    public AccountController(BankingFacade<AccountRequestDTO> accountInformationFacade, BankingFacade<TransferRequestDTO> transferFundFacade) {
        super(accountInformationFacade, transferFundFacade);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> getAccountDetails(@NotNull @Valid @RequestBody AccountRequestDTO accountRequestDTO) {
        log.info("[REQUEST-ID : {}] : ACCOUNT REQUEST RECEIVED.", accountRequestDTO.getRequestId());
        return this.accountInformationFacade.processRequest(accountRequestDTO);
    }
}
