package com.example.demo.request;

import lombok.Data;

@Data
public class AddUserRequest {
    private String name;
    private String email;
    private String password;
    private Boolean isAdmin;
}
