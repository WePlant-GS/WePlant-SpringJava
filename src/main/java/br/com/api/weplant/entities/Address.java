package br.com.api.weplant.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_wp_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 6, name = "house_number")
    private String number;

    @Column(length = 50, nullable = false)
    private String street;

    @Column(length = 50, nullable = false)
    private String neighborhood;

    @Column(length = 50, nullable = false)
    private String city;

    @Column(length = 50, nullable = false)
    private String state;

    @Column(length = 8, nullable = false)
    private String zip_code;

    @Column(length = 50, nullable = false)
    private String country;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private User user;

}
