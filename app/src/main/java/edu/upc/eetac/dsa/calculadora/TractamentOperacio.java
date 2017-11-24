package edu.upc.eetac.dsa.calculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class TractamentOperacio extends AppCompatActivity {

    Button modificar, esborrar;
    TextView textView4;
    //ArrayList<String> lista;
    String operacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tractament_operacio);

        operacio = getIntent().getExtras().getString("item");
        //lista = getIntent().getExtras().getStringArrayList("lista");
        textView4 = (TextView) findViewById(R.id.textView4);
        textView4.setText(operacio);

        modificar = (Button) findViewById(R.id.modifiactBtn);
        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intre = getIntent();
                intre.putExtra("obj",operacio);
                setResult(RESULT_OK, intre);
                finish();
            }
        });

        esborrar=(Button) findViewById(R.id.esborrarBtn);
        esborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intre = getIntent();
                intre.putExtra("obj",operacio);
                setResult(RESULT_CANCELED, intre);
                finish();
            }
        });
    }
}
