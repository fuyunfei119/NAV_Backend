package com.example.javafeatures.Controller;

import com.example.javafeatures.Entity.Customer;
import com.example.javafeatures.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/Test1")
    public List<Customer> Test1() throws NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return customerService.test();
    }

    @GetMapping("/Customers")
    public List<Customer> FindSet() throws NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return customerService.FindSet();
    }

    @GetMapping("/GetFilters")
    public List<Customer> GetFilter() throws Exception {
        return customerService.CheckIfCustomerHasOver_100_Balance();
    }

    @GetMapping("/Test3")
    public List<Customer> Test3() throws Exception {
        return customerService.GetFirstCustomerLocatedInUSA();
    }

    @GetMapping("/Test4")
    public List<Customer> Test4() throws NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return customerService.GetLastRetailCustomer();
    }

    @GetMapping("/Test5")
    public List<Customer> Test5() throws Exception {
        return customerService.GetFirst_10_ActiveCustomer();
    }

    @GetMapping("/Test6")
    public List<Customer> Test6() throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return customerService.GetCustomer();
    }

    @GetMapping("/Test7")
    public Integer Test7() throws Exception {
        return customerService.CountRetailCustomerType();
    }

    @GetMapping("/TestForJan")
    public List<Customer> Test8() throws Exception {
        return customerService.FindTop_10_CUstomer();
    }

    @GetMapping("/InsertAndModify")
    public Customer Test9() throws Exception {
        return customerService.InsertAndModify();
    }
}
