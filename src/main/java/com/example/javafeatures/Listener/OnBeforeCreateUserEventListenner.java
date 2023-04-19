package com.example.javafeatures.Listener;

import com.example.javafeatures.Event.OnBeforeCreateUser;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OnBeforeCreateUserEventListenner implements ApplicationListener<OnBeforeCreateUser> {
    @Override
    public void onApplicationEvent(OnBeforeCreateUser event) {
        System.out.println("OnBeforeCreateUser....");
    }
}
