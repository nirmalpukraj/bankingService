package com.demo.transferService.service.transfer;

import com.demo.transferService.dto.request.TransferRequestDTO;
import com.demo.transferService.dto.response.ResponseDTO;
import com.demo.transferService.entity.AccountEntity;
import com.demo.transferService.entity.TransactionEntity;
import com.demo.transferService.repository.AccountRepository;
import com.demo.transferService.repository.TransactionRepository;
import com.demo.transferService.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Service
@Slf4j
public class TransferFundService extends BaseService {

    public TransferFundService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        super(transactionRepository, accountRepository);
    }

    @Transactional
    public ResponseEntity<ResponseDTO> processRequest(TransferRequestDTO transferRequest) {

        if(transferRequest.getSourceAccountNumber().equals(transferRequest.getDestinationAccountNumber()))
            return errorResponseFunction.apply(transferRequest, "INVALID REQUEST! SOURCE AND DESTINATION ACCOUNT CANNOT BE SAME.");

        Optional<AccountEntity> optionalSourceAccount = this.accountRepository.findByAccountNumber(transferRequest.getSourceAccountNumber());
        Optional<AccountEntity> optionalDestinationAccount = this.accountRepository.findByAccountNumber(transferRequest.getDestinationAccountNumber());

        if (!optionalSourceAccount.isPresent())
            return errorResponseFunction.apply(transferRequest, "INVALID REQUEST! SOURCE ACCOUNT DETAILS NOT FOUND.");
        if (!optionalDestinationAccount.isPresent())
            return errorResponseFunction.apply(transferRequest, "INVALID REQUEST! DESTINATION ACCOUNT DETAILS NOT FOUND.");
        if (transferAmountIsNotValid(optionalSourceAccount.get(), transferRequest))
            return errorResponseFunction.apply(transferRequest, "INVALID REQUEST! INSUFFICIENT FUNDS.");

        AccountEntity sourceAccountEntity = optionalSourceAccount.get();
        AccountEntity destinationAccountEntity = optionalDestinationAccount.get();
        sourceAccountEntity.setAccountBalance(optionalSourceAccount.get().getAccountBalance().subtract(transferRequest.getTransferAmount()));
        destinationAccountEntity.setAccountBalance(optionalDestinationAccount.get().getAccountBalance().add(transferRequest.getTransferAmount()));
        TransactionEntity transactionEntity = getTransaction(sourceAccountEntity, destinationAccountEntity, transferRequest);
        this.accountRepository.save(sourceAccountEntity);
        this.accountRepository.save(destinationAccountEntity);
        this.transactionRepository.save(transactionEntity);
        return transactionResponseEntityFunction.apply(transactionEntity);

    }

    private boolean transferAmountIsNotValid(AccountEntity accountEntity, TransferRequestDTO transferRequest) {
        return transferRequest.getTransferAmount().compareTo(accountEntity.getAccountBalance()) > 0;
    }

    private TransactionEntity getTransaction(AccountEntity sourceAccountEntity, AccountEntity destinationAccountEntity, TransferRequestDTO transferRequest) {
        return TransactionEntity.builder()
                .requestId(transferRequest.getRequestId())
                .sourceAccountEntity(sourceAccountEntity)
                .destinationAccountEntityNumber(destinationAccountEntity)
                .transferAmount(transferRequest.getTransferAmount())
                .transactionReferenceNumber("TRN-".concat(String.valueOf(System.currentTimeMillis())))
                .transactionTimestamp(now())
                .build();
    }
}
