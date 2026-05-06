package com.example.imc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalculActivity extends AppCompatActivity {

    private EditText etName, etAge, etTaille, etPoids;
    private RadioButton radioMale, radioFemale;
    private Button btnCalcul;
    private FloatingActionButton faReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etTaille = findViewById(R.id.edTaiile);
        etPoids = findViewById(R.id.etPoids);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        btnCalcul = findViewById(R.id.btnCalcul);
        faReturn = findViewById(R.id.faReturn);
        btnCalcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String ageStr = etAge.getText().toString().trim();
                String tailleStr = etTaille.getText().toString().trim();
                String poidsStr = etPoids.getText().toString().trim();

                if (name.isEmpty()) { etName.setError("Entrez votre nom"); return; }
                if (ageStr.isEmpty()) { etAge.setError("Entrez votre âge"); return; }
                if (tailleStr.isEmpty()) { etTaille.setError("Entrez votre taille"); return; }
                if (poidsStr.isEmpty()) { etPoids.setError("Entrez votre poids"); return; }

                int age = Integer.parseInt(etAge.getText().toString().trim());
                double taille = Double.parseDouble(etTaille.getText().toString().trim());
                double poids = Double.parseDouble(etPoids.getText().toString().trim());

                if (age < 18){
                    Toast.makeText(CalculActivity.this, "Âge doit être plus que 18", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (taille < 100 || taille > 250){
                    Toast.makeText(CalculActivity.this, "Taille entre 100 et 250 cm", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (poids < 40 || poids > 250){
                    Toast.makeText(CalculActivity.this, "Poids entre 40 et 250 kg", Toast.LENGTH_SHORT).show();
                    return;
                }

                String gender = "";
                if (radioMale != null && radioMale.isChecked()) gender = "Male";
                else if (radioFemale != null && radioFemale.isChecked()) gender = "Female";

                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());


                double tailleM = taille / 100.0;
                double imc = poids / (tailleM * tailleM);

                MaSQLiteDataBase db = new MaSQLiteDataBase(CalculActivity.this);

                ImcUtils.Thresholds t =
                        "Female".equalsIgnoreCase(gender)
                                ? ImcUtils.thresholdsForWoman(age)
                                : ImcUtils.thresholdsForMan(age);

                String category = ImcUtils.categoryForImc(imc, t);
                db.insertionRESULTS(date, name, taille, poids, age, gender, imc, category);
                Intent intent = new Intent(CalculActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });
        faReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}