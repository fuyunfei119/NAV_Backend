package com.example.javafeatures.Event;

import com.example.javafeatures.Entity.User;
import org.springframework.context.ApplicationEvent;

public class OnAfterCreateUser extends ApplicationEvent {

    private User user;
    
    public OnAfterCreateUser(Object source) {
        super(source);
    }

    public OnAfterCreateUser(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {this.user = user;}
}
