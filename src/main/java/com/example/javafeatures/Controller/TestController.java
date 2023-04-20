package com.example.javafeatures.Controller;

import com.example.javafeatures.Entity.Customer;
import com.example.javafeatures.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/Customers")
    public List<Customer> FindSet() throws NoSuchFieldException {
        return customerService.FindSet();
    }
}
