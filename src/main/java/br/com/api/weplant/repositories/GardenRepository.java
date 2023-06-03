package br.com.api.weplant.repositories;

import br.com.api.weplant.entities.Garden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GardenRepository extends JpaRepository<Garden, Long> {

    List<Garden> findAllByUserId(Long id);

}
