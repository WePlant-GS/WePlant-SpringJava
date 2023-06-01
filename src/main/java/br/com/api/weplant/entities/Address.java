package br.com.api.weplant.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @OneToOne(mappedBy = "address")
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    
}
