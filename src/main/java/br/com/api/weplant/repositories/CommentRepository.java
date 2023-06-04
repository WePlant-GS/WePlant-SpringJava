package br.com.api.weplant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.weplant.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
