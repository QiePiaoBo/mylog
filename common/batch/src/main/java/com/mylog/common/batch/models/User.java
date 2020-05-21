package com.mylog.common.batch.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String username;
    String password;
    int age;

    public User(int id, String username, int age, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public User() {
    }
}