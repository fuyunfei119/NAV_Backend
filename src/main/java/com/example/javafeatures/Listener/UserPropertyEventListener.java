package com.example.javafeatures.Listener;

import com.example.javafeatures.Entity.User;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;


@Component
public class UserPropertyEventListener {

    @PrePersist
    void onPrePersist(User user) {
        System.out.println("userListener.onPrePersist(): " + user);
    }
    @PostPersist
    void onPostPersist(User user) {
        System.out.println("userListener.onPostPersist(): " + user);
    }
    @PostLoad
    void onPostLoad(User user) {
        System.out.println("userListener.onPostLoad(): " + user);
    }
    @PreUpdate
    void onPreUpdate(User user) {
        System.out.println("userListener.onPreUpdate(): " + user);
    }
    @PostUpdate
    void onPostUpdate(User user) {
        System.out.println("userListener.onPostUpdate(): " + user);
    }
    @PreRemove
    void onPreRemove(User user) {
        System.out.println("userListener.onPreRemove(): " + user);
    }
    @PostRemove
    void onPostRemove(User user) {
        System.out.println("userListener.onPostRemove(): " + user);
    }

}
