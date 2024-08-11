package com.demo.transferService;

import com.demo.transferService.entity.AccountEntity;
import com.demo.transferService.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.function.BiFunction;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class TransferBankingApplication {

    final AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(TransferBankingApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            this.accountRepository.save(accountBiFunction.apply("RKJ1000000065",new BigDecimal("128953.000")));
            this.accountRepository.save(accountBiFunction.apply("RKJ1000000066",new BigDecimal("00.000")));
            this.accountRepository.save(accountBiFunction.apply("RKJ1000000067",new BigDecimal("100.000")));
            this.accountRepository.save(accountBiFunction.apply("RKJ1000000068",new BigDecimal("509908.000")));
            this.accountRepository.save(accountBiFunction.apply("RKJ1000000064",new BigDecimal("676767.000")));
            this.accountRepository.save(accountBiFunction.apply("RKJ1000000063",new BigDecimal("986428.777")));
            log.info("INSERTED ACCOUNTS SUCCESSFULLY!");
            log.trace("{}", this.accountRepository.findAll());
        };


    }

    private final BiFunction<String, BigDecimal, AccountEntity> accountBiFunction = (accountNumber, balance) ->{
		AccountEntity accountEntity = new AccountEntity();
		accountEntity.setAccountNumber(accountNumber);
		accountEntity.setAccountBalance(balance);
		return accountEntity;
	};
}
