package com.example.lotoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Menu : AppCompatActivity() {

    private lateinit var btQuina : Button
    private lateinit var btMega : Button
    private lateinit var btLotoFacil : Button
    private lateinit var btLotoMania : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        btMega = findViewById(R.id.btMega)

        btMega.setOnClickListener {
            var telaMenu : Intent

            telaMenu = Intent(this, Mega::class.java)
            startActivity(telaMenu)
        }

        btLotoFacil = findViewById(R.id.btLotoFacil)

        btLotoFacil.setOnClickListener {
            var telaMenu : Intent

            telaMenu = Intent(this, LotoFacil::class.java)
            startActivity(telaMenu)
        }

        btLotoMania = findViewById(R.id.btLotoMania)

        btLotoMania.setOnClickListener {
            var telaMenu : Intent

            telaMenu = Intent(this, LotoMania::class.java)
            startActivity(telaMenu)
        }

        btQuina = findViewById(R.id.btQuina)

        btQuina.setOnClickListener {
            var telaMenu : Intent

            telaMenu = Intent(this, Quina::class.java)
            startActivity(telaMenu)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}