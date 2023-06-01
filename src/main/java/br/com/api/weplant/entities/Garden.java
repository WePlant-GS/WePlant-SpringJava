package br.com.api.weplant.entities;

import br.com.api.weplant.dto.GardenDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "tb_wp_garden")
@SequenceGenerator(name = "gardenSequence", sequenceName = "SQ_TB_WP_GARDEN",allocationSize = 1)
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gardenSequence")
    private Long id;

    @Column(name = "garden_name", length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String plant;

    @Column(length = 1)
    private Character type;//Pode ser V (Vertical) ou H (Horizontal)

    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonIgnore
    private User user;

    public Garden(GardenDTO gardenRegister) {
        this.name = gardenRegister.name();
        this.status = gardenRegister.status();
        this.plant = gardenRegister.plant();
        this.type = gardenRegister.type();
    }
}
