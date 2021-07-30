package br.com.zup.edu.compras

import br.com.zup.edu.shared.ExecutorDeTransacao
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post

@Controller("/api/compras/")
class ConfirmacaoDeCompraController(val compraRepository: CompraRepository,
                                    val acoes: List<AcaoPosCompra>,
                                    val executorDeTransacao: ExecutorDeTransacao,
) {


    @Post("/{id}/retorno-pagamento")
    fun confirma(@PathVariable("id") id: Long) : HttpResponse<Any> {
        val possivelCompra = compraRepository.findById(id)

        if (possivelCompra.isEmpty) {
            return HttpResponse.notFound()
        }

        val compra = possivelCompra.get()

        executorDeTransacao.executa { // abre transacao
            acoes.forEach { acao -> acao.executa(compra) }
        } // fecha transacao

        return HttpResponse.noContent()
    }
}
