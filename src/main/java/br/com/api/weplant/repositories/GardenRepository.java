package br.com.api.weplant.repositories;

import br.com.api.weplant.entities.Garden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GardenRepository extends JpaRepository<Garden, Long> {

    List<Garden> findAllByUserId(Long id);
  
    @Query("from Garden g where g.user.id = :id")
    Garden findByUserId(@Param("id") Long id);

}