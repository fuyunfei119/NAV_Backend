package com.example.javafeatures.Event.Init;

import org.springframework.context.ApplicationEvent;

public class OnBeforeInitCustomer extends ApplicationEvent {
    private Boolean IsHandled;

    public OnBeforeInitCustomer(Object source) {
        super(source);
    }

    public OnBeforeInitCustomer(Object source, Boolean isHandled) {
        super(source);
        IsHandled = isHandled;
    }

    public Boolean getHandled() {
        return IsHandled;
    }

    public void setHandled(Boolean handled) {
        IsHandled = handled;
    }
}
