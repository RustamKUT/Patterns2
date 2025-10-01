package netology.ru.data;

import lombok.Data;
import lombok.Value;

@Value
public class RegistrationInfo {
    private final String login;
    private final String password;
    private final String status;

    public RegistrationInfo(String login, String password, String status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public String getLogin() {
        return null;
    }

    public String getPassword() {
        return null;
    }

    public String getStatus() {
        return null;
    }
}