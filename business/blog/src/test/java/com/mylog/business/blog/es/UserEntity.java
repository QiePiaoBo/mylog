package com.mylog.business.blog.es;

/**
 * @author Dylan
 * @Description UserEntity
 * @Date : 2022/12/10 - 16:33
 */
public class UserEntity {

    private Long id;

    private String name;

    private String sex;

    private String age;

    private String email;

    public UserEntity() {
    }

    public UserEntity(Long id, String name, String sex, String age, String email) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
