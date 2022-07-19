package com.example.projetoappquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class PerguntasActivity : AppCompatActivity() {

    private lateinit var textNome: TextView
    private lateinit var textNumeroPergunta: TextView
    private lateinit var textPergunta: TextView
    private lateinit var radioGroupRespostas: RadioGroup
    private lateinit var radioResposta1: RadioButton
    private lateinit var radioResposta2: RadioButton
    private lateinit var radioResposta3: RadioButton
    private lateinit var btnConfirmarResposta: Button

    private lateinit var listaPerguntas: Array<Pergunta>
    private lateinit var pergunta: Pergunta
    private var indicePerguntaAtual = 0
    private var totalAcertos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perguntas)
        iniciarComponentesInterface()

        exibirNomeUsuario()
        listaPerguntas = DadosPerguntas.recuperarListaPerguntas()
        /*for (pergunta in listaPerguntas){
            Log.i("listaPerguntas", pergunta.titulo)
            Log.i("listaPerguntas", pergunta.respostaCerta.toString())
        }*/
        exibirPergunta()

        btnConfirmarResposta.setOnClickListener {
            if (validarSelecao()){

                //Verifica a resposta
                verificaResposta()

                indicePerguntaAtual++
                if (indicePerguntaAtual < listaPerguntas.size){
                    exibirPergunta()
                } else{
                    val intent = Intent(this, ResultadoActivity::class.java)
                    intent.putExtra("totalAcertos", totalAcertos)
                    intent.putExtra("totalPerguntas", listaPerguntas.size)
                    startActivity(intent)
                    finish()
                }
            } else{
                Toast.makeText(this, "Selecione uma resposta.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun verificaResposta() {
        val respostaUsuario = when{
            radioResposta1.isChecked -> 1
            radioResposta2.isChecked -> 2
            radioResposta3.isChecked -> 3
            else -> -1
        }

        if(respostaUsuario == pergunta.respostaCerta){
            Toast.makeText(this, "Resposta Correta", Toast.LENGTH_SHORT).show()
            totalAcertos++
        } else {
            Toast.makeText(this, "Resposta Errada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validarSelecao(): Boolean {
        return (radioResposta1.isChecked || radioResposta2.isChecked || radioResposta3.isChecked)
    }

    private fun exibirPergunta() {
        pergunta = listaPerguntas[indicePerguntaAtual]
        textPergunta.text = pergunta.titulo
        radioResposta1.text = pergunta.resposta01
        radioResposta2.text = pergunta.resposta02
        radioResposta3.text = pergunta.resposta03

        //Atualiza status
        textNumeroPergunta.text = "Pergunta ${indicePerguntaAtual + 1} de ${listaPerguntas.size}"

        radioGroupRespostas.clearCheck()
    }

    private fun exibirNomeUsuario() {
        //Recuperar o nome que veio da outra activity e exibir na tela
        val bundle = intent.extras
        val nome = bundle?.getString("nome") ?: "Usuário"
        textNome.text = "Olá, $nome"
    }

    private fun iniciarComponentesInterface() {
        textNome = findViewById(R.id.text_nome)
        textNumeroPergunta = findViewById(R.id.text_numero_pergunta)
        textPergunta = findViewById(R.id.text_pergunta)
        radioGroupRespostas = findViewById(R.id.radio_group_respostas)
        radioResposta1 = findViewById(R.id.radio_resposta1)
        radioResposta2 = findViewById(R.id.radio_resposta2)
        radioResposta3 = findViewById(R.id.radio_resposta3)
        btnConfirmarResposta = findViewById(R.id.btn_confirmar_resposta)
    }
}