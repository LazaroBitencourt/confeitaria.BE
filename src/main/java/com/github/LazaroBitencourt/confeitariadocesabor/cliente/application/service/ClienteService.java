package com.github.LazaroBitencourt.confeitariadocesabor.cliente.application.service;

import com.github.LazaroBitencourt.confeitariadocesabor.cliente.application.api.*;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
    ClienteIdResponse cadastraNovoCliente(ClienteRequest clientRequest);

    DetalhaClienteResponse detalhaClientePorId(UUID idCliente);

    List<DetalhaClienteResponse> listaTodosClientes();

    void alteraInformacoesDoCliente(UUID idCliente, AlteraInformacoesRequest informacoesCliente);

    void deletaClientePorId(UUID idCliente);

    DetalhaEnderecoDoClienteResponse buscaEnderecoClientePorId(UUID idCliente);
}
