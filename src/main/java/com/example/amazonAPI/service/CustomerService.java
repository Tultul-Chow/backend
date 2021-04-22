package com.example.amazonAPI.service;

import com.example.amazonAPI.Models.CustomerRepository;
import com.example.amazonAPI.Models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements UserDetailsService
{
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public Customer addCustomer(Customer customer)
    {
        String encodedPassword=bCryptPasswordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);

        Customer insertIntoCustomers= repository.insert(customer);
        return insertIntoCustomers;
    }
    public List<Customer> getCustomer()
    {
         return  repository.findAll();
    }
    public Optional<Customer> getAdCustomer(String id) throws Exception
    {
        Optional<Customer>customer = repository.findById(id);
        if(!customer.isPresent())
        {
            throw new Exception ("Customer with id "+id+" not found");
        }
        return customer;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Customer foundCustomer= repository.findByUsername(username);

       String userN=foundCustomer.getUsername();
       String password=foundCustomer.getPassword();
       return new User(userN,password, new ArrayList<>());

    }
}
