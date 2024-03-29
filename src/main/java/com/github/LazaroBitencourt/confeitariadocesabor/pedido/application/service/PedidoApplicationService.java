package com.github.LazaroBitencourt.confeitariadocesabor.pedido.application.service;

import com.github.LazaroBitencourt.confeitariadocesabor.cliente.application.repository.ClienteRepository;
import com.github.LazaroBitencourt.confeitariadocesabor.cliente.domain.Cliente;

import com.github.LazaroBitencourt.confeitariadocesabor.pedido.application.api.*;
import com.github.LazaroBitencourt.confeitariadocesabor.pedido.application.repository.ItemPedidoRepositoy;
import com.github.LazaroBitencourt.confeitariadocesabor.pedido.application.repository.PedidoRepository;
import com.github.LazaroBitencourt.confeitariadocesabor.pedido.domain.ItemPedido;
import com.github.LazaroBitencourt.confeitariadocesabor.pedido.domain.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class PedidoApplicationService implements PedidoService{

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ItemPedidoService itemPedidoService;

    @Override
    public PedidoIdResponse cadastraPedido(PedidoRequest novoPedido) {
        log.info("[inicia] PedidoApplicationService - cadastraPedido");
        Cliente cliente = clienteRepository.buscaClientePorId(novoPedido.getCliente());
        List<ItemPedido> listaDeItemPedido = cadastraItensPedido(novoPedido);
        Pedido pedido = repository.salva(new Pedido( cliente, listaDeItemPedido, novoPedido));
        log.info("[finaliza] PedidoApplicationService - cadastraPedido");
        return PedidoIdResponse.builder().idPedido(pedido.getIdPedido()).build();
    }

    @Override
    public DetalhaPedidoResponse detalhaPedidoPorId(UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - detalhaPedidoPorId");
        Pedido pedido = repository.buscaPedidoPorId(idPedido);
        log.info("[finaliza] PedidoApplicationService - detalhaPedidoPorId");
        return new DetalhaPedidoResponse(pedido);
    }

    @Override
    public List<DetalhaPedidoResponse> listaPedidosDoClientePorIdCliente(UUID idCliente) {
        log.info("[inicia] PedidoApplicationService - listaPedidosDoClientePorIdCliente");
        List<Pedido> listaDePedidos = repository.buscaPedidosDoClientePorIdCliente(idCliente);
        log.info("[finaliza] PedidoApplicationService - listaPedidosDoClientePorIdCliente");
        return DetalhaPedidoResponse.converte(listaDePedidos);
    }

    @Override
    public List<DetalhaPedidoResponse> listaTodosPedidos() {
        log.info("[inicia] PedidoApplicationService - listaTodosPedidos");
        List<Pedido> listaDePedidos = repository.buscaTodosPedidos();
        log.info("[finaliza] PedidoApplicationService - listaTodosPedidos");
        return DetalhaPedidoResponse.converte(listaDePedidos);
    }

    public void adicionaNovoItemPedidoEmPedido(ItemPedidoRequest itemPedidoRequest, UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - adicionaNovoItemPedidoEmPedido");
        Pedido pedido = repository.buscaPedidoPorId(idPedido);
        ItemPedido itemPedido = itemPedidoService.cadastraItemPedido(itemPedidoRequest);
        pedido.adicionaNovoItemPedido(itemPedido);
        repository.salva(pedido);
        log.info("[finaliza] PedidoApplicationService - adicionaNovoItemPedidoEmPedido");
    }

    @Override
    public void removeItemPedidoDoPedidoPorId(Long idItemPedido, UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - removeItemPedidoDoPedidoPorId");
        Pedido pedido = repository.buscaPedidoPorId(idPedido);
        pedido.removeItemPedido(idItemPedido);
        repository.salva(pedido);
        log.info("[finaliza] PedidoApplicationService - removeItemPedidoDoPedidoPorId");
    }

    @Override
    public void alteraDataHoraDeEntregaDoPedidoPorId(DataHoraEntregaRequest dataHoraEntrega, UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - alteraDataHoraDeEntregaDoPedidoPorId");
        Pedido pedido = repository.buscaPedidoPorId(idPedido);
        pedido.alteraDataHoraDeEntrega(dataHoraEntrega);
        repository.salva(pedido);
        log.info("[finaliza] PedidoApplicationService - alteraDataHoraDeEntregaDoPedidoPorId");
    }

    @Override
    public void alteraFormaDeEntregaParaRetiraNaLoja(UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - alteraFormaDeEntregaParaRetiraNaLoja");
        Pedido pedido = repository.buscaPedidoPorId(idPedido);
        pedido.alteraFormaDeEntregaParaRetiraNaLoja();
        log.info("[finaliza] PedidoApplicationService - alteraFormaDeEntregaParaRetiraNaLoja");
    }

    @Override
    public void alteraFormaDeEntregaParaEntregaNoEndereco(UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - alteraFormaDeEntregaParaEntregaNoEndereco");
        Pedido pedido = repository.buscaPedidoPorId(idPedido);
        pedido.alteraFormaDeEntregaParaEntregaNoEndereco();
        log.info("[finaliza] PedidoApplicationService - alteraFormaDeEntregaParaEntregaNoEndereco");
    }

    @Override
    public void alteraFormaDePagamentoParaDinheiro(UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - alteraFormaDePagamentoParaDinheiro");
        Pedido pedido = repository.buscaPedidoPorId(idPedido);
        pedido.alteraFormaDePagamentoParaDinheiro();
        log.info("[finaliza] PedidoApplicationService - alteraFormaDePagamentoParaDinheiro");
    }

    @Override
    public void alteraFormaDePagamentoParaCartaoDeCredito(UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - alteraFormaDePagamentoParaCartaoDeCredito");
        Pedido pedido = repository.buscaPedidoPorId(idPedido);
        pedido.alteraFormaDePagamentoParaCartaoDeCredito();
        log.info("[finaliza] PedidoApplicationService - alteraFormaDePagamentoParaCartaoDeCredito");
    }

    @Override
    public void alteraFormaDePagamentoParaCartaoDeDebito(UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - alteraFormaDePagamentoParaCartaoDeDebito");
        Pedido pedido = repository.buscaPedidoPorId(idPedido);
        pedido.alteraFormaDePagamentoParaCartaoDeDebito();
        log.info("[finaliza] PedidoApplicationService - alteraFormaDePagamentoParaCartaoDeDebito");
    }

    @Override
    public void deletaPedidoPorId(UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - deletaPedidoPorId");
        repository.buscaPedidoPorId(idPedido);
        repository.deletaPedidoPorId(idPedido);
        log.info("[finaliza] PedidoApplicationService - deletaPedidoPorId");
    }

    private List<ItemPedido> cadastraItensPedido(PedidoRequest itensDePedido) {
        return itensDePedido.getItensDePedido()
                .stream()
                .map(itemPedido
                        -> itemPedidoService.cadastraItemPedido(itemPedido))
                .collect(Collectors.toList());
    }
}
