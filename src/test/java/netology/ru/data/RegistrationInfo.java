package netology.ru.data;

import lombok.Data;
import lombok.Value;

@Data
public class RegistrationInfo {
    private final String login;
    private final String password;
    private final String status;
}
