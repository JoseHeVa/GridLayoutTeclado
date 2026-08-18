package com.example.gridlayoutteclado

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvPantalla: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPantalla = findViewById(R.id.tvPantalla)
        val gridTeclado = findViewById<GridLayout>(R.id.gridTeclado)
        val btnAceptar = findViewById<Button>(R.id.btnAceptar)

        // Recorre todos los botones numéricos de la rejilla y asigna
        // el mismo listener sin necesidad de anidar contenedores extra.
        for (i in 0 until gridTeclado.childCount) {
            val vista = gridTeclado.getChildAt(i)
            if (vista is Button && vista.id != R.id.btnAceptar) {
                vista.setOnClickListener { onTeclaPresionada(vista.text.toString()) }
            }
        }

        btnAceptar.setOnClickListener {
            Toast.makeText(this, "Valor ingresado: ${tvPantalla.text}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onTeclaPresionada(tecla: String) {
        when (tecla) {
            "C" -> tvPantalla.text = ""
            "⌫" -> {
                val actual = tvPantalla.text.toString()
                if (actual.isNotEmpty()) tvPantalla.text = actual.dropLast(1)
            }
            else -> tvPantalla.text = "${tvPantalla.text}$tecla"
        }
    }
}
