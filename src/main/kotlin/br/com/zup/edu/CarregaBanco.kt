package br.com.zup.edu

import br.com.zup.edu.clientes.Cliente
import br.com.zup.edu.clientes.ClienteRepository
import br.com.zup.edu.compras.Compra
import br.com.zup.edu.compras.CompraRepository
import br.com.zup.edu.produtos.Produto
import br.com.zup.edu.produtos.ProdutoRepository
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.event.ApplicationStartupEvent
import java.math.BigDecimal
import javax.inject.Singleton

@Singleton
class CarregaBanco(val clienteRepository: ClienteRepository,
                   val produtoRepository: ProdutoRepository,
                   val compraRepository: CompraRepository

): ApplicationEventListener<ApplicationStartupEvent> {


    override fun onApplicationEvent(event: ApplicationStartupEvent) {
        val walterWhite = Cliente("Walter White", "walter@bad.com.br")
        val mikeRoss = Cliente("Mike Ross", "mike@suits.com.br")

        val tylenol = Produto("Tylenol", BigDecimal("20.0"), 10)
        val resfenol = Produto("Resfenol", BigDecimal("13.1"), 10)
        val vick = Produto("Vick Vaporub", BigDecimal("22.05"), 10)

        clienteRepository.save(walterWhite)
        clienteRepository.save(mikeRoss)

        produtoRepository.save(tylenol)
        produtoRepository.save(resfenol)
        produtoRepository.save(vick)

        compraRepository.save(Compra(walterWhite, listOf(tylenol, resfenol)))
        compraRepository.save(Compra(mikeRoss, listOf(vick)))
    }
}