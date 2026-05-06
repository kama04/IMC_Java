package com.example.imc;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    public TextView txtRes, txtAtt, txtTitleRes;
    public ImageView ivRes, ivTab;
    public FloatingActionButton faReturn,closeBtn;
    public Button btnRecalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        MaSQLiteDataBase maSqldb = new MaSQLiteDataBase(this);
        Cursor monCursor2 = maSqldb.lireTable();

        faReturn = findViewById(R.id.faReturn);
        txtRes = findViewById(R.id.txtRes);
        txtAtt = findViewById(R.id.txtAtt);
        ivRes = findViewById(R.id.ivRes);
        ivTab = findViewById(R.id.ivTab);
        txtTitleRes = findViewById(R.id.txtTitleRes);
        btnRecalc = findViewById(R.id.btnRecalc);
        closeBtn = findViewById(R.id.closeBtn);

        if (monCursor2 == null || monCursor2.getCount() == 0) {
            txtTitleRes.setText("Aucun résultat");
            if (monCursor2 != null) monCursor2.close();
            return;
        }

        monCursor2.moveToLast();
        double resImc = monCursor2.getDouble(6);
        int resAge = monCursor2.getInt(4);
        String resGender = monCursor2.getString(5);
        monCursor2.close();

        txtTitleRes.setText("Votre IMC est de: " +
                String.format(Locale.getDefault(), "%.1f", resImc));
        ivTab.setImageResource(Integer.parseInt(String.valueOf(ImcUtils.tableResId(resGender))));


        ImcUtils.Thresholds t =
                "Female".equalsIgnoreCase(resGender)
                        ? ImcUtils.thresholdsForWoman(resAge)
                        : ImcUtils.thresholdsForMan(resAge);

        String category = ImcUtils.categoryForImc(resImc, t);

        if ("Poids normal".equals(category)) {
            txtRes.setText("Bravo votre IMC est dans la zone: Normal");
        } else {
            txtRes.setText("Attention votre IMC est dans la zone: " + category);
        }

        txtAtt.setText("⚠\uFE0F Résultat donné à titre indicatif.\n" +
                "Non adapté aux enfants et aux femmes enceintes.\n" +
                "En cas de doute, consultez un médecin.");

        ivRes.setImageResource(ImcUtils.stickerResId(resGender, category));

        faReturn.setOnClickListener(v -> finish());

        btnRecalc.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, CalculActivity.class);
            startActivity(intent);
        });
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, Accueil.class);
                startActivity(intent);
            }
        });
    }
}