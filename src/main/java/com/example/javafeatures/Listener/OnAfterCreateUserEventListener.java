package com.example.javafeatures.Listener;

import com.example.javafeatures.Event.OnAfterCreateUser;
import com.example.javafeatures.Event.OnBeforeCreateUser;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OnAfterCreateUserEventListener implements ApplicationListener<OnAfterCreateUser> {

    @Override
    public void onApplicationEvent(OnAfterCreateUser event) {
        System.out.println("OnAfterCreateUser " + event.getUser());
    }
}
