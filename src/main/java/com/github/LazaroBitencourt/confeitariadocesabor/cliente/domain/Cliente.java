package com.github.LazaroBitencourt.confeitariadocesabor.cliente.domain;

import com.github.LazaroBitencourt.confeitariadocesabor.endereco.domain.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID", name = "pedido_id", unique = true, updatable = false)
    private UUID idCliente;
    @NotBlank
    private String nome;
    @ManyToOne
    @JoinColumn(name = "endereco_id", nullable = false, updatable = false)
    private Endereco endereco;
}