package br.com.api.weplant.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull @NotBlank
    @JsonFormat(pattern = "nnnnn-nnn")
    private String CEP;

    @Column(length = 6, name = "house_number")
    private String number;

    @Column(length = 50)
    private String street;

    @Column(length = 50)
    private String neighborhood;

    @Column(length = 50)
    private String city;

    @Column(length = 50)
    private String state;

    @Column(length = 50)
    private String country;

    @OneToOne(mappedBy = "address")
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    
}
