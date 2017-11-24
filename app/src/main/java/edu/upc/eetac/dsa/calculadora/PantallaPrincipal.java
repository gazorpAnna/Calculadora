package edu.upc.eetac.dsa.calculadora;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PantallaPrincipal extends AppCompatActivity {


    static final String[] arrayOper = new String[]{
            "+","-","x","/" };
    Spinner spinner;
    Button historial,clear,igual;
    EditText num1, num2;
    TextView result;
    Integer resultatNum;
    ArrayList<String> histOperacions= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);



        // Carrego els operants del spinner:
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PantallaPrincipal.this,android.R.layout.simple_spinner_item, arrayOper);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);
        result = (TextView) findViewById(R.id.resultat);

        igual = (Button) findViewById(R.id.igual);
        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tanco el teclat
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                // Comprobo si algun camp està buit:
                if(num1.getText().toString().isEmpty() || num2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Cal indicar els dos valors numèrics", Toast.LENGTH_SHORT).show();

                }else { // sino, faig els calculs:
                    if (spinner.getSelectedItem().toString().contains("+")) {
                        resultatNum = Integer.parseInt(num1.getText().toString()) + Integer.parseInt(num2.getText().toString());
                        result.setText(resultatNum.toString());
                    } else if (spinner.getSelectedItem().toString().contains("-")) {
                        resultatNum = Integer.parseInt(num1.getText().toString()) - Integer.parseInt(num2.getText().toString());
                        result.setText(resultatNum.toString());
                    } else if (spinner.getSelectedItem().toString().contains("x")) {
                        resultatNum = Integer.parseInt(num1.getText().toString()) * Integer.parseInt(num2.getText().toString());
                        result.setText(resultatNum.toString());
                    } else if (spinner.getSelectedItem().toString().contains("/")) {
                        resultatNum = Integer.parseInt(num1.getText().toString()) / Integer.parseInt(num2.getText().toString());
                        result.setText(resultatNum.toString());
                    }
                    histOperacions.add(histOperacions.size()+1+": " + num1.getText().toString()+ " "+ spinner.getSelectedItem().toString()+" "
                            + num2.getText().toString() +" "+"=" + " "+ result.getText().toString());
                }



            }
        });

        // Botó per posar a 0 tots els camps:
        clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1.setText("0"); num2.setText("0"); result.setText("0");
            }
        });


        historial = (Button) findViewById(R.id.historial);
        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inb1 = new Intent(PantallaPrincipal.this, LlistaOperacions.class);
                inb1.putStringArrayListExtra("listaOp",histOperacions);
                startActivityForResult(inb1, 1);
            }
        });


    }
    private int getIndex(Spinner sp, String str)
    {
        int index = 0;
        for(int i=0;i<sp.getCount();i++){
            if(sp.getItemAtPosition(i).equals(str))
                index = i;
        }
        return index;
    }
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if( resultCode == RESULT_OK) {
                histOperacions = data.getExtras().getStringArrayList("lista");
            } else if(resultCode == RESULT_FIRST_USER){
                String op = data.getExtras().getString("op");//getIntent().getExtras().getString("op");
                histOperacions.remove(op);
                String[] sp = op.split(" ");
                num1.setText(sp[1]);
                spinner.setSelection(getIndex(spinner, sp[2]));
                num2.setText(sp[3]);

            }
        }
    }
}
