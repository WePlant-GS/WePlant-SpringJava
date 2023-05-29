package br.com.api.weplant.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "tb_wp_user")
@SequenceGenerator(name = "userSequence", sequenceName = "SQ_TB_WP_USER",allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
    private Long id;

    @Column(name = "compl_name", length = 30, nullable = false)
    private String name;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Calendar birthday;

    @Column(name = "username", length = 20, nullable = false)
    private String username;

    @Column(name = "user_email", length = 15)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_status", length = 1, nullable = false)
    private Character status;

    @Embedded
    private Address address;

    @Embedded
    private Phone phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Garden> gardenList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> postList;

}
