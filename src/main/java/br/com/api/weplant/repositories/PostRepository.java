package br.com.api.weplant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.weplant.entities.Post;


public interface PostRepository extends JpaRepository<Post, Long> {
}
