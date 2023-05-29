package br.com.api.weplant.controllers;

import br.com.api.weplant.dto.UserDTO;
import br.com.api.weplant.entities.User;
import br.com.api.weplant.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();
        List<UserDTO> usersDTO = users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @PutMapping("/updt")
    public ResponseEntity<Void> update(@RequestBody @Valid UserDTO newUser, @PathVariable Long id) {
        User user = userService.fromDTO(newUser);
        userService.update(user, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
