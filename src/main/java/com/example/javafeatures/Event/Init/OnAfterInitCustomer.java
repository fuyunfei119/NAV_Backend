package com.example.javafeatures.Event.Init;

import com.example.javafeatures.Entity.Customer;
import org.springframework.context.ApplicationEvent;

public class OnAfterInitCustomer extends ApplicationEvent {

    private Boolean IsHandled;

    public OnAfterInitCustomer(Object source) {
        super(source);
    }

    public OnAfterInitCustomer(Object source, Boolean isHandled) {
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
