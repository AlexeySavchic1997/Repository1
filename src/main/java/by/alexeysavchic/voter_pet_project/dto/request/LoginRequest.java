package by.alexeysavchic.voter_pet_project.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest
{
    @NotBlank
    @Size(min = 2, max = 30, message = "username must be between 2 and 30 symbols")
    private String username;
    @NotBlank
    @Size(min = 6, max = 15, message = "password must be between 6 and 15 symbols")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
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
