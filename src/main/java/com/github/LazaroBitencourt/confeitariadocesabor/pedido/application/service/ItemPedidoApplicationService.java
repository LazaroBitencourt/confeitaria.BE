package com.github.LazaroBitencourt.confeitariadocesabor.pedido.application.service;

import com.github.LazaroBitencourt.confeitariadocesabor.pedido.application.api.ItemPedidoRequest;
import com.github.LazaroBitencourt.confeitariadocesabor.pedido.application.api.ItemPedidoResponse;
import com.github.LazaroBitencourt.confeitariadocesabor.pedido.application.repository.ItemPedidoRepositoy;
import com.github.LazaroBitencourt.confeitariadocesabor.pedido.domain.ItemPedido;
import com.github.LazaroBitencourt.confeitariadocesabor.produtos.application.repositoy.ProdutoRepository;
import com.github.LazaroBitencourt.confeitariadocesabor.produtos.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ItemPedidoApplicationService implements ItemPedidoService {

    private final ItemPedidoRepositoy repository;
    private final ProdutoRepository produtoRepository;

    @Override
    public ItemPedido cadastraItemPedido(ItemPedidoRequest novoItemPedido) {
        log.info("[inicia] ItemPedidoApplicationService - cadastraItemPedido");
        Produto produto = produtoRepository.buscaProdutoPorId(novoItemPedido.getIdProduto());
        ItemPedido itemPedido = repository.salva(new ItemPedido(produto,novoItemPedido.getQuantidade()));
        log.info("[finaliza] ItemPedidoApplicationService - cadastraItemPedido");
        return itemPedido;
    }

    @Override
    public ItemPedidoResponse buscaItemPedidoPorId(Long idItemProduto) {
        log.info("[inicia] ItemPedidoApplicationService - buscaItemPedidoPorId");
       ItemPedido itemPedido = repository.buscaItemPedidoPorId(idItemProduto);
        ItemPedidoResponse itemPedidoResponse = ItemPedidoResponse.builder()
                .idProduto(itemPedido.getProduto().getIdProduto())
                .quantidade(itemPedido.getQuantidade())
                .build();
        log.info("[finaliza] ItemPedidoApplicationService - buscaItemPedidoPorId");
        return itemPedidoResponse;
    }

    @Override
    public void deletaItemPedidoPorId(Long idItemProduto) {
        log.info("[inicia] ItemPedidoApplicationService - deletaItemPedidoPorId");
        repository.buscaItemPedidoPorId(idItemProduto);
        repository.deletaItemPedidoPorId(idItemProduto);
        log.info("[finaliza] ItemPedidoApplicationService - deletaItemPedidoPorId");
    }
}
