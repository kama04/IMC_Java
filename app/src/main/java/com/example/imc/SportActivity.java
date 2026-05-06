package com.example.imc;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class SportActivity extends AppCompatActivity {

    private Button btnRch;
    private FloatingActionButton fabBack, fadd;
    private EditText edtS;
    private ListView maListViewSport;

    private SQLiteSportDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);

        fadd = findViewById(R.id.fadd);
        fabBack = findViewById(R.id.faReturn);
        btnRch = findViewById(R.id.btnRch);
        edtS = findViewById(R.id.etSr);
        maListViewSport = findViewById(R.id.lv2);

        db = new SQLiteSportDataBase(SportActivity.this);

        affichage(db.lireTable());

        fabBack.setOnClickListener(v -> finish());

        btnRch.setOnClickListener(view -> {
            String edtStr = edtS.getText().toString().trim();

            if (edtStr.isEmpty()) {
                affichage(db.lireTable());
            } else {
                affichage(db.lireTable(edtStr));
            }

            Log.d("SportActivity", "Recherche: " + edtStr);
        });

        fadd.setOnClickListener(view -> createAct());
    }

    public void createAct() {
        Intent intent = new Intent(SportActivity.this, Form.class);
        startActivity(intent);
    }

    public void affichage(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            if (cursor != null) {
                cursor.close();
            }

            new AlertDialog.Builder(SportActivity.this)
                    .setTitle("Cette activité n’existe pas")
                    .setMessage("Pourriez-vous l’ajouter, s’il vous plaît ?")
                    .setPositiveButton("Oui", (dialog, which) -> createAct())
                    .setNegativeButton("Non", null)
                    .show();

            return;
        }

        ArrayList<HashMap<String, String>> listItem = new ArrayList<>();

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            HashMap<String, String> map = new HashMap<>();

            String date = cursor.getString(0);
            String name = cursor.getString(1);
            String time = cursor.getString(2);
            String comments = cursor.getString(3);

            map.put("date", date);
            map.put("name", name);
            map.put("time", time);
            map.put("comments", comments);
            map.put("img", String.valueOf(image(name)));

            listItem.add(map);
            cursor.moveToNext();
        }

        cursor.close();

        SimpleAdapter monAdapter = new SimpleAdapter(
                this,
                listItem,
                R.layout.affichage_item,
                new String[]{"img", "date", "name", "time", "comments"},
                new int[]{R.id.img, R.id.txt1, R.id.txt2, R.id.txt3, R.id.txt4}
        );

        maListViewSport.setAdapter(monAdapter);
    }

    public int image(String name) {
        if (name == null) {
            return R.drawable.logo;
        }

        switch (name) {
            case "Football":
                return R.drawable.foot;

            case "Box":
                return R.drawable.box;

            default:
                return R.drawable.logo;
        }
    }
}