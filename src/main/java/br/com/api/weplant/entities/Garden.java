package br.com.api.weplant.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.api.weplant.dto.GardenDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "tb_wp_garden")
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "garden_name", length = 50, nullable = false)
    private String name;

    @Column(nullable = false,length = 15)
    private String status;

    @Column(nullable = false,length = 50)
    private String plant;

    @Column(length = 1)
    private String type;//Pode ser V (Vertical) ou H (Horizontal)

    @OneToMany(mappedBy = "user")
    private List<Note> notes;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false)
    @JsonIgnore
    private User user;

    public Garden(GardenDTO gardenRegister) {
        this.name = gardenRegister.name();
        this.status = gardenRegister.status();
        this.plant = gardenRegister.plant();
        this.type = gardenRegister.type();
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

}
