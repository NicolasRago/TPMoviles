package com.example.tpmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Enunciado2Activity extends AppCompatActivity {

    private Button suma, resta, division, multiplicacion;
    private TextView resultado;

    public static final int OPERACION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enunciado2);

        suma = findViewById(R.id.buttonSuma);
        resta = findViewById(R.id.buttonResta);
        division = findViewById(R.id.buttonDivision);
        multiplicacion = findViewById(R.id.buttonMultiplicacion);
        resultado = findViewById(R.id.resultado);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, Enunciado2ActivityOperacion.class);
        switch (view.getId()) {
            case R.id.buttonSuma:
                char mas = '+';
                intent.putExtra("operador",mas);
                startActivityForResult(intent,OPERACION);
                break;
            case R.id.buttonResta:
                char menos = '-';
                intent.putExtra("operador",menos);
                startActivityForResult(intent,OPERACION);
                break;
            case R.id.buttonDivision:
                char div = '/';
                intent.putExtra("operador",div);
                startActivityForResult(intent,OPERACION);
                break;
            case R.id.buttonMultiplicacion:
                char mult = '*';
                intent.putExtra("operador",mult);
                startActivityForResult(intent,OPERACION);
                break;
            default:
                break;

        }
    }

    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPERACION) {
            if (resultCode == RESULT_OK) {
                int reply = data.getIntExtra("resultado",0);
                resultado.setText(new Integer(reply).toString());
            }}
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("resultado",
                resultado.getText().toString());
    }

    @Override
    public void onRestoreInstanceState (Bundle mySavedState) {
        super.onRestoreInstanceState(mySavedState);
        if (mySavedState != null) {
            String res = mySavedState.getString("resultado");
            if (res != null)
                resultado.setText(res);
        }
    }
}
