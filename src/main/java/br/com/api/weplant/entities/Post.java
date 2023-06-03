package br.com.api.weplant.entities;

import br.com.api.weplant.dto.PostReducedDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "tb_wp_post")
@SequenceGenerator(name = "postSequence", sequenceName = "SQ_TB_WP_POST",allocationSize = 1)
public class Post {

    @Id
    @GeneratedValue(generator = "postSequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    @Column(length = 200)
    private String description;

    @Column(name = "post_date", nullable = false)
    private Calendar date;

    @ManyToOne(cascade = CascadeType.DETACH)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.MERGE)
    private List<Comment> comments;

    public Post(PostReducedDTO postReducedDTO) {
        this.image = postReducedDTO.image();
        this.description = postReducedDTO.description();
        this.date = Calendar.getInstance();
    }

}
