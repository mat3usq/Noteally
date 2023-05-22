package pl.noteally.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.noteally.data.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

}
