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
public class Phone {

    @Column(length = 3)
    private String DDI;

    @Column(length = 3)
    private String DDD;

    @Column(length = 12)
    private String number;

}
