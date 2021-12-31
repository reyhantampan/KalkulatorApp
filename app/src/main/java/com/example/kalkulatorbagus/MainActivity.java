package com.example.kalkulatorbagus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sp;
    private ArrayAdapter<String> resultAdapter;
    private Toast MainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        ListView resultListView = (ListView) findViewById(R.id.resultListView);
        resultListView.setAdapter(resultAdapter);
    }

    public void hitungButtonClick(View view) {
        EditText firstNumberEditText = (EditText) findViewById(R.id.firstNumberEditText);
        EditText secondNumberEditText = (EditText) findViewById(R.id.secondNumberEditText);

        int firstNumber = Integer.parseInt(firstNumberEditText.getText().toString());
        int secondNumber = Integer.parseInt(secondNumberEditText.getText().toString());

        RadioButton tambahRadioButton = findViewById(R.id.tambahRadioButton);
        RadioButton kurangRadioButton = findViewById(R.id.kurangRadioButton);
        RadioButton kaliRadioButton = findViewById(R.id.kaliRadioButton);
        RadioButton bagiRadioButton = findViewById(R.id.bagiRadioButton);

        int Result = 0;

        if(tambahRadioButton.isChecked()){
            Result = firstNumber + secondNumber;
        } else if (kurangRadioButton.isChecked()){
            Result = firstNumber - secondNumber;
        } else if (kaliRadioButton.isChecked()){
            Result = firstNumber * secondNumber;
        } else if (bagiRadioButton.isChecked()){
            Result = firstNumber / secondNumber;
        }

        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText("Hasil : " + Result);

        CheckBox recordResultCheckBox = (CheckBox) findViewById(R.id.recordResultCheckBox);

        if(recordResultCheckBox.isChecked()) {
            resultAdapter.add("Angka Pertama : " + firstNumber );
            resultAdapter.add("Angka Kedua : " + secondNumber );
            resultAdapter.add("Hasil : " + Result);
        }
        sp = getSharedPreferences("firstNumber", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("firstNumber", String.valueOf(firstNumberEditText));
        editor.putString("secondNumber", String.valueOf(secondNumberEditText));
        editor.commit();
        Toast.makeText(MainActivity.this,  "Tersimpan", Toast.LENGTH_SHORT).show();
    }
}