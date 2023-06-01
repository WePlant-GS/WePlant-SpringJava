package br.com.api.weplant.dto;


import br.com.api.weplant.entities.Address;
import br.com.api.weplant.entities.Phone;
import br.com.api.weplant.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Calendar;


public record UserNoProtectedDataDTO(

     String name,

     @JsonFormat(pattern = "dd/MM/yyyy")
     Calendar birthday,

     String username,

     String email,

     String status,

     Address address,

     Phone phone) {

    public UserNoProtectedDataDTO(User user) {
       this(user.getName(), user.getBirthday(), user.getUsername(), user.getEmail(), user.getStatus(), user.getAddress(), user.getPhone());
    }

}
