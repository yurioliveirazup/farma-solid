package br.com.zup.edu.compras

import br.com.zup.edu.produtos.Produto
import br.com.zup.edu.produtos.ProdutoRepository
import br.com.zup.edu.shared.Email
import br.com.zup.edu.shared.EmailService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import java.math.BigDecimal

@Controller("/api/compras/")
class ConfirmacaoDeCompraController(val compraRepository: CompraRepository,
                                    val produtoRepository: ProdutoRepository,
                                    val notaFiscalRepository: NotaFiscalRepository,
                                    val emailService: EmailService,
) {


    @Post("/{id}/retorno-pagamento")
    fun confirma(@PathVariable("id") id: Long) : HttpResponse<Any> {
        val possivelCompra = compraRepository.findById(id)

        if (possivelCompra.isEmpty) {
            return HttpResponse.notFound()
        }

        val compra = possivelCompra.get()
        compra.finaliza()

        val notaFiscal = NotaFiscal(compra, compra.cliente)
        val totalDaCompra = compra.produtos
                                  .map(Produto::preco)
                                  .reduce(BigDecimal::add)

        if (totalDaCompra > BigDecimal("100.0")) {
            notaFiscal.valor = totalDaCompra * BigDecimal("1.3")
        } else {
            notaFiscal.valor = totalDaCompra * BigDecimal("1.5")
        }

        notaFiscalRepository.save(notaFiscal)

        val email = Email(
                destinatario = compra.cliente.email,
                remetente = "farma@solid.com.br",
                corpo = "Seus remédios logo menos estarão aí"
        )
        emailService.enviaEmail(email)


        val produtos = compra.produtos.map(Produto::decrementa)
        compraRepository.update(compra)
        produtoRepository.updateAll(produtos)

        return HttpResponse.noContent()
    }
}
