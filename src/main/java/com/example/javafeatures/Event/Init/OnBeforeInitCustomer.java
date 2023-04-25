package com.example.javafeatures.Event.Init;

import com.example.javafeatures.Entity.Customer;
import com.example.javafeatures.Repositry.CustomerRecord;
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
