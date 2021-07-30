package br.com.zup.edu.compras

import br.com.zup.edu.shared.Email
import br.com.zup.edu.shared.EmailService
import javax.inject.Singleton

// TODO: Colocar em um scheduler
@Singleton
class EnviarEmailPosCompra(val emailService: EmailService) : AcaoPosCompra {

    override fun executa(compra: Compra) {

        val email = Email(
                destinatario = compra.cliente.email,
                remetente = "farma@solid.com.br",
                corpo = "Seus remédios logo menos estarão aí"
        )

        emailService.enviaEmail(email)
    }
}