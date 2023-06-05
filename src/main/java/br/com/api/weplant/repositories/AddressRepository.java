package br.com.api.weplant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.weplant.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
