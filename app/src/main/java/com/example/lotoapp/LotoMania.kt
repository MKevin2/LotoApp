package com.example.lotoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LotoMania : AppCompatActivity() {

    private lateinit var etQtd: EditText
    private lateinit var btGerarJogos: Button
    private lateinit var tvResultado: TextView
    private lateinit var tvJogos : TextView

    // Variáveis de controle para navegar entre os jogos gerados
    private val listaDeJogos = mutableListOf<String>()
    private var indiceAtual = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loto_mania)

        // 2. Fazendo o findViewById no onCreate
        etQtd = findViewById(R.id.etQuantidadeJogos)
        btGerarJogos = findViewById(R.id.btGerarLotoMania)
        tvResultado = findViewById(R.id.tvResultadoLotoMania)
        tvJogos= findViewById(R.id.tvJogos)

        btGerarJogos.setOnClickListener {
            val inputTexto = etQtd.text.toString()

            if (inputTexto.isNotEmpty()) {
                val quantidade = inputTexto.toInt()

                if (quantidade > 0) {
                    // Se for o primeiro clique ou se mudou a quantidade, geramos a lista do zero
                    if (listaDeJogos.isEmpty()) {
                        for (i in 1..quantidade) {
                            val jogo = (0..99).shuffled().take(50).sorted()
                            listaDeJogos.add(jogo.joinToString(", "))
                        }
                    }

                    // Verifica se ainda temos jogos para mostrar na sequência
                    if (indiceAtual < listaDeJogos.size) {
                        // Atualiza o contador de jogos (ex: Jogo 1 de 3)
                        tvJogos.text = "Jogo ${indiceAtual + 1} de ${listaDeJogos.size}"

                        // Mostra apenas os números do jogo atual
                        tvResultado.text = listaDeJogos[indiceAtual]

                        // Avança o índice para o próximo clique
                        indiceAtual++

                        // Se chegou no último jogo, muda o texto do botão para indicar recomeço ou fim
                        if (indiceAtual == listaDeJogos.size) {
                            btGerarJogos.text = "Gerar novos jogos"
                        }
                    } else {
                        // Se já mostrou todos, limpa e reinicia o ciclo
                        listaDeJogos.clear()
                        indiceAtual = 0
                        tvJogos.text = ""
                        tvResultado.text = ""
                        btGerarJogos.text = "Gerar Jogos"
                        Toast.makeText(this, "Todos os jogos foram exibidos. Digite uma nova quantidade.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Digite um valor maior que zero", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Digite a quantidade de jogos", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}