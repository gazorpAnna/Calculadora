package edu.upc.eetac.dsa.calculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConfirmarEsborrar extends AppCompatActivity implements View.OnClickListener{

    Button si, no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_esborrar);

        si = (Button) findViewById(R.id.button_si);
        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inb1 = getIntent();
                setResult(RESULT_OK, inb1);
                finish();
            }
        });

        no = (Button) findViewById(R.id.button_no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inb1 = getIntent();
                setResult(RESULT_CANCELED, inb1);
                finish();
            }
        });
    }
    public void onClick(View view) {
        Intent inb1 = getIntent();
        setResult(RESULT_OK, inb1);
        finish();
    }
}
