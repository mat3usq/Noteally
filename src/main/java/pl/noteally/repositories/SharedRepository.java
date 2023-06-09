package pl.noteally.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.noteally.data.Catalog;
import pl.noteally.data.Note;
import pl.noteally.data.SharedNote;

import java.util.List;

public interface SharedRepository  extends JpaRepository<SharedNote, Integer> {

    @Query
            ("SELECT n FROM SharedNote n WHERE n.user.id = ?1")
    List<SharedNote> findByUserId(int userId);

    @Query
            ("SELECT n FROM SharedNote n WHERE n.user.id = ?1 AND n.note.id = ?2")
    List<SharedNote> findByUserIdAndNoteId(int userId, int noteId);

    @Query
            ("SELECT n FROM SharedNote n WHERE n.user.id = ?1 OR n.note.catalog.user.id = ?1")
    List<SharedNote> findRelatedNotes(int userId);
}
