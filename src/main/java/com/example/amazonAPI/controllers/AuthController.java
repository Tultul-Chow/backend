package com.example.amazonAPI.controllers;


import com.example.amazonAPI.CustomizedResponse;
import com.example.amazonAPI.Models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping(value="/auth",consumes={
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity login(@RequestBody Customer customer)
    {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getUsername(), customer.getPassword()));

            var customizedResponse = new CustomizedResponse("You login successfully", null);

            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        }
        catch(BadCredentialsException ex){
            var customizedResponse = new CustomizedResponse("Your username and/or password were incorrect", null);

            return new ResponseEntity(customizedResponse, HttpStatus.UNAUTHORIZED);
        }

    }
}
