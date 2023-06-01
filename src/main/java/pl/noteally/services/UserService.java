package pl.noteally.services;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.noteally.data.Role;
import pl.noteally.data.User;
import pl.noteally.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptpasswordEncoder;

    @Autowired
    private HttpSession httpSession;
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public void signUpUser(User user){
        boolean userExists = userRepository.findByLogin(user.getLogin()).isPresent();

        if(userExists){
            throw new IllegalStateException("Login already taken");
        }

        String encodedPassword = bCryptpasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        user.setRole(Role.USER);

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(login);
        if(user.isPresent())
        {
            httpSession.setAttribute("userId", user.get().getId());
            httpSession.setMaxInactiveInterval(100);
            return user.get();
        }
        else throw new UsernameNotFoundException("Niepoprawne hasło lub nazwa użytkownika");
    }
}