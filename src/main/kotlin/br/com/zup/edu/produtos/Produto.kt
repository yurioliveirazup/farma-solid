package br.com.zup.edu.produtos

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

@Entity
class Produto(@field:NotBlank val nome: String,
              @field:Positive val preco: BigDecimal,
              @field:Positive var quantidadeEmEstoque: Int,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null


    fun decrementa() : Produto{
        quantidadeEmEstoque--

        return this
    }
}
