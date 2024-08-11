package com.demo.transferService.service;

import com.demo.transferService.dto.request.AccountRequestDTO;
import com.demo.transferService.dto.request.RequestDTO;
import com.demo.transferService.dto.response.AccountResponseDTO;
import com.demo.transferService.dto.response.ErrorResponseDTO;
import com.demo.transferService.dto.response.ResponseDTO;
import com.demo.transferService.dto.response.TransferResponseDTO;
import com.demo.transferService.entity.AccountEntity;
import com.demo.transferService.entity.TransactionEntity;
import com.demo.transferService.repository.AccountRepository;
import com.demo.transferService.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.function.BiFunction;
import java.util.function.Function;

import static com.demo.transferService.enumeration.Status.FAILED;
import static com.demo.transferService.enumeration.Status.SUCCESS;

@Slf4j
@AllArgsConstructor
public abstract class BaseService {

    protected final TransactionRepository transactionRepository;
    protected final AccountRepository accountRepository;

    protected final BiFunction<AccountEntity, AccountRequestDTO, ResponseEntity<ResponseDTO>> accountRequestAccountResponseBiFunction = (account, accountRequest) -> {
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        accountResponseDTO.setRequestId(accountRequest.getRequestId());
        accountResponseDTO.setStatus(SUCCESS);
        accountResponseDTO.setAccountNumber(account.getAccountNumber());
        accountResponseDTO.setAccountBalance(account.getAccountBalance());
        return ResponseEntity.ok(accountResponseDTO);
    };

    protected final BiFunction<RequestDTO, String, ResponseEntity<ResponseDTO>> errorResponseFunction = (requestDTO, message) -> {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setRequestId(requestDTO.getRequestId());
        errorResponseDTO.setStatus(FAILED);
        errorResponseDTO.setMessage(message);
        return ResponseEntity.ok(errorResponseDTO);
    };

    protected final Function<TransactionEntity, ResponseEntity<ResponseDTO>> transactionResponseEntityFunction = (transactionEntity) -> {
        TransferResponseDTO transferResponse = new TransferResponseDTO();
        transferResponse.setRequestId(transactionEntity.getRequestId());
        transferResponse.setTransferAmount(transactionEntity.getTransferAmount());
        transferResponse.setStatus(SUCCESS);
        transferResponse.setTransactionReferenceNumber(transactionEntity.getTransactionReferenceNumber());
        transferResponse.setTransactionTimestamp(transactionEntity.getTransactionTimestamp());
        return ResponseEntity.ok(transferResponse);
    };

}
