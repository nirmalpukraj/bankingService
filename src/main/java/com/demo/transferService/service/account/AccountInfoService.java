package com.demo.transferService.service.account;

import com.demo.transferService.dto.request.AccountRequestDTO;
import com.demo.transferService.dto.response.ResponseDTO;
import com.demo.transferService.entity.AccountEntity;
import com.demo.transferService.repository.AccountRepository;
import com.demo.transferService.repository.TransactionRepository;
import com.demo.transferService.service.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountInfoService extends BaseService {

    public AccountInfoService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        super(transactionRepository, accountRepository);
    }

    public ResponseEntity<ResponseDTO> processRequest(AccountRequestDTO accountRequestDTO) {


        Optional<AccountEntity> optionalAccount = this.accountRepository.findByAccountNumber(accountRequestDTO.getAccountNumber());

        if (optionalAccount.isPresent()) {
            return accountRequestAccountResponseBiFunction.apply(optionalAccount.get(), accountRequestDTO);
        } else {
            return errorResponseFunction.apply(accountRequestDTO,"ACCOUNT DETAILS NOT FOUND!");
        }


    }


}
