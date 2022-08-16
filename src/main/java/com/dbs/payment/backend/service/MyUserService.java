package com.dbs.payment.backend.service;

import com.dbs.payment.backend.models.User;
import com.dbs.payment.backend.repo.UserRepository;
import com.dbs.payment.backend.response.CustomerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found with username"));
        return MyUserDetails.build(user);
    }


//    public ResponseEntity<CustomerData> getCustDetails(){
//        MyUserDetails myUserDetails =(MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        User user = userRepository.findByUsername(myUserDetails.getUsername()).get();
//
//        CustomerData cd = new CustomerData();
//        cd.setAccHolderName(user.getCustomerId().getAccHolderName());
//        cd.setClearBalance(user.getCustomerId().getClearBalance());
//        cd.setOverdraft(user.getCustomerId().isOverdraft());
//        cd.setId(user.getCustomerId().getId());
//
//
//        return new ResponseEntity<CustomerData>(cd, HttpStatus.OK);
//
//    }

}
