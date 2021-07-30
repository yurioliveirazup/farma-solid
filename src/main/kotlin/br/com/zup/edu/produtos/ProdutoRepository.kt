package br.com.zup.edu.produtos

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface ProdutoRepository : JpaRepository<Produto, Long>
