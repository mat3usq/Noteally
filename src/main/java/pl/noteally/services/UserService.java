package pl.noteally.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.noteally.data.User;
import pl.noteally.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }
}

