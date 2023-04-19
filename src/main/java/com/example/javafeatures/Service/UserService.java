package com.example.javafeatures.Service;

import com.example.javafeatures.Entity.User;
import com.example.javafeatures.Enum.UserFields;
import com.example.javafeatures.Event.OnAfterCreateUser;
import com.example.javafeatures.Event.OnBeforeCreateUser;
import com.example.javafeatures.Repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
public class UserService implements ApplicationEventPublisherAware {

    @Autowired
    private UserRepositry userRepositry;
    @Autowired
    private UserRepositry userRepositry2;
    private ApplicationEventPublisher publisher;

    public User getOne() {

        publisher.publishEvent(new OnBeforeCreateUser(this));

        User user = new User("YunfeiFu", 25,1L);
        user.setAge(26);

        publisher.publishEvent(new OnAfterCreateUser(this,user));

        return user;
    }


    public void test() throws NoSuchFieldException {
        userRepositry
                .SetRange(UserFields.id, 10000)
                .SetRange(UserFields.age, 23)
                .SetRange(UserFields.userName, "YunfeiFU")
                .FindSet();

        System.out.println(userRepositry2.GetFilters());
    }

    public void test2() {

        System.out.println(userRepositry);
        System.out.println(userRepositry2);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
