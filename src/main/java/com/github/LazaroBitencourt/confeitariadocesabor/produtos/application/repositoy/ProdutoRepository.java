package com.github.LazaroBitencourt.confeitariadocesabor.produtos.application.repositoy;

import com.github.LazaroBitencourt.confeitariadocesabor.produtos.domain.Produto;

public interface ProdutoRepository {
    Produto save(Produto produto);
}