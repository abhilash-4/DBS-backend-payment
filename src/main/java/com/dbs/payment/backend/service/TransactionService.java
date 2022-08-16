package com.dbs.payment.backend.service;

import com.dbs.payment.backend.DTO.TransactionDTO;
import com.dbs.payment.backend.DTO.TransactionItem;
import com.dbs.payment.backend.models.*;
import com.dbs.payment.backend.models.Currency;
import com.dbs.payment.backend.repo.*;
import com.dbs.payment.backend.response.CustomerData;
import com.dbs.payment.backend.response.MessageResponse;
import com.dbs.payment.backend.response.TransactionResponse;
import com.dbs.payment.backend.response.testResponse;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    BanksRepository banksRepository;

    @Autowired
    MessageCodeRepository messageCodeRepository;

    @Autowired
    TransferTypeRepository transferTypeRepository;

    @Autowired
    UserRepository userRepository;



    public ResponseEntity<Object> getAllTransactions(){

        MyUserDetails myUserDetails =(MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = userRepository.findByUsername(myUserDetails.getUsername()).get();

        List<String> roles = myUserDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        List<Transaction> list = transactionRepository.findAll();

        List<TransactionResponse> tr = new ArrayList<>();


        if(roles.contains("Employee")){

            for (Transaction item: list) {
                TransactionResponse temp  = new TransactionResponse();
                temp.setTransactionId(item.getId());
                temp.setAmount(item.getInrAmount()+"");
                temp.setDate(item.getTransferDate()+"");
                temp.setRecAccName(item.getRecAccountName());
                temp.setStatus(item.getStatus()+"");
                tr.add(temp);

            }

            return new ResponseEntity<Object>(tr,HttpStatus.OK);
        }
        else{

            List<Transaction> l = transactionRepository.findByCustomerId(u.getCustomerId().getId());


            for (Transaction item: l) {
                TransactionResponse temp  = new TransactionResponse();
                temp.setTransactionId(item.getId());
                temp.setAmount(item.getInrAmount()+"");
                temp.setDate(item.getTransferDate()+"");
                temp.setRecAccName(item.getRecAccountName());
                temp.setStatus(item.getStatus()+"");
                tr.add(temp);

            }

            return new ResponseEntity<>(tr,HttpStatus.OK);

//            List<Transaction> l = new ArrayList<>();
//            if()
        }
    }



    //Get Customer Details By Customer ID
    public ResponseEntity<Object> getCustomerById(String id){

        Optional<Customers> customer =  customerRepository.findById(id);

        MyUserDetails myUserDetails =(MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(myUserDetails.getId()).get();


        List<String> roles = myUserDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        if(customer.isEmpty()) {
            return new ResponseEntity<Object>(
                    new MessageResponse("customer id is not valid"),HttpStatus.UNAUTHORIZED);
        }
        else if( roles.contains("Employee") ||  user.getCustomerId().getId().equals(customer.get().getId())){
            return new ResponseEntity<Object>(customer.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Object>(
                    new MessageResponse("customer id is not valid"),HttpStatus.UNAUTHORIZED);
//            return new ResponseEntity<Object>(customer.get(),HttpStatus.OK);
        }

    }

    //Get Bank Details By BIC
    public ResponseEntity<Object> getBankByBIC(String bic){
        Optional<Banks> bank = banksRepository.findById(bic);

        if(bank.isEmpty()){
            return new ResponseEntity<Object>(new MessageResponse("No Institution with given BIC"),HttpStatus.OK);
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(bank.get());
        }

    }

    //Get All Message Codes
    public ResponseEntity<List<MessageCode>> getAllMessageCodes(){

        return new ResponseEntity<>(messageCodeRepository.findAll(),HttpStatus.OK);

    }


    //Get All Transfer Type Codes
    public ResponseEntity<List<TransferType>> getAllTransferCodes(){

        return new ResponseEntity<>(transferTypeRepository.findAll(),HttpStatus.OK);

    }

    //Get All Currency  Codes
    public ResponseEntity<List<Currency>> getAllCurrencyCodes(){

        return new ResponseEntity<>(currencyRepository.findAll(),HttpStatus.OK);

    }



    public void saveTransaction(TransactionItem transactionItem,
                                  Customers customer,
                                  Currency currencyCode,
                                  Banks receiverBic,
                                  MessageCode messageCode,
                                  TransferType transferType,
                                String status
                                  ){

        Transaction transaction = new Transaction();

        transaction.setCustomer(customer);
        transaction.setCurrencycode(currencyCode);
        transaction.setReceiverBIC(receiverBic);
        transaction.setRecAccountNumber(transactionItem.getRecAccountNumber());
        transaction.setRecAccountName(transactionItem.getRecAccountName());
        transaction.setTransferTypeCode(transferType);
        transaction.setMessageCode(messageCode);
        transaction.setCurrencyAmount(transactionItem.getCurrencyAmount());
        transaction.setTransferFee(transactionItem.getTransferFee());
        transaction.setInrAmount(transactionItem.getInrAmount());
        transaction.setTransferDate(new Date());

        if(status.equals("failed")){
            transaction.setStatus(EStatus.FAILED);
        }
        else{
            transaction.setStatus(EStatus.SUCCESS);
        }
        transactionRepository.save(transaction);
    }


    public ResponseEntity<Object> submitTransaction(TransactionDTO transactionDTO) throws FileNotFoundException {

        Double total = transactionDTO.getInrAmount() + transactionDTO.getTransferFee();

        Optional<Customers> customer = customerRepository.findById(transactionDTO.getCustomerId());
        Optional<Currency> currencyCode = currencyRepository.findById(transactionDTO.getCurrencyCode());
        Optional<Banks> receiverBic = banksRepository.findById(transactionDTO.getReceiverBIC());
        Optional<MessageCode> messageCode = messageCodeRepository.findById(transactionDTO.getMessageCode());
        Optional<TransferType> transferType = transferTypeRepository.findById(transactionDTO.getTransferTypeCode());


        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("my user name is " + myUserDetails.getUsername());
        User u = userRepository.findByUsername(myUserDetails.getUsername()).get();
        System.out.println("my role is " + u.getRoles());


        List<String> roles = myUserDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        // Not entering his customer id
        if(roles.contains("Customer") && !transactionDTO.getCustomerId().equals(u.getCustomerId().getId())){
            return new ResponseEntity<Object>(
                    new MessageResponse("Please Enter a valid Customer Id that is assigned to you"),HttpStatus.OK);
        }

        //Same Account Validation
        if(customer.get().getId().equals(transactionDTO.getRecAccountNumber())){

            return new ResponseEntity(
                    new MessageResponse("You cannot transfer money to yourself"),HttpStatus.NOT_ACCEPTABLE);
        }


        TransactionItem transactionItem = new TransactionItem();

        transactionItem.setRecAccountNumber(transactionDTO.getRecAccountNumber());
        transactionItem.setRecAccountName(transactionDTO.getRecAccountName());
        transactionItem.setCurrencyAmount(transactionDTO.getCurrencyAmount());
        transactionItem.setTransferFee(transactionDTO.getTransferFee());
        transactionItem.setInrAmount(transactionDTO.getInrAmount());



        //BANK Transfer for Own Account Validation
        if(transferType.get().getTransferCode().equals("BT")){

            if(!customer.get().getAccHolderName().toUpperCase().contains("HDFC BANK")){

                saveTransaction(transactionItem,
                        customer.get(),
                        currencyCode.get(),
                        receiverBic.get(),
                        messageCode.get(),
                        transferType.get(),"failed");

                return  new ResponseEntity<>(new MessageResponse("Bank Transfer can only be processed only if receiver and sender " +
                        "both belongs to HDFC BANK"),HttpStatus.NOT_ACCEPTABLE);
            }
        }

        //If no overdraft facility and no sufficient balance cancel the transaction
        if(!customer.get().isOverdraft() && total > customer.get().getClearBalance()){

            saveTransaction(transactionItem,
                    customer.get(),
                    currencyCode.get(),
                    receiverBic.get(),
                    messageCode.get(),
                    transferType.get(),"failed");

            return new ResponseEntity<Object>(new MessageResponse("you dont have sufficient funds or overdraft" +
                    "facility to process the transaction"),HttpStatus.NOT_ACCEPTABLE);

        }


        // If BANKS use CT then cancel the transaction
        if(transferType.get().getTransferCode().equals("CT")){

            if(transactionDTO.getRecAccountName().toUpperCase().contains("HDFC BANK")) {

                saveTransaction(transactionItem,
                        customer.get(),
                        currencyCode.get(),
                        receiverBic.get(),
                        messageCode.get(),
                        transferType.get(), "failed");

                return ResponseEntity.badRequest().body(new MessageResponse(
                        "Please Choose transfer type as Bank Transfer " +
                                "to transfer between bank accounts"
                ));
            }
        }


        //Check with sanction listing
        boolean isSanctioned = sanctionCheck(transactionDTO.getRecAccountName());

        if(isSanctioned){
            saveTransaction(transactionItem,
                    customer.get(),
                    currencyCode.get(),
                    receiverBic.get(),
                    messageCode.get(),
                    transferType.get(),"failed");

            return ResponseEntity.badRequest().body(new MessageResponse(
                    "DANGER ..........  The account is a sanctioned by OFAC(Office of Foreign Assets Control)"
            ));
        }






       //Setting the clear balance
      customer.get().setClearBalance(customer.get().getClearBalance() - total);


        System.out.println("saving the transaction");
       saveTransaction(transactionItem,
               customer.get(),
               currencyCode.get(),
               receiverBic.get(),
               messageCode.get(),
               transferType.get(),"success");


       return  new ResponseEntity<Object>(new MessageResponse("Transaction Successful"),HttpStatus.OK);
    }



    ///Sanction List Check
    public static boolean sanctionCheck(String name) throws FileNotFoundException{
        boolean flag = false;
        name = name.toUpperCase();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream("src/main/resources/sdn.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.contains(name)) {
                flag = true;
            }
        }

        if (flag) {
            return true;
        } else {
            return false;
        }
    }


    public ResponseEntity<Object> findMessageCount(){
        System.out.println("message count");
        return new ResponseEntity<Object>(transactionRepository.findMessageCount(),HttpStatus.OK);
    }

    public ResponseEntity<Object> findTransferCount(){
        return new ResponseEntity<Object>(transactionRepository.findTransferTypeCount(),HttpStatus.OK);
    }

    public ResponseEntity<Object> findTopCustoemrs(){
        List<Map<String,Integer>> map = transactionRepository.findTopByCutsomers();
        List<testResponse> list  = new ArrayList<>();


//        map.get(i).forEach((k, v) -> {
//
//            for (Object key : map.get(0).keySet()) {
//                System.out.println("Key : " + key.toString() + " Value : " + map.get(key));
//            }
////                testResponse t = new testResponse();
////                Customers c = customerRepository.findById(k).get();
//            System.out.println(k + " this is it" + v);
////                t.setCustomerName(c.getAccHolderName());
////                t.setTotal(v);
////                list.add(t);
//
//        });


        return new ResponseEntity<Object>(transactionRepository.findTopByCutsomers(),HttpStatus.OK);
    }

    public ResponseEntity<Object> findTopBanks(){

        return new ResponseEntity<Object>(transactionRepository.findTopByBanks(),HttpStatus.OK);
    }


}
