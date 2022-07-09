package com.example.exapracamst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.exapracamst.Objetos.Habilidades;
import com.example.exapracamst.Objetos.Heroe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText buscar;
    Button BuscarHeroes;
    private String token = "5612224972122527";
    private String url = "https://www.superheroapi.com/api.php/5612224972122527/search/";
    private LinearLayout listaResultados;
    private Map<String, Heroe> heroesObtenidos;
    private ArrayList<Heroe> herosArray;

    private RequestQueue ListaRequest = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        heroesObtenidos = new HashMap<>();
        buscar= (EditText)findViewById(R.id.busqueda);
        ListaRequest = Volley.newRequestQueue(this);
        listaResultados = findViewById(R.id.lista_resultados);
        herosArray = new ArrayList<>();
    }

    //funcion que Permita encontrar buscar todos los héroes que coincidan con el criterio de búsqueda
    public void buscarHeroes(View view){
        String superHeroeB = buscar.getText().toString();
        String url_registros = "https://www.superheroapi.com/api.php/5612224972122527/search/" + superHeroeB;
            jsonParse(url_registros);
        //JSONObject object =
    }

    public  void jsonParse(String url){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i=0; i< jsonArray.length(); i++){
                        JSONObject heroe = jsonArray.getJSONObject(i);
                        String id = heroe.getString("id");
                        String name = heroe.getString("name");
                        //System.out.println("id: " + id);
                       // System.out.println("name: " + name);
                        //Habilidades h = (Habilidades) heroe.getJSONObject("powerstats");
                        JSONObject habilidad = (JSONObject)heroe.getJSONObject("powerstats");
                         String intelligence = habilidad.getString("intelligence");
                         String strength = habilidad.getString("strength");
                         String speed= habilidad.getString("speed");
                         String durability= habilidad.getString("durability");
                         String power= habilidad.getString("power");
                         String combat= habilidad.getString("combat");
                         Habilidades h1 = new Habilidades(intelligence,strength,speed,durability,power,combat);
                         Heroe heroeO = new Heroe(id,name,h1);
                         String string = String.valueOf(i);
                         heroesObtenidos.put(string,heroeO);
                            herosArray.add(heroeO);
                    }
                    Intent i = new Intent(MainActivity.this, Resultados.class);
                    i.putExtra("heroes", (Serializable) heroesObtenidos);
                    i.putExtra("heroeArray", herosArray);
                    startActivity(i);
                    herosArray.clear();
                   // finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        ListaRequest.add(request);
    }





}