package com.sb04.employee.entity;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtResponse {
    private String jwtToken;
    private String username;
}
