package br.com.zup.edu.compras

import br.com.zup.edu.clientes.Cliente
import java.math.BigDecimal
import javax.persistence.*

@Entity
class NotaFiscal(@ManyToOne val compra: Compra,
                 @ManyToOne val cliente: Cliente,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var valor: BigDecimal
    private set

    init {

        valor = if (compra.total > BigDecimal("100.0")) {
            compra.total * BigDecimal("1.3")
        } else {
            compra.total * BigDecimal("1.5")
        }

    }
}
