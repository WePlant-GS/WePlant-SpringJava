package br.com.api.weplant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.api.weplant.entities.Garden;

public interface GardenRepository extends JpaRepository<Garden, Long> {

    @Query("from Garden g where g.user.id = :id")
    Garden findByUserId(@Param("id") Long id);

}
