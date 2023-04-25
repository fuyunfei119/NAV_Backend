package com.example.javafeatures.Event.Validate;

import org.springframework.context.ApplicationEvent;

public class OnValidateEvent extends ApplicationEvent {
    public OnValidateEvent(Object source) {
        super(source);
    }
}
