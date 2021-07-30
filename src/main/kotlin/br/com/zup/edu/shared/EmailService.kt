package br.com.zup.edu.shared


interface EmailService {

    fun enviaEmail(email: Email)
}

data class Email(val destinatario: String,
            val remetente: String,
            val corpo: String,
)