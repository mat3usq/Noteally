package pl.noteally.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.noteally.data.User;

@Service
@AllArgsConstructor
public class Log_Reg_Service {
    private final UserService userService;
    public String register(User user) {

        return null;
    }
}
