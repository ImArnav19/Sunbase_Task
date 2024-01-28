package com.sb04.employee.entity;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtRequest {

    private String email;
    private String password;
}
