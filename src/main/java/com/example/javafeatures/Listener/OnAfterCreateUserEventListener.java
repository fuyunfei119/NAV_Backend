package com.example.javafeatures.Listener;

import com.example.javafeatures.Event.Insert.OnAfterCreateCustomer;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OnAfterCreateUserEventListener implements ApplicationListener<OnAfterCreateCustomer> {

    @Override
    public void onApplicationEvent(OnAfterCreateCustomer event) {
        System.out.println("OnAfterCreateUser " + event.getUser());
    }
}
