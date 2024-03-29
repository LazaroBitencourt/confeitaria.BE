package com.github.LazaroBitencourt.confeitariadocesabor.cliente.application.api;

import com.github.LazaroBitencourt.confeitariadocesabor.cliente.application.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ClienteRestController implements ClienteAPI{

    private final ClienteService service;

    @Override
    public ClienteIdResponse postCadastraNovoCliente(ClienteRequest clientRequest) {
        log.info("[inicia] ClienteRestController - postCadastraCliente");
        ClienteIdResponse clienteIdResponse = service.cadastraNovoCliente(clientRequest);
        log.info("[finaliza] ClienteRestController - postCadastraCliente");
        return clienteIdResponse;
    }

    @Override
    public DetalhaClienteResponse getDetalhaClientePorId(UUID idCliente) {
        log.info("[inicia] ClienteRestController - getDetalhaClientePorId");
        DetalhaClienteResponse detalhaClienteResponse= service.detalhaClientePorId(idCliente);
        log.info("[finaliza] ClienteRestController - getDetalhaClientePorId");
        return detalhaClienteResponse;
    }

    @Override
    public List<DetalhaClienteResponse> getListaTodosClientes() {
        log.info("[inicia] ClienteRestController - getListaTodosClientes");
        List<DetalhaClienteResponse> listaDeClientes = service.listaTodosClientes();
        log.info("[finaliza] ClienteRestController - getListaTodosClientes");
        return listaDeClientes;
    }

    @Override
    public void putAlteraInformacoesDoCliente(UUID idCliente, AlteraInformacoesRequest informacoesCliente) {
        log.info("[inicia] ClienteRestController - putAlteraInformacoesDoCliente");
        service.alteraInformacoesDoCliente(idCliente, informacoesCliente);
        log.info("[finaliza] ClienteRestController - putAlteraInformacoesDoCliente");
    }

    @Override
    public void deleteClientePorId(UUID idCliente) {
        log.info("[inicia] ClienteRestController - deleteClientePorId");
        service.deletaClientePorId(idCliente);
        log.info("[finaliza] ClienteRestController - deleteClientePorId");
    }

    @Override
    public DetalhaEnderecoDoClienteResponse getBuscaEnderecoClientePorId(UUID idCliente) {
        log.info("[inicia] ClienteRestController - getBuscaEnderecoClientePorId");
        DetalhaEnderecoDoClienteResponse detalhaEnderecoDoClienteResponse = service.buscaEnderecoClientePorId(idCliente);
        log.info("[finaliza] ClienteRestController - getBuscaEnderecoClientePorId");
        return detalhaEnderecoDoClienteResponse;
    }
}
