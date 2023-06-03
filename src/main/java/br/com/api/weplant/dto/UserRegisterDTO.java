package br.com.api.weplant.dto;

import br.com.api.weplant.entities.Address;
import br.com.api.weplant.entities.Phone;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Calendar;

public record UserRegisterDTO(

        @NotNull @NotBlank @NotNull
        String name,

        @NotNull @NotBlank
        @JsonFormat(pattern = "dd/MM/yyyy")
        Calendar birthday,

        @NotNull @NotBlank
        String username,

        @NotNull @NotBlank
        String email,

        @NotNull @NotBlank
        String password,

        @NotNull @NotBlank
        Address address,

        @NotNull @NotBlank
        Phone phone
) {}
