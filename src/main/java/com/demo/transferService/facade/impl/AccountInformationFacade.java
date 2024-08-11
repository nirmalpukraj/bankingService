package com.demo.transferService.facade.impl;

import com.demo.transferService.dto.request.AccountRequestDTO;
import com.demo.transferService.dto.response.ResponseDTO;
import com.demo.transferService.facade.BankingFacade;
import com.demo.transferService.service.account.AccountInfoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
@Component
@AllArgsConstructor
public class AccountInformationFacade implements BankingFacade<AccountRequestDTO> {

    final AccountInfoService accountInfoService;

    @Override
    public ResponseEntity<ResponseDTO> processRequest(AccountRequestDTO accountRequestDTO) {
        return this.accountInfoService.processRequest(accountRequestDTO);

    }
}
