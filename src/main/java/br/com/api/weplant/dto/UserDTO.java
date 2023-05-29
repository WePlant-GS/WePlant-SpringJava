package br.com.api.weplant.dto;


import br.com.api.weplant.entities.Address;
import br.com.api.weplant.entities.Phone;
import br.com.api.weplant.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private String name;

    private Calendar birthday;

    private String username;

    private Character status;

    private Address address;

    private Phone phone;

    public UserDTO(User user) {
        this.name = user.getName();
        this.birthday = user.getBirthday();
        this.username = user.getUsername();
        this.status = user.getStatus();
        this.address = user.getAddress();
        this.phone = user.getPhone();
    }
}
