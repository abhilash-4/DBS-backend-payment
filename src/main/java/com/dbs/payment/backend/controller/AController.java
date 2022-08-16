package com.dbs.payment.backend.controller;

import com.dbs.payment.backend.models.*;
import com.dbs.payment.backend.repo.LoggerRepository;
import com.dbs.payment.backend.repo.UserRepository;
import com.dbs.payment.backend.response.CustomerData;
import com.dbs.payment.backend.service.MyUserDetails;
import com.dbs.payment.backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class AController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    UserRepository userRepository;



    @GetMapping("/getUsers")
    public User getUName(){
        MyUserDetails myUserDetails =(MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User u = userRepository.findByUsername(myUserDetails.getUsername()).get();
        return u;
    }


    //get cutsomer by customer  ID
    @GetMapping("/getCustomer/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable String id){
        return transactionService.getCustomerById(id);
    }

    //Get ALL message codes
    @GetMapping("/getAllMessageCodes")
    public ResponseEntity<List<MessageCode>> getAllMessageCodes(){
        return transactionService.getAllMessageCodes();
    }


    //Get ALL transfer codes
    @GetMapping("/getAllTransferCodes")
    public ResponseEntity<List<TransferType>> getAllTransferCodes(){
        return transactionService.getAllTransferCodes();
    }

    //Get ALL currency codes
    @GetMapping("/getAllCurrencyCodes")
    public ResponseEntity<List<Currency>> getAllCurrencyCodes(){
        return transactionService.getAllCurrencyCodes();
    }



    @GetMapping("/getMyDetails")
    public ResponseEntity<CustomerData> getCustDetails(){

        MyUserDetails myUserDetails =(MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByUsername(myUserDetails.getUsername()).get();

        CustomerData cd = new CustomerData();
        cd.setAccHolderName(user.getCustomerId().getAccHolderName());
        cd.setClearBalance(user.getCustomerId().getClearBalance());
        cd.setOverdraft(user.getCustomerId().isOverdraft());
        cd.setId(user.getCustomerId().getId());


        return new ResponseEntity<CustomerData>(cd, HttpStatus.OK);
    }


    @GetMapping("getBank/{bic}")
    public ResponseEntity<Object> getBankByBIC(@PathVariable String bic){
        return transactionService.getBankByBIC(bic);
    }

    @GetMapping("getMessageCodes")
    public ResponseEntity<Object> getMessageCodes(){
        return new ResponseEntity<>(transactionService.getAllMessageCodes(),HttpStatus.OK);
    }

    //get message code count
    @GetMapping("getMessageCount")
    public ResponseEntity<Object> getMesssageCount(){
        return transactionService.findMessageCount();
    }


    @GetMapping("getTransferCount")
    public ResponseEntity<Object> getTransferCount(){
        return transactionService.findTransferCount();
    }


    @GetMapping("getTopCustomers")
    public ResponseEntity<Object> getTopCustomers(){
        return transactionService.findTopCustoemrs();
    }

    @GetMapping("getTopBanks")
    public ResponseEntity<Object> getTopBanks(){
        return transactionService.findTopBanks();
    }

}
