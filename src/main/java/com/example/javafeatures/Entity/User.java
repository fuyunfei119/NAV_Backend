package com.example.javafeatures.Entity;

import com.example.javafeatures.Enum.UserFields;
import com.example.javafeatures.Listener.UserPropertyEventListener;
import com.mysql.cj.protocol.a.ColumnDefinitionFactory;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import org.hibernate.internal.util.PropertiesHelper;
import org.hibernate.mapping.Column;
import org.hibernate.tool.schema.extract.spi.ForeignKeyInformation;
import org.hibernate.type.EnumType;
import org.springframework.beans.propertyeditors.PropertiesEditor;

@EntityListeners(UserPropertyEventListener.class)
public class User {
    private String userName;
    private Integer age;
    @Id
    private Long id;

    public static String username;

    public User() {
    }

    public User(String userName, Integer age, Long id) {
        this.userName = userName;
        this.age = age;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
