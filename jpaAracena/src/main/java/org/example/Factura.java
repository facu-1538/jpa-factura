package org.example;

import lombok.*;

import javax.persistence.*;
import java.io.CharArrayReader;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Builder

@Entity
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String fecha;

    private int numero;

    private int total;


    @ManyToOne(cascade = CascadeType.PERSIST)

    @JoinColumn(name = "fk_clientes")

    private Cliente cliente;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)

    @JoinColumn(name = "factura_id")
@Builder.Default
    private Set<DetalleFactura> detalles = new HashSet<>();

    public void addDetalleFactura(DetalleFactura detalleFactura) {
        detalles.add(detalleFactura);
        detalleFactura.setFactura(this);
    }


}
