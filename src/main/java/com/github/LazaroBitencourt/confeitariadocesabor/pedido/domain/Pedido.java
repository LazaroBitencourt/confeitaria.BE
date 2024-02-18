package com.github.LazaroBitencourt.confeitariadocesabor.pedido.domain;

import com.github.LazaroBitencourt.confeitariadocesabor.cliente.domain.Cliente;
import com.github.LazaroBitencourt.confeitariadocesabor.itempedido.domain.ItemPedido;
import com.github.LazaroBitencourt.confeitariadocesabor.pedido.application.api.PedidoRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID", name = "pedido_id",unique = true, updatable = false)
    private UUID idPedido;
    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false, updatable = false)
    private Cliente cliente;
    @NotNull
    private LocalDateTime dataHoraDoPedido;
    @NotNull
    private LocalDateTime dataHoraParaEntrega;
    @OneToMany()
    private List<ItemPedido> itensDePedido;
    @NotNull
    private double valorTotal;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoDeEntrega formaDeEntrega;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoDePagamento formaDePagamento;

    public Pedido(Cliente cliente, PedidoRequest pedidoRequest) {
        this.cliente = cliente;
        this.dataHoraDoPedido = LocalDateTime.now();
        this.dataHoraParaEntrega = pedidoRequest.getDataHoraParaEntrega();
        this.itensDePedido = pedidoRequest.getItensDePedido();
        this.valorTotal = calculaValorTotalDeItensDePedido(pedidoRequest.getItensDePedido());
        this.formaDeEntrega = pedidoRequest.getFormaDeEntrega();
        this.formaDePagamento = pedidoRequest.getFormaDePagamento();
    }

    private double calculaValorTotalDeItensDePedido(List<ItemPedido> itensDePedido) {
        return itensDePedido.stream().mapToDouble(itemPedido -> itemPedido.getProduto().getPreco() * itemPedido.getQuantidade())
                .sum();
    }

}
