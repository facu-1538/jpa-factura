package org.example;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@ToString
@Setter
@Getter


@Entity
public class Cliente  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nombre;

    private String apellido;

    //@Column(unique = true)
    private int dni;

    @OneToOne(cascade = CascadeType.ALL)

    @JoinColumn(name = "fk_domicilio")

    private Domicilio domicilio;

}
