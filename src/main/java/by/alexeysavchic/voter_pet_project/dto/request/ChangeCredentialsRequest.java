package by.alexeysavchic.voter_pet_project.dto.request;

public class ChangeCredentialsRequest
{
    String username;

    String email;

    String oldPassword;

    String newPassword;

    public ChangeCredentialsRequest() {
    }

    public ChangeCredentialsRequest(String username, String email, String oldPassword, String newPassword) {
        this.username = username;
        this.email = email;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
