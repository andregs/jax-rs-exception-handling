package com.andregs.rest.ping;

import javax.validation.constraints.NotNull;

/**
 * We use this bean to test validation and JSON exceptions.
 */
public class Credentials {
    
    @NotNull
    private String username;    
    
    @NotNull
    private String password;

    public Credentials() {
    }

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
