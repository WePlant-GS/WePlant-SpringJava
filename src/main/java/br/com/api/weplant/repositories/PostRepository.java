package br.com.api.weplant.repositories;

import br.com.api.weplant.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
