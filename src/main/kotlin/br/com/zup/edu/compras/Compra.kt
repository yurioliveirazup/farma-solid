package br.com.zup.edu.compras

import br.com.zup.edu.clientes.Cliente
import br.com.zup.edu.produtos.Produto
import java.math.BigDecimal
import javax.persistence.*
import kotlin.math.E

@Entity
class Compra(@ManyToOne val cliente: Cliente,
             @ManyToMany(cascade = [
                 CascadeType.MERGE
             ], fetch = FetchType.EAGER)
             val produtos: List<Produto>,
) {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Enumerated(EnumType.STRING)
    var status = Status.AGUARDANDO_PAGAMENTO

    val total = produtos.map(Produto::preco)
                        .reduce(BigDecimal::add)

    fun finaliza() {
        status = Status.FINALIZADO
    }
}

enum class Status {
    AGUARDANDO_PAGAMENTO,
    FINALIZADO
}
