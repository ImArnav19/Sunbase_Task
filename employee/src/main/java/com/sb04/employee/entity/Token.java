package com.sb04.employee.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;



public class Token {



    private String access_token;
    public Token(){

    }



    public void setAccess_token(String access_token) {

        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }
}
