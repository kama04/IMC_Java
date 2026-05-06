package com.example.imc;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Form extends AppCompatActivity {

    private Button btnValid;
    private StringBuffer monBuffer;
    private FloatingActionButton fabBack;
    private EditText etNameAct,etDuree, etComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form);
        fabBack = findViewById(R.id.faReturn);
        btnValid = findViewById(R.id.btnValid);
        etNameAct = findViewById(R.id.etNameAct);
        etDuree = findViewById(R.id.etDuree);
        etComment = findViewById(R.id.etComment);

        btnValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strAct = etNameAct.getText().toString().trim();
                String durStr = etDuree.getText().toString().trim();
                String strCom = etComment.getText().toString().trim();

                if (strAct.isEmpty()) { etNameAct.setError("Entrez nom de sport"); return; }
                if (durStr.isEmpty()) { etDuree.setError("Entrez duree de sport"); return; }
                if (strCom.isEmpty()) { strCom = "No comment"; }
                Integer dur = Integer.parseInt(etDuree.getText().toString().trim());

                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
                SQLiteSportDataBase db = new SQLiteSportDataBase(Form.this);
                db.insertionSport(date,strAct,dur,strCom);

                monBuffer = new StringBuffer();
                Cursor monCurseur3 = db.lireTable();
                monCurseur3.moveToLast();
                monBuffer.append("Date " + monCurseur3.getString(0)+"\n");
                monBuffer.append("Nom de sport " + monCurseur3.getString(1)+ "\n");
                monBuffer.append("Duree " + monCurseur3.getInt(2)+"min" + "\n");
                monBuffer.append("Comment " + monCurseur3.getString(3) + "\n");
                monCurseur3.close();
                infoSport(strAct, monBuffer);
            }
        });
        fabBack.setOnClickListener(v -> finish());
    }
    public void infoSport(String title, StringBuffer msg) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg.toString())
                .setPositiveButton("OK",  (dialog, which) -> finish())
                .show();
    }
}