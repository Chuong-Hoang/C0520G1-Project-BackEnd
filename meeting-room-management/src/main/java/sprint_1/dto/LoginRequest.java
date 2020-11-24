package sprint_1.dto;

import javax.validation.constraints.NotEmpty;

/**
 * LoginRequest
 *
 * Version 1.0
 *
 * Date: 24/11/2020
 *
 * Copyright
 *
 * Author: Le Toan
 */
public class LoginRequest {
    @NotEmpty
    private String username;
    @NotEmpty
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
