package br.com.api.weplant.entities;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_wp_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String body;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(nullable = false)
    private Post post;

    @Column(nullable = false)
    private LocalDate date_comment; 
}
