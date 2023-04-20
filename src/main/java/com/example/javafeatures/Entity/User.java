package com.example.javafeatures.Entity;


public class User {
    private String userName;
    private Integer age;
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
