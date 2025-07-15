package com.example.workshopMongo.dto;

import com.example.workshopMongo.domain.User;

public class UserDto {

    private String id;
    private String name;
    private String email;

    public UserDto(){

    }

    public UserDto(User obj){
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
