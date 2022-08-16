package com.dbs.payment.backend.controller;


import com.dbs.payment.backend.DTO.AuthDto;
import com.dbs.payment.backend.DTO.RequestOtp;
import com.dbs.payment.backend.DTO.SignupDTO;
import com.dbs.payment.backend.models.Customers;
import com.dbs.payment.backend.models.ERole;
import com.dbs.payment.backend.models.Logger;
import com.dbs.payment.backend.models.Role;
import com.dbs.payment.backend.models.User;
import com.dbs.payment.backend.repo.CustomerRepository;
import com.dbs.payment.backend.repo.LoggerRepository;
import com.dbs.payment.backend.repo.RoleRepository;
import com.dbs.payment.backend.repo.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.dbs.payment.backend.response.LoginResponse;
import com.dbs.payment.backend.response.MessageResponse;
import com.dbs.payment.backend.security.JwtUtils;
import com.dbs.payment.backend.service.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    LoggerRepository loggerRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public static final String ACCOUNT_SID = "AC88476e22d734d53fc5e9b49eb7843978";
    public static final String AUTH_TOKEN = "d14418eb6b79a6b9ac7e69921a2ee3b6";


    @GetMapping("/createEmployee")
    public void createEmployee(){

        List<User> usersList  = userRepository.findAll();

        if(usersList.isEmpty()){
            Set<Role> roles = new HashSet<>();
            Role emp = roleRepository.findByRolename(ERole.Employee).get();
            String username = "sriram";
            roles.add(emp);
            String password = encoder.encode("sriram");

            User user = new User(username,password,"9121638807",null);
            user.setRoles(roles);

            userRepository.save(user);
        }

    }

    @GetMapping("/getLogger")
    public ResponseEntity<Object> getLogger(){
        return new ResponseEntity<>(loggerRepository.findAll(), HttpStatus.OK);
    }



    @GetMapping("/getUsername")
    public ResponseEntity<Object> getUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    public void AccountLogger(String customerId, String Username,String ipAddress) {

        User u = userRepository.findByUsername(Username).get();
        Customers c = customerRepository.findById(customerId).get();

        MyUserDetails myUserDetails =(MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User emp = userRepository.findById(myUserDetails.getId()).get();

        Logger l = new Logger();

        l.setCustomerId(c);
        l.setEmployeeId(emp);
        l.setUserId(u);
        l.setScreenName(emp.getUsername());
        l.setIpaddress(ipAddress);
        l.setAction("Created");

        loggerRepository.save(l);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> Register(@Valid @RequestBody SignupDTO signupRequest){

        if(userRepository.existsByUsername(signupRequest.getUsername())){
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error : username  is already taken"));
        }


        User user = new User(signupRequest.getUsername(),
                encoder.encode(signupRequest.getPassword()),
                signupRequest.getPhone());

        Set<String> strRoles = signupRequest.getRolename();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByRolename(ERole.Customer)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }

        Customers c = customerRepository.findById(signupRequest.getCustomerId()).get();

        user.setCustomerId(c);
        user.setRoles(roles);
        userRepository.save(user);


        AccountLogger(signupRequest.getCustomerId(), signupRequest.getUsername(), signupRequest.getIpAddress());

        return ResponseEntity.ok(new MessageResponse("User Registered Successfully!"));

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthDto loginRequest){

        Authentication authentication  = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();

        String otpStatus = sendOtp(myUserDetails.getPhone());

        List<String> roles = myUserDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        System.out.println(roles.contains("Employee"));

        return ResponseEntity.ok(new LoginResponse(jwt,
                myUserDetails.getId(),
                otpStatus,
                roles,
                myUserDetails.getUsername(),true));
    }


    public String sendOtp(String phone){
        String p = "+91" + phone;
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Verification verification = Verification.creator(
                "VAe7928d184686f743fa04c150f377a28d",
                p,
                "sms").create();

        return verification.getStatus();

    }


    @PostMapping("/verifyOtp")
    public ResponseEntity<Object> VerifyOtp(@RequestBody RequestOtp otp){

        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String p = "+91" + myUserDetails.getPhone();

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        VerificationCheck verificationCheck = VerificationCheck.creator(
                        "VAe7928d184686f743fa04c150f377a28d")
                .setTo(p)
                .setCode(otp.getOtp())
                .create();

        System.out.println(verificationCheck.getStatus());
        System.out.println( verificationCheck.getSnaAttemptsErrorCodes());


        return new ResponseEntity(new MessageResponse(verificationCheck.getStatus()),HttpStatus.OK);
    }


}