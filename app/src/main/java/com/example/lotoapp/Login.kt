package com.example.lotoapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java
import kotlin.system.exitProcess

class Login : AppCompatActivity() {

    private lateinit var etNome : EditText

    private lateinit var etSenha : EditText

    private val NOME_CORRETO = "Matheus"

    private val SENHA_CORRETA = "1234"

    private lateinit var btLogin : Button

    private lateinit var btSair : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        etNome = findViewById(R.id.etNome)
        etSenha = findViewById(R.id.etSenha)

        btLogin = findViewById(R.id.btLogin)

        btLogin.setOnClickListener {
            val emailDigitado = etNome.text.toString().trim()
            val senhaDigitada = etSenha.text.toString().trim()

            // 1. Validação de campos vazios
            if (emailDigitado.isEmpty()) {
                etNome.error = "Digite seu nome!"
                etNome.requestFocus()
                return@setOnClickListener
            }

            if (senhaDigitada.isEmpty()) {
                etSenha.error = "Digite sua senha!"
                etSenha.requestFocus()
                return@setOnClickListener
            }

            // 2. Validação com credenciais estáticas
            if (emailDigitado == NOME_CORRETO && senhaDigitada == SENHA_CORRETA) {
                Toast.makeText(this, "Bem vindo(a) ao Sistema!", Toast.LENGTH_SHORT).show()

                val telaMenu = Intent(this, Menu::class.java)
                startActivity(telaMenu)
                finish() // Opcional: fecha a tela de Login ao entrar no Menu
            } else {
                Toast.makeText(this, "Nome ou senha incorretos", Toast.LENGTH_LONG).show()
            }
        }

        btSair = findViewById(R.id.btSair)

        btSair.setOnClickListener {
            // Cria a caixa de diálogo de confirmação
            AlertDialog.Builder(this)
                .setTitle("Confirmar saída")
                .setMessage("Tem certeza de que deseja sair do aplicativo?")
                .setPositiveButton("Sim") { _, _ ->
                    // Ação executada se o usuário clicar em "Sim"
                    finishAffinity()
                    exitProcess(0)
                }
                .setNegativeButton("Não") { dialog, _ ->
                    // Ação executada se o usuário clicar em "Não"
                    dialog.dismiss() // Apenas fecha o aviso
                }
                .setCancelable(true) // Permite fechar clicando fora da caixa
                .show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}