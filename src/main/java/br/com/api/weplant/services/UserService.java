package br.com.api.weplant.services;

import br.com.api.weplant.dto.UserRegister;
import br.com.api.weplant.dto.UserNoProtectedDataDTO;
import br.com.api.weplant.entities.Address;
import br.com.api.weplant.entities.Phone;
import br.com.api.weplant.entities.User;
import br.com.api.weplant.exceptions.NoDataFoundException;
import br.com.api.weplant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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
        User user = findById(id);
        user.setStatus('I');
        userRepository.save(user);
    }

    public void dataUpdate(User userToAtt, User user) {
        String name = (user.getName() != null && user.getName().isEmpty() && user.getName().isBlank()) ? user.getName() : userToAtt.getName();
        userToAtt.setName(name);

        String Username = (user.getUsername() != null && user.getUsername().isEmpty() && user.getUsername().isBlank()) ? user.getUsername() : userToAtt.getUsername();
        userToAtt.setUsername(Username);

        Calendar calendar = user.getBirthday() != null ? user.getBirthday() : userToAtt.getBirthday();
        userToAtt.setBirthday(calendar);

        Address address = user.getAddress() != null ? user.getAddress() : userToAtt.getAddress();
        userToAtt.setAddress(address);

        Phone phone = user.getPhone() != null ? user.getPhone() : userToAtt.getPhone();
        userToAtt.setPhone(phone);

    }

    public User fromDTORegister(UserRegister userRegister) {
        return new User(userRegister);
    }

    public User fromDTOResponse(UserNoProtectedDataDTO userNoProtectedDataDTO) {
        return new User(userNoProtectedDataDTO);
    }
}
