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
import com.example.lotoapp.R.id.etJogosLotoFacil

class LotoFacil : AppCompatActivity() {

    private lateinit var etQtd: EditText
    private lateinit var etJogos: EditText
    private lateinit var btGerarJogos: Button
    private lateinit var tvResultado: TextView
    private lateinit var tvJogos : TextView
    private val listaDeJogos = mutableListOf<String>()
    private var indiceAtual = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loto_facil)

        etJogos = findViewById(R.id.etNumLotoFacil)
        etQtd = findViewById(R.id.etJogosLotoFacil)
        btGerarJogos = findViewById(R.id.btGerarLotoFacil)
        tvResultado = findViewById(R.id.tvResultadoLotoFacil)
        tvJogos = findViewById(R.id.tvJogoLotoFacil)

        btGerarJogos.setOnClickListener {
            val inputTextoQtd = etQtd.text.toString()
            val inputTextoJogos = etJogos.text.toString()

            if (inputTextoQtd.isNotEmpty() && inputTextoJogos.isNotEmpty()) {
                val quantidadeJogos = inputTextoQtd.toInt()
                val quantidadeNumeros = inputTextoJogos.toInt()

                if (quantidadeJogos > 0 && quantidadeNumeros in 15..20) {
                    if (listaDeJogos.isEmpty()) {
                        for (i in 1..quantidadeJogos) {
                            val jogo = (1..25).shuffled().take(quantidadeNumeros).sorted()
                            listaDeJogos.add(jogo.joinToString(", "))
                        }
                    }

                    if (indiceAtual < listaDeJogos.size) {
                        tvJogos.text = "Jogo ${indiceAtual + 1}/${listaDeJogos.size}"
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
                        Toast.makeText(this, "Todos os jogos foram exibidos. Digite uma nova quantidade!", Toast.LENGTH_SHORT).show()
                        etQtd.setText("")
                        etJogos.setText("")
                        etQtd.requestFocus()
                    }
                } else {
                    Toast.makeText(this, "A quantidade de números por jogo deve ser entre 15 e 20!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}