package br.com.zup.edu.shared

import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
open class ExecutorDeTransacao {


    @Transactional
    open fun executa(funcao: () -> Unit) {

        funcao()

    }
}