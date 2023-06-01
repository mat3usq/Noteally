package pl.noteally.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.noteally.data.User;


public interface UserRepository extends JpaRepository<User, Integer> {
}
