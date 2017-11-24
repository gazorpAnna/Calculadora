package edu.upc.eetac.dsa.calculadora;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LlistaOperacions extends AppCompatActivity implements View.OnClickListener {

    Button buttonX,borrarHistorial;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista_operacions);

        lista = getIntent().getExtras().getStringArrayList("listaOp");
        listView = (ListView) findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista );
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent tractamentOp = new Intent(LlistaOperacions.this, TractamentOperacio.class);
                //tractamentOp.putStringArrayListExtra("lista",lista);
                tractamentOp.putExtra("item",lista.get(i));
                startActivityForResult(tractamentOp, 200);
            }
        });


        buttonX = (Button) findViewById(R.id.buttonX);
        buttonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intre = getIntent();
                intre.putExtra("lista",lista);
                setResult(RESULT_OK, intre);
                finish();
            }
        });
        borrarHistorial = (Button) findViewById(R.id.borrarHistorial);
        borrarHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent confirmBorr = new Intent(LlistaOperacions.this, ConfirmarEsborrar.class);
                //inb1.putStringArrayListExtra("listaOp",histOperacions);
                startActivityForResult(confirmBorr, 110);
                //onClick(view);
            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent tornar = getIntent();
        setResult(RESULT_OK, tornar);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // Ve de confirmarEsborrar
        if(requestCode == 110){
            if(resultCode == RESULT_OK){
                lista.clear();
                Intent intre = getIntent();
                intre.putExtra("lista",lista);
                setResult(RESULT_OK, intre);
                finish();
            }
        } else if (requestCode == 200){ // ve de TractamentOperacio
            if(resultCode == RESULT_OK){
                //lista = getIntent().getExtras().getStringArrayList("listaViene");
                String op = data.getExtras().getString("obj");
                Intent intre = getIntent();
                intre.putExtra("op",op);
                setResult(RESULT_FIRST_USER, intre);
                finish();
            } else if (resultCode == RESULT_CANCELED){
                String op = data.getExtras().getString("obj");
                lista.remove(op);
                arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lista );
                listView.setAdapter(arrayAdapter);
            }
        }
    }
}