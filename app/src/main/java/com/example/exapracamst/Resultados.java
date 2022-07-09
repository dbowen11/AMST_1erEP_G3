package com.example.exapracamst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.exapracamst.Objetos.Heroe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Resultados extends AppCompatActivity {
    private Map<String, Heroe> heroesMapa;
    private ArrayList<Heroe> heroesArray;
    private LinearLayout ln;
    private TextView resultados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        Intent i = getIntent();
        heroesMapa = (Map<String, Heroe>) i.getSerializableExtra("heroes");
        heroesArray = (ArrayList<Heroe>) i.getSerializableExtra("heroeArray");
        resultados = findViewById(R.id.txtResultados);
        ln = findViewById(R.id.lista_resultados);
        mostrarHeroes();

    }

    public void mostrarHeroes(){
        for (final Heroe h: heroesArray
             ) {
            resultados.setText("Resultados: " + String.valueOf(heroesArray.size()));
            TextView tx = new TextView(getApplicationContext());
            tx.setText(h.getName().toString());
            tx.setTextSize(18);
            ln.addView(tx);
            tx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Resultados.this, Grafico.class);
                    i.putExtra("heroe", h);
                    startActivity(i);
                    //finish();

                }
            });
        }
    }


}