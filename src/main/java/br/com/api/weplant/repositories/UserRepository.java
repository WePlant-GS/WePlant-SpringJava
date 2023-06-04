package br.com.api.weplant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.weplant.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
