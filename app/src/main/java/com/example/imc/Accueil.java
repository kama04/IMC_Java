package com.example.imc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Accueil extends AppCompatActivity {

    private ImageView imgV1, imgV2, imgV3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        imgV1 = findViewById(R.id.img1);
        imgV2 = findViewById(R.id.img2);
        imgV3 = findViewById(R.id.img3);

        imgV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Accueil.this, CalculActivity.class);
                startActivity(i);
            }
        });
        imgV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Accueil.this, ResultsActivityList.class);
                startActivity(i);
            }
        });
        imgV3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Accueil.this, SportActivity.class);
                startActivity(i);
            }
        });
    }
}
