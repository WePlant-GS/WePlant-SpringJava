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
@Table(name = "tb_wp_phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3)
    private String DDI;

    @Column(length = 3)
    private String DDD;

    @Column(length = 12)
    private String number;

    @OneToOne(mappedBy = "phone")
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
