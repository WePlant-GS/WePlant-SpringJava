package br.com.api.weplant.repositories;

import br.com.api.weplant.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
