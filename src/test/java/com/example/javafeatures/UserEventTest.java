package com.example.javafeatures;

import com.example.javafeatures.Repositry.CustomerRecord;
import com.example.javafeatures.Repositry.Mapper.CustomerRepositry;
import com.example.javafeatures.Service.CustomerService;
import com.example.javafeatures.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserEventTest {

    private UserService userService;
    private CustomerService customerService;
    @Autowired
    private CustomerRecord customerRecord;

    @Autowired
    private CustomerRepositry customerRepositry;

    @Autowired
    public UserEventTest(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    @Test
    void EventTest() throws NoSuchFieldException {
        userService.test();
    }

    @Test
    void Test() {
        userService.test2();
    }

    @Test
    void TestCustomer() throws NoSuchFieldException {
//        customerRepositry.FindSetWithFilters("accountStatus,customerType,paymentInformation","WHERE accountStatus = Active AND customerType = Retail AND paymentInformation = PayPal");
    }

    @Test
    void test2() {
        System.out.println(customerRepositry.test());
    }
}
