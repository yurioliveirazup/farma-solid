package br.com.zup.edu.compras

import br.com.zup.edu.produtos.Produto
import br.com.zup.edu.produtos.ProdutoRepository
import javax.inject.Singleton

@Singleton
class FinalizaCompraEDecrementaEstoque(val compraRepository: CompraRepository,
                                       val produtoRepository: ProdutoRepository,
) : AcaoPosCompra {

    override fun executa(compra: Compra) {
        compra.finaliza()

        val produtos = compra.produtos.map(Produto::decrementaEstoque)
        compraRepository.update(compra)
        produtoRepository.updateAll(produtos)
    }
}