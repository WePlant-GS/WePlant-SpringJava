package br.com.api.weplant.repositories;

import br.com.api.weplant.entities.Garden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GardenRepository extends JpaRepository<Garden, Long> {

}
