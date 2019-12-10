package com.example.tpmoviles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Enunciado2ActivityOperacion extends AppCompatActivity implements View.OnClickListener {

    private char operador;
    private TextView op;
    private EditText valor1,valor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enunciado2_operacion);

        valor1 = findViewById(R.id.number1);
        valor2 = findViewById(R.id.number2);
        operador = getIntent().getCharExtra("operador",'d');
        op = findViewById(R.id.op);
        op.setText(Character.toString(operador));
    }

    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case R.id.buttonResult:
                    Intent replyIntent = new Intent(this,Enunciado2Activity.class);
                    replyIntent.putExtra("resultado",obtenerResultado());
                    setResult(RESULT_OK, replyIntent);
                    finish();
                    break;
                default:
                    break;
            }
        }
        catch (NumberFormatException e) {

        }

    }

    private int obtenerResultado() {
        double result;
        switch (operador) {
            case '+':
                result = (double)Integer.parseInt(valor1.getText().toString()) +
                        (double)Integer.parseInt(valor2.getText().toString());
                break;
            case '-':
                result = (double)Integer.parseInt(valor1.getText().toString()) -
                        (double)Integer.parseInt(valor2.getText().toString());
                break;
            case '/':
                result = (double)Integer.parseInt(valor1.getText().toString()) /
                        (double)Integer.parseInt(valor2.getText().toString());
                break;
            case '*':
                result = (double)Integer.parseInt(valor1.getText().toString()) *
                        (double)Integer.parseInt(valor2.getText().toString());
                break;
            default:
                result = 0;
                break;
        }
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        else if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        else return (int) result;
    }

}
