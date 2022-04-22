package com.demo.userservice.domain;


import javax.persistence.*;



@Entity
public class User {

    private String email;
    private String name;
    private String pwd;

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    


}