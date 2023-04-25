package com.example.javafeatures.Event.Insert;

import com.example.javafeatures.Entity.Customer;
import org.springframework.context.ApplicationEvent;

public class OnAfterCreateCustomer extends ApplicationEvent {

    private Customer customer;
    
    public OnAfterCreateCustomer(Object source) {
        super(source);
    }

    public OnAfterCreateCustomer(Object source, Customer customer) {
        super(source);
        this.customer = customer;
    }

    public Customer getUser() {
        return customer;
    }

    public void setUser(Customer customer) {this.customer = customer;}
}
