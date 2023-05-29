package br.com.api.weplant.repositories;

import br.com.api.weplant.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
