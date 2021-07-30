package br.com.zup.edu.compras

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface NotaFiscalRepository : JpaRepository<NotaFiscal, Long>