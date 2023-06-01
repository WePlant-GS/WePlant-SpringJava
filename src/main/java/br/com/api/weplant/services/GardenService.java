package br.com.api.weplant.services;

import br.com.api.weplant.dto.GardenDTO;
import br.com.api.weplant.entities.Garden;
import br.com.api.weplant.exceptions.NoDataFoundException;
import br.com.api.weplant.repositories.GardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GardenService {

    @Autowired
    private GardenRepository gardenRepository;

    public List<Garden> findAll() {
        return gardenRepository.findAll();
    }

    public Garden findById(Long id) {
        return gardenRepository.findById(id).orElseThrow((() -> new NoDataFoundException("Garden with id " + id + " not found")));
    }

    public Garden insert(Garden garden) {
        return gardenRepository.save(garden);
    }

    public void update(Garden garden, Long id) {
        Garden gardenInDB = findById(id);
        dataUpdate(gardenInDB, garden);
        gardenRepository.save(gardenInDB);
        gardenRepository.flush();
    }

    public void delete(Long id) {
        gardenRepository.deleteById(id);
    }

    public Garden findByUserId(Long id) {
        return gardenRepository.findByUserId(id);
    }

    private void dataUpdate(Garden gardenToAtt, Garden garden) {
        String name = (garden.getName() != null && garden.getName().isEmpty() && garden.getName().isBlank()) ? garden.getName() : gardenToAtt.getName();
        gardenToAtt.setName(name);

        String status = (garden.getStatus() != null && garden.getStatus().isEmpty() && garden.getStatus().isBlank()) ? garden.getStatus() : gardenToAtt.getStatus();
        gardenToAtt.setStatus(status);

        String plant = (garden.getPlant() != null && garden.getPlant().isEmpty() && garden.getPlant().isBlank()) ? garden.getPlant() : gardenToAtt.getPlant();
        gardenToAtt.setPlant(plant);

        Character type = (garden.getType() != null && garden.getType().toString().isBlank() && garden.getType().toString().isEmpty()) ? garden.getType() : gardenToAtt.getType();
        gardenToAtt.setType(type);

    }

    public Garden fromDTO(GardenDTO gardenDTO) {
        return new Garden(gardenDTO);
    }

//    public User fromDTOResponse(UserNoProtectedDataDTO userNoProtectedDataDTO) {
//        return new User(userNoProtectedDataDTO);
//    }

}
