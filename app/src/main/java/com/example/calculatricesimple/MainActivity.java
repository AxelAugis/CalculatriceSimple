package com.example.calculatricesimple;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private int op1 = 0;
    private int op2 = 0;
    private int storeResult = 0;
    private Ops operator = null;
    private boolean isOp1 = true;

    private enum Ops {
        PLUS, MOINS, FOIS, DIV
    }

    public void computeResult(View v) {
        if(operator != null) {
            switch (operator) {
                case PLUS:
                    storeResult = op1 + op2;
                    break;
                case MOINS:
                    storeResult = op1 - op2;
                    break;
                case FOIS:
                    storeResult = op1 * op2;
                    break;
                case DIV:
                    storeResult = op1 / op2;
                    break;
            }
            op1 = storeResult;
            op2 = 0;
            updateDisplay(storeResult);
        }
    }

    public void updateDisplay(int value) {
        screen.setText(String.valueOf(value));
    }

    public void setOperator(View v) {
        Button b = (Button) v;
        if (!isOp1) {
            computeResult(v);
        }
        switch (b.getText().toString()) {
            case "+":
                operator = Ops.PLUS;
                break;
            case "-":
                operator = Ops.MOINS;
                break;
            case "*":
                operator = Ops.FOIS;
                break;
            case "/":
                operator = Ops.DIV;
                break;
        }
        isOp1 = false;
    }

    // Display number on the screen based on the clicked button
    public void addNumber(View v) {
        Button b = (Button) v;
        try {
            int val = Integer.parseInt(b.getText().toString());
            if(isOp1) {
                op1 = op1 * 10 + val;
                updateDisplay(op1);
            } else {
                op2 = op2 * 10 + val;
                updateDisplay(op2);
            }
        } catch(NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "Valeur erronée", Toast.LENGTH_LONG).show();
        }
    }

    // Reset all the values to their original state
    public void resetAll(View v) {
        op1 = 0;
        op2 = 0;
        storeResult = 0;
        operator = null;
        isOp1 = true;
        updateDisplay(op1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        screen = findViewById(R.id.screen);
    }
}