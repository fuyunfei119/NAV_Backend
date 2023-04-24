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

    @GetMapping("/GetFilters")
    public List<Customer> GetFilter() throws Exception {
        return customerService.CheckIfCustomerHasOver_100_Balance();
    }

    @GetMapping("/Test3")
    public Customer Test3() throws Exception {
        return customerService.GetFirstCustomerLocatedInUSA();
    }

    @GetMapping("/Test4")
    public Customer Test4() throws NoSuchFieldException {
        return customerService.GetLastRetailCustomer();
    }

    @GetMapping("/Test5")
    public List<Customer> Test5() throws Exception {
        return customerService.GetFirst_10_ActiveCustomer();
    }

    @GetMapping("/Test6")
    public Customer Test6() {
        return customerService.GetCustomer();
    }

    @GetMapping("/Test7")
    public Integer Test7() throws Exception {
        return customerService.CountRetailCustomerType();
    }
}
