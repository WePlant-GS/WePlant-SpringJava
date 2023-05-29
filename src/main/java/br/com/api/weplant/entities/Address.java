package br.com.api.weplant.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Column(length = 6, name = "house_number")
    private String number;

    @Column(length = 25)
    private String street;

    @Column(length = 25)
    private String neighborhood;

    @Column(length = 25)
    private String city;

    @Column(length = 25)
    private String state;

    @Column(length = 40)
    private String country;
    
}
