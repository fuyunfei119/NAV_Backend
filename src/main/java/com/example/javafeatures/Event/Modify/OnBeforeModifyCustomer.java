package com.example.javafeatures.Event.Modify;

import org.springframework.context.ApplicationEvent;

public class OnBeforeModifyCustomer extends ApplicationEvent {
    public OnBeforeModifyCustomer(Object source) {
        super(source);
    }
}
