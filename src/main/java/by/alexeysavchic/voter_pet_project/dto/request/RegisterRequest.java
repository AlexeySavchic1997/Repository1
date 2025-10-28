package by.alexeysavchic.voter_pet_project.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest
{

    @NotBlank
    @Size(min = 2, max = 30, message = "username must be between 2 and 30 symbols")
    private String username;

    @NotBlank
    @Size(min = 6, max = 15, message = "password must be between 6 and 15 symbols")
    private String password;

    @NotBlank
    @Email(message = "wrong email pattern")
    private String email;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
