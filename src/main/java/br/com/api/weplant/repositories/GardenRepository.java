package br.com.api.weplant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.weplant.entities.Garden;

public interface GardenRepository extends JpaRepository<Garden, Long> {

    Garden findByUserId(Long id);

}
