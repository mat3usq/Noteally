package pl.noteally.services;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.noteally.data.Role;
import pl.noteally.data.User;
import pl.noteally.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptpasswordEncoder;
    private final HttpSession httpSession;
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
            httpSession.setAttribute("userId", user.get().getId());
            return user.get();
        }
        else throw new UsernameNotFoundException("Niepoprawne hasło lub nazwa użytkownika");
    }

    public void updateUser(User user, Integer userId)
    {
        Optional<User> existingUser = userRepository.findById(userId);
        existingUser.get().setLogin(user.getLogin());
        existingUser.get().setName(user.getName());
        existingUser.get().setSurname(user.getSurname());
        existingUser.get().setAge(user.getAge());
        existingUser.get().setRole(user.getRole());
        userRepository.save(existingUser.get());
    }

    public void deleteUserById(Integer userId)
    {
        userRepository.deleteById(userId);
    }
}
