package br.com.api.weplant.services;

import br.com.api.weplant.dto.UserDTO;
import br.com.api.weplant.entities.User;
import br.com.api.weplant.exceptions.NoDataFoundException;
import br.com.api.weplant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow((() -> new NoDataFoundException("User with id " + id + " not found")));
    }

    public User insert(User user) {
        return userRepository.save(user);
    }

    public void update(User user, Long id) {
        User userInDb = findById(id);
        dataUpdate(userInDb, user);
        userRepository.save(userInDb);
        userRepository.flush();
    }

    public void delete(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public void dataUpdate(User userToAtt, User user) {
        userToAtt.setName(user.getName());
        userToAtt.setBirthday(user.getBirthday());
        userToAtt.setAddress(user.getAddress());
        userToAtt.setPhone(user.getPhone());
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(
                userDTO.getName(),
                userDTO.getBirthday(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getStatus(),
                userDTO.getAddress(),
                userDTO.getPhone()
        );
    }
}
