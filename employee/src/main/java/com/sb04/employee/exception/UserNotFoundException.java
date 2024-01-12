package com.sb04.employee.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String id){
        super("Cound not Found the user with id : "+id);
    }


}
