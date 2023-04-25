package com.example.javafeatures.Event.Delete;

import org.springframework.context.ApplicationEvent;

public class OnBeforeDeleteCustomer extends ApplicationEvent {
    public OnBeforeDeleteCustomer(Object source) {
        super(source);
    }
}
