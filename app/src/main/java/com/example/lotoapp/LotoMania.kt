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
    private val listaDeJogos = mutableListOf<String>()
    private var indiceAtual = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loto_mania)

        etQtd = findViewById(R.id.etQuantidadeJogos)
        btGerarJogos = findViewById(R.id.btGerarLotoMania)
        tvResultado = findViewById(R.id.tvResultadoLotoMania)
        tvJogos = findViewById(R.id.tvJogos)

        btGerarJogos.setOnClickListener {
            val inputTexto = etQtd.text.toString()

            if (inputTexto.isNotEmpty()) {
                val quantidade = inputTexto.toInt()

                if (quantidade > 0) {
                    if (listaDeJogos.isEmpty()) {
                        for (i in 1..quantidade) {
                            val jogo = (0..99).shuffled().take(50).sorted()
                            listaDeJogos.add(jogo.joinToString(", "))
                        }
                    }

                    if (indiceAtual < listaDeJogos.size) {
                        tvJogos.text = "Jogo ${indiceAtual + 1} de ${listaDeJogos.size}"
                        tvResultado.text = listaDeJogos[indiceAtual]
                        indiceAtual++

                        if (indiceAtual == listaDeJogos.size) {
                            btGerarJogos.text = "Gerar novos jogos"
                        }

                    } else {
                        listaDeJogos.clear()
                        indiceAtual = 0
                        tvJogos.text = ""
                        tvResultado.text = ""
                        btGerarJogos.text = "Gerar Jogos"
                        Toast.makeText(this, "Todos os jogos foram exibidos. Digite uma nova quantidade.", Toast.LENGTH_SHORT).show()
                        etQtd.setText("")
                        etQtd.requestFocus()
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