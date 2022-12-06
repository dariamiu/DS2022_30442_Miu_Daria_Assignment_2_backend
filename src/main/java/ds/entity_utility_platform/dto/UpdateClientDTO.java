package ds.entity_utility_platform.dto;

public class UpdateClientDTO {


    private String oldUsername;

    private String newUsername;
    private String password;

    private int passwordChanged;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UpdateClientDTO(String username) {
        this.oldUsername = username;
    }

    public UpdateClientDTO(String oldUsername, String newUsername, String password, int passwordChanged) {
        this.oldUsername = oldUsername;
        this.newUsername = newUsername;
        this.password = password;
        this.passwordChanged = passwordChanged;
    }

    public UpdateClientDTO() {
    }

    public String getOldUsername() {
        return oldUsername;
    }

    public void setOldUsername(String oldUsername) {
        this.oldUsername = oldUsername;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUserName) {
        this.newUsername = newUserName;
    }

    public int getPasswordChanged() {
        return passwordChanged;
    }

    public void setPasswordChanged(int passwordChanged) {
        this.passwordChanged = passwordChanged;
    }
}
