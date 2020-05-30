package com.mylog.common.batch.models;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class Person implements Serializable {

    private final long serialVersionUID = 1L;

    private Integer id;
    @Size(min = 2, max = 8)
    private String name;
    private int age;
    private String gender;

    public Person(Integer id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    public Person(){

    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
