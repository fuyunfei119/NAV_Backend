package com.example.javafeatures;

import com.example.javafeatures.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserEventTest {

    private UserService userService;

    @Autowired
    public UserEventTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    void EventTest() throws NoSuchFieldException {
        userService.test();
    }

    @Test
    void Test() {
        userService.test2();
    }
}
