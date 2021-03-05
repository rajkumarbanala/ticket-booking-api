package com.example.demo.security.dto;

import javax.validation.constraints.NotNull;

import lombok.ToString;

@ToString
public class LoginUserDTO {

	@NotNull
    private String username;
	
	@NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
