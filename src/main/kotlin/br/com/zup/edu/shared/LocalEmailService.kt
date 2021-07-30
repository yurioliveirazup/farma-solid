package br.com.zup.edu.shared

import javax.inject.Singleton

@Singleton
class LocalEmailService : EmailService {


    override fun enviaEmail(email: Email) {
        println("Enviando Email $email")
    }


}