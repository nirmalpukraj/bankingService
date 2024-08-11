package com.demo.transferService;

import com.demo.transferService.dto.request.AccountRequestDTO;
import com.demo.transferService.dto.request.TransferRequestDTO;
import com.demo.transferService.dto.response.AccountResponseDTO;
import com.demo.transferService.dto.response.ErrorResponseDTO;
import com.demo.transferService.dto.response.TransferResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class TransferFundsBankingTests {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate = null;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "");
    }

    @Test
    void successTransferFundTest() {
        TransferRequestDTO transferRequest = new TransferRequestDTO();
        transferRequest.setRequestId(1234567890l);
        transferRequest.setTransferAmount(new BigDecimal(500));
        transferRequest.setSourceAccountNumber("RKJ1000000063");
        transferRequest.setDestinationAccountNumber("RKJ1000000064");
        // Initiate Transfer
        ResponseEntity<TransferResponseDTO> responseResponseEntity = restTemplate
                .postForEntity(baseUrl.concat("/transfers"), transferRequest, TransferResponseDTO.class);

        // Get Source account holder details
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO();
        accountRequestDTO.setRequestId(1234567890l);
        accountRequestDTO.setAccountNumber("RKJ1000000063");
        ResponseEntity<AccountResponseDTO> sourceAccount = restTemplate
                .postForEntity(baseUrl.concat("/accounts"), accountRequestDTO, AccountResponseDTO.class);


        // Get Destination account holder details
        AccountRequestDTO accountRequestDTO1 = new AccountRequestDTO();
        accountRequestDTO1.setRequestId(1234567890l);
        accountRequestDTO1.setAccountNumber("RKJ1000000064");
        ResponseEntity<AccountResponseDTO> destinationAccountAccount = restTemplate
                .postForEntity(baseUrl.concat("/accounts"), accountRequestDTO1, AccountResponseDTO.class);

        assertAll(
                () -> assertNotNull(responseResponseEntity.getBody()),
                () -> assertEquals(200, responseResponseEntity.getStatusCodeValue()),
                //Validate if money deducted from source account
                () -> assertNotNull(sourceAccount.getBody()),
                () -> assertEquals(new BigDecimal("985928.78"), sourceAccount.getBody().getAccountBalance()),

                // Validate if money credited to beneficiary account
                () -> assertNotNull(destinationAccountAccount.getBody()),
                () -> assertEquals(new BigDecimal("677267.00"), destinationAccountAccount.getBody().getAccountBalance())
        );

    }

    @Test
    void sourceAndDestinationAccountIsSameFailureTest() {
        TransferRequestDTO transferRequest = new TransferRequestDTO();
        transferRequest.setRequestId(1234567890l);
        transferRequest.setTransferAmount(new BigDecimal(500));
        transferRequest.setSourceAccountNumber("RKJ1000000065");
        transferRequest.setDestinationAccountNumber("RKJ1000000065");

        ResponseEntity<ErrorResponseDTO> responseResponseEntity = restTemplate
                .postForEntity(baseUrl.concat("/transfers"), transferRequest, ErrorResponseDTO.class);

        assertAll(
                () -> assertNotNull(responseResponseEntity.getBody()),
                () -> assertEquals("INVALID REQUEST! SOURCE AND DESTINATION ACCOUNT CANNOT BE SAME.", responseResponseEntity.getBody().getMessage())
        );
    }

    @Test
    void sourceAccountNotFoundFailureTest() {
        TransferRequestDTO transferRequest = new TransferRequestDTO();
        transferRequest.setRequestId(1234567890l);
        transferRequest.setTransferAmount(new BigDecimal(500));
        transferRequest.setSourceAccountNumber("RKJ1000000165");
        transferRequest.setDestinationAccountNumber("RKJ1000000065");

        ResponseEntity<ErrorResponseDTO> responseResponseEntity = restTemplate
                .postForEntity(baseUrl.concat("/transfers"), transferRequest, ErrorResponseDTO.class);

        assertAll(
                () -> assertNotNull(responseResponseEntity.getBody()),
                () -> assertEquals("INVALID REQUEST! SOURCE ACCOUNT DETAILS NOT FOUND.", responseResponseEntity.getBody().getMessage())
        );
    }

    @Test
    void destinationAccountNotFoundFailureTest() {
        TransferRequestDTO transferRequest = new TransferRequestDTO();
        transferRequest.setRequestId(1234567890l);
        transferRequest.setTransferAmount(new BigDecimal(500));
        transferRequest.setSourceAccountNumber("RKJ1000000065");
        transferRequest.setDestinationAccountNumber("RKJ1000000165");

        ResponseEntity<ErrorResponseDTO> responseResponseEntity = restTemplate
                .postForEntity(baseUrl.concat("/transfers"), transferRequest, ErrorResponseDTO.class);

        assertAll(
                () -> assertNotNull(responseResponseEntity.getBody()),
                () -> assertEquals("INVALID REQUEST! DESTINATION ACCOUNT DETAILS NOT FOUND.", responseResponseEntity.getBody().getMessage())
        );
    }

    @Test
    void insufficientBalanceTest() {
        TransferRequestDTO transferRequest = new TransferRequestDTO();
        transferRequest.setRequestId(1234567890l);
        transferRequest.setTransferAmount(new BigDecimal(1289532));
        transferRequest.setSourceAccountNumber("RKJ1000000065");
        transferRequest.setDestinationAccountNumber("RKJ1000000064");
        ResponseEntity<ErrorResponseDTO> responseResponseEntity = restTemplate
                .postForEntity(baseUrl.concat("/transfers"), transferRequest, ErrorResponseDTO.class);
        assertAll(
                () -> assertNotNull(responseResponseEntity.getBody()),
                () -> assertEquals("INVALID REQUEST! INSUFFICIENT FUNDS.", responseResponseEntity.getBody().getMessage())
        );
    }

}
