package com.demo.transferService.controller;

import com.demo.transferService.dto.request.AccountRequestDTO;
import com.demo.transferService.dto.request.TransferRequestDTO;
import com.demo.transferService.facade.BankingFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class BaseController {

    final BankingFacade<AccountRequestDTO> accountInformationFacade;
    final BankingFacade<TransferRequestDTO> transferFundFacade;



}
