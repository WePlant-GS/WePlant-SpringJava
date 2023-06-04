package br.com.api.weplant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.weplant.entities.Phone;


public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
