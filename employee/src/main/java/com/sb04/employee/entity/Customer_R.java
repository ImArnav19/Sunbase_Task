package com.sb04.employee.entity;

import jakarta.persistence.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class Customer_R {


    private String uuid;


    @Setter
    @Getter
    private String first_name;


    @Setter
    @Getter
    private String last_name;


    @Setter
    @Getter
    private String street;


    @Setter
    @Getter
    private String address;


    @Setter
    @Getter
    private String city;


    @Setter
    @Getter
    private String state;


    @Setter
    @Getter
    private String email;


    @Setter
    @Getter
    private String phone;

    public Customer_R(){

    }

    public String getId() {
        return this.uuid;
    }

    public void setId(String uuid) {
        this.uuid = uuid;
    }

}
