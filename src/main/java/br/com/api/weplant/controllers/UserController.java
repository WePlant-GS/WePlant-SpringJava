package br.com.api.weplant.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.api.weplant.dto.GardenDTO;
import br.com.api.weplant.dto.TokenJWT;
import br.com.api.weplant.dto.UserLogin;
import br.com.api.weplant.dto.UserNoProtectedDataDTO;
import br.com.api.weplant.dto.UserRegisterDTO;
import br.com.api.weplant.entities.Garden;
import br.com.api.weplant.entities.User;
import br.com.api.weplant.services.GardenService;
import br.com.api.weplant.services.TokenService;
import br.com.api.weplant.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GardenService gardenService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/all")
    public ResponseEntity<List<UserNoProtectedDataDTO>> findAll() {
        List<User> users = userService.findAll();
        List<UserNoProtectedDataDTO> usersDTO = users.stream().map(UserNoProtectedDataDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserNoProtectedDataDTO> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserNoProtectedDataDTO(user));
    }

    @PostMapping("/add")
    public ResponseEntity<User> insert(@RequestBody UserRegisterDTO userRegisterDTO) {
        User user = userService.fromDTORegister(userRegisterDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(userService.findAll().size()).toUri();
        return ResponseEntity.created(uri).body(user);
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

    @PostMapping("/login")
    public ResponseEntity<TokenJWT> login(@RequestBody @Valid UserLogin data) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                data.username(),
                data.password());

        Authentication autentication = manager.authenticate(authenticationToken);

        String tokenJWT = tokenService.gerarToken((User) autentication.getPrincipal());

        return ResponseEntity.ok().body(new TokenJWT(tokenJWT));
    }

    @PostMapping("/teste")
    public ResponseEntity<User> checkUsername(@RequestBody UserLogin username) {
        return ResponseEntity.ok().body(userService.findByUsername(username.username()));
    }

}
