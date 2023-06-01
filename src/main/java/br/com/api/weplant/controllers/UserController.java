package br.com.api.weplant.controllers;

import br.com.api.weplant.dto.GardenDTO;
import br.com.api.weplant.dto.UserRegisterDTO;
import br.com.api.weplant.dto.UserNoProtectedDataDTO;
import br.com.api.weplant.entities.Garden;
import br.com.api.weplant.entities.User;
import br.com.api.weplant.services.GardenService;
import br.com.api.weplant.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GardenService gardenService;

    @GetMapping("/all")
    public ResponseEntity<List<UserNoProtectedDataDTO>> findAll() {
        List<User> users = userService.findAll();
        List<UserNoProtectedDataDTO> usersDTO = users.stream().map(UserNoProtectedDataDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserNoProtectedDataDTO> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserNoProtectedDataDTO(user));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> insert(@RequestBody UserRegisterDTO userRegisterDTO) {
        User user = userService.fromDTORegister(userRegisterDTO);
        userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(userService.findAll().size()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/updt/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid UserNoProtectedDataDTO newUser, @PathVariable Long id) {
        User user = userService.fromDTOResponse(newUser);
        userService.update(user, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/garden/add")
    public ResponseEntity<Void> addGarden(@RequestBody GardenDTO gardenDTO, @PathVariable Long id) {
        Garden garden = gardenService.fromDTO(gardenDTO);
        userService.addNewGarden(garden, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/garden")
    public ResponseEntity<GardenDTO> findGardenByUserID(@PathVariable Long id) {
        Garden garden = gardenService.findByUserId(id);
        return ResponseEntity.ok().body(new GardenDTO(garden));
    }

}
