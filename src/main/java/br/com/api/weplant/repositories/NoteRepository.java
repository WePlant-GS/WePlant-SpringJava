package br.com.api.weplant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.weplant.entities.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

}
