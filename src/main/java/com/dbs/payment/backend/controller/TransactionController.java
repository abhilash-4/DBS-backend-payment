package com.dbs.payment.backend.controller;

import com.dbs.payment.backend.DTO.TransactionDTO;
import com.dbs.payment.backend.response.MessageResponse;
import com.dbs.payment.backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/test")
    public String test(){
        return "working sucessfully";
    }

    @PostMapping("/transact")
    public ResponseEntity transact(@RequestBody TransactionDTO transactionDTO) throws FileNotFoundException {
        return transactionService.submitTransaction(transactionDTO);

    }


    @GetMapping("/getAllTrans")
    public ResponseEntity<Object> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

}
