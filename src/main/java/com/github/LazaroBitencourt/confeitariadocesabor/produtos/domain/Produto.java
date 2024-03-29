package com.github.LazaroBitencourt.confeitariadocesabor.produtos.domain;

import com.github.LazaroBitencourt.confeitariadocesabor.produtos.application.api.ProdutoRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BIGINT", name = "produto_id", unique = true, updatable = false)
    private Long idProduto;
    @NotBlank(message = "Deve conter o nome do produto")
    private String nome;
    private String descricao;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @NotNull
    private LocalDate dataFabricacao;
    @NotNull
    private LocalDate dataValidade;
    @NotNull
    private Double preco;

    public Produto(ProdutoRequest produtoRequest) {
        this.nome = produtoRequest.getNome();
        this.descricao = produtoRequest.getDescricao();
        this.categoria = produtoRequest.getCategoria();
        this.dataFabricacao = produtoRequest.getDataFabricacao();
        this.dataValidade = produtoRequest.getDataValidade();
        this.preco = produtoRequest.getPreco();
    }
}
