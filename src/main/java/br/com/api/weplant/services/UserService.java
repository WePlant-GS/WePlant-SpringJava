package br.com.api.weplant.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.weplant.dto.UserNoProtectedDataDTO;
import br.com.api.weplant.dto.UserRegisterDTO;
import br.com.api.weplant.entities.Address;
import br.com.api.weplant.entities.Garden;
import br.com.api.weplant.entities.Phone;
import br.com.api.weplant.entities.User;
import br.com.api.weplant.exceptions.NoDataFoundException;
import br.com.api.weplant.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GardenService gardenService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PhoneService phoneService;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow((() -> new NoDataFoundException("User with id " + id + " not found")));
    }

    public User insert(User user) {
        addressService.insert(user.getAddress());
        phoneService.insert(user.getPhone());
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

    public void addNewGarden(Garden garden, Long id) {
        User user = findById(id);
        user.addGarden(garden);
        userRepository.save(user);
        userRepository.flush();
        gardenService.insert(garden);
    }

    public void dataUpdate(User userToAtt, User user) {
        String name = (user.getName() != null && user.getName().isEmpty() && user.getName().isBlank()) ? user.getName() : userToAtt.getName();
        userToAtt.setName(name);

        String Username = (user.getUsername() != null && user.getUsername().isEmpty() && user.getUsername().isBlank()) ? user.getUsername() : userToAtt.getUsername();
        userToAtt.setUsername(Username);

        LocalDate calendar = user.getBirthday() != null ? user.getBirthday() : userToAtt.getBirthday();
        userToAtt.setBirthday(calendar);

        Address address = user.getAddress() != null ? user.getAddress() : userToAtt.getAddress();
        userToAtt.setAddress(address);

        Phone phone = user.getPhone() != null ? user.getPhone() : userToAtt.getPhone();
        userToAtt.setPhone(phone);

    }

    public User fromDTORegister(UserRegisterDTO userRegisterDTO) {
        return new User(userRegisterDTO);
    }

    public User fromDTOResponse(UserNoProtectedDataDTO userNoProtectedDataDTO) {
        return new User(userNoProtectedDataDTO);
    }
}
