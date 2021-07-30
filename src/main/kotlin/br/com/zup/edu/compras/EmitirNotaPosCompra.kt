package br.com.zup.edu.compras

import javax.inject.Singleton

@Singleton
class EmitirNotaPosCompra(val notaFiscalRepository: NotaFiscalRepository) : AcaoPosCompra {

    override fun executa(compra: Compra) {

        val notaFiscal = NotaFiscal(compra, compra.cliente)

        notaFiscalRepository.save(notaFiscal)
    }
}