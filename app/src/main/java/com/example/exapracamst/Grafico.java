package com.example.exapracamst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.exapracamst.Objetos.Habilidades;
import com.example.exapracamst.Objetos.Heroe;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Grafico extends AppCompatActivity {
    public BarChart graficoBarras;
    private TextView txtNameResultado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);
        Intent i = getIntent();
        Heroe heroe = (Heroe)i.getSerializableExtra("heroe");
        Habilidades h = heroe.getHabidad();
        iniciarGrafico();
        //graficarprueba();
        txtNameResultado = findViewById(R.id.txtGraficoResultados);
        txtNameResultado.setText("Grafico Habilidades: "+ heroe.getName());
        graficar(h);
    }

//metodo que inicializa el grafico
    public void iniciarGrafico() {
        graficoBarras = findViewById(R.id.barChart);
        graficoBarras.getDescription().setEnabled(false);
        graficoBarras.setMaxVisibleValueCount(60);
        graficoBarras.setPinchZoom(false);
        graficoBarras.setDrawBarShadow(false);
        graficoBarras.setDrawGridBackground(false);
        XAxis xAxis = graficoBarras.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        graficoBarras.getAxisLeft().setDrawGridLines(false);
        graficoBarras.animateY(1500);
        graficoBarras.getLegend().setEnabled(false);
    }

    //metodo que actualiza el grafico


    private void graficar(Habilidades poderes){
        Float intelligence;
        Float power;
        Float strength;
        Float speed;
        Float durability;
        Float combat;
        ArrayList<BarEntry> dato_Habilidades = new ArrayList<>();
        //Asignamos a nustra variables los datos del objeto Habilidad
         intelligence = Float.parseFloat(poderes.getIntelligence());
         power = Float.parseFloat(poderes.getPower());
         strength = Float.parseFloat(poderes.getStrength());
        speed= Float.parseFloat(poderes.getSpeed());
        durability= Float.parseFloat(poderes.getDurability());
        combat= Float.parseFloat(poderes.getCombat());

        dato_Habilidades.add(new BarEntry(1, intelligence));
        dato_Habilidades.add(new BarEntry(2, power));
        dato_Habilidades.add(new BarEntry(3, speed));
        dato_Habilidades.add(new BarEntry(4, strength));
        dato_Habilidades.add(new BarEntry(5, combat));
        dato_Habilidades.add(new BarEntry(6, durability));

        llenarGrafico(dato_Habilidades);

    }

    private void graficarprueba(){
        Float intelligence;
        Float power;
        Float strength;
        Float speed;
        Float durability;
        Float combat;
        ArrayList<BarEntry> dato_Habilidades = new ArrayList<>();


        dato_Habilidades.add(new BarEntry(1, 23));
        dato_Habilidades.add(new BarEntry(2, 22));
        dato_Habilidades.add(new BarEntry(3, 45));
        dato_Habilidades.add(new BarEntry(4, 34));
        dato_Habilidades.add(new BarEntry(5, 12));
        dato_Habilidades.add(new BarEntry(6, 12));

        llenarGrafico(dato_Habilidades);

    }



    //metodo que llena el grafico

    private void llenarGrafico(ArrayList<BarEntry> dato_temp){
        BarDataSet temperaturasDataSet;
        if ( graficoBarras.getData() != null &&
                graficoBarras.getData().getDataSetCount() > 0) {
            temperaturasDataSet = (BarDataSet)
                    graficoBarras.getData().getDataSetByIndex(0);
            temperaturasDataSet.setValues(dato_temp);
            graficoBarras.getData().notifyDataChanged();
            graficoBarras.notifyDataSetChanged();
        } else {
            temperaturasDataSet = new BarDataSet(dato_temp, "Data Set");
            temperaturasDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
            temperaturasDataSet.setDrawValues(true);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(temperaturasDataSet);
            BarData data = new BarData(dataSets);
            graficoBarras.setData(data); graficoBarras.setFitBars(true);
        }
        graficoBarras.invalidate();
         }
}
