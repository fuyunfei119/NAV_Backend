package com.example.javafeatures.Listener;

import com.example.javafeatures.Event.Insert.OnBeforeInsertCustomer;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OnBeforeCreateUserEventListenner implements ApplicationListener<OnBeforeInsertCustomer> {
    @Override
    public void onApplicationEvent(OnBeforeInsertCustomer event) {
    }
}
