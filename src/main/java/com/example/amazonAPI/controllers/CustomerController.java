package com.example.amazonAPI.controllers;

import com.example.amazonAPI.CustomizedResponse;
import com.example.amazonAPI.Models.Customer;
import com.example.amazonAPI.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins ="http://localhost:3000")
@Controller
public class CustomerController {
    @Autowired
    private CustomerService service;
    @GetMapping("/customers")
    public ResponseEntity getCustomers()
    {
        var customizedResponse = new CustomizedResponse("A List Of Customers",service.getCustomer());

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity getACustomer(@PathVariable("id")String id)
    {
        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Customer with id: "+id, Collections.singletonList(service.getAdCustomer(id)));
        }  catch (Exception e) {
            customizedResponse=new CustomizedResponse(e.getMessage(),null);
            return new ResponseEntity(customizedResponse,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
        //return new ResponseEntity(service.getCustomers(), HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/customers",consumes={
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addCustomer(@RequestBody Customer customer)
    {
        var customizedResponse = new CustomizedResponse("Customer Created Successfully", Collections.singletonList(service.addCustomer(customer)));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
}
