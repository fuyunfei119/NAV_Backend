package com.example.javafeatures;

import com.example.javafeatures.Enum.CustomerFields;
import com.example.javafeatures.Repositry.CustomerRecord;
import com.example.javafeatures.Repositry.Mapper.CustomerRepositry;
import com.example.javafeatures.Service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class UserEventTest {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRecord customerRecord;

    @Autowired
    private CustomerRepositry customerRepositry;

    @Test
    void TypeTest() {
        System.out.println(customerRepositry.Test());
    }

    @Test
    @Transactional
    void EventTest() {
        customerRecord
                .Init()
                .Insert(true,true);
    }
}
