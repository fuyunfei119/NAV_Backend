package com.example.javafeatures.Event;

import org.springframework.context.ApplicationEvent;

public class OnBeforeCreateUser extends ApplicationEvent {

    public OnBeforeCreateUser(Object source) {
        super(source);
    }
}
