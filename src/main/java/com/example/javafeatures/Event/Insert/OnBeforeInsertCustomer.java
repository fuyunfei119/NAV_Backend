package com.example.javafeatures.Event.Insert;

import org.springframework.context.ApplicationEvent;

public class OnBeforeInsertCustomer extends ApplicationEvent {

    public OnBeforeInsertCustomer(Object source) {
        super(source);
    }
}
