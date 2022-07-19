package com.example.projetoappquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultadoActivity : AppCompatActivity() {

    private lateinit var textResultado: TextView
    private lateinit var btnReiniciarQuiz: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        textResultado = findViewById(R.id.text_resultado)
        btnReiniciarQuiz = findViewById(R.id.btn_reiniciar_quiz)

        val bundle = intent.extras
        val totalAcertos = bundle?.getInt("totalAcertos") ?: 0
        val totalPerguntas = bundle?.getInt("totalPerguntas") ?: 0

        textResultado.text = "Você acertou $totalAcertos de $totalPerguntas perguntas"

        btnReiniciarQuiz.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}