package pl.noteally.services;

import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.noteally.data.Role;
import pl.noteally.data.User;
import pl.noteally.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptpasswordEncoder;
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public String signUpUser(User user){
        boolean userExists = userRepository.findByLogin(user.getLogin()).isPresent();

        if(userExists){
            throw new IllegalStateException("Login already taken");
        }

        String encodedPassword = bCryptpasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        user.setRole(Role.USER);

        userRepository.save(user);
        return "catalogs";
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(login);
        if(user.isPresent())
        {
            return user.get();
        }
        else throw new UsernameNotFoundException("Niepoprawne hasło lub nazwa użytkownika");
    }
}

