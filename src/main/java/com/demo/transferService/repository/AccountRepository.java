package com.demo.transferService.repository;

import com.demo.transferService.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity,Long> {

    Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
