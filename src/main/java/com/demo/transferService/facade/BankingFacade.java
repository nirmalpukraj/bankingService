package com.demo.transferService.facade;

import com.demo.transferService.dto.response.ResponseDTO;
import org.springframework.http.ResponseEntity;


@FunctionalInterface
public interface BankingFacade<T> {
    ResponseEntity<ResponseDTO> processRequest(T t);
}
