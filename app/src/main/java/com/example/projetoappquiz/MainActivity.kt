package com.example.projetoappquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var editNome: EditText
    private lateinit var btnIniciarQuiz: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editNome = findViewById(R.id.edit_nome)
        btnIniciarQuiz = findViewById(R.id.btn_iniciar_quiz)

        btnIniciarQuiz.setOnClickListener {
            if (editNome.text.isNotEmpty()){
                val intent = Intent(this, PerguntasActivity::class.java)
                intent.putExtra("nome", editNome.text.toString())
                startActivity(intent)
                finish()
            } else{
                Toast.makeText(this, "Preencha o nome", Toast.LENGTH_LONG).show()
                /*val intent = Intent(this, PerguntasActivity::class.java)
                intent.putExtra("nome", "Usuário")
                startActivity(intent)
                finish()*/
            }
        }
    }
}