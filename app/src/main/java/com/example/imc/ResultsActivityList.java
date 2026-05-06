package com.example.imc;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultsActivityList extends AppCompatActivity {

    private ListView maListViewPerso;
    private FloatingActionButton fabBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_list);

        maListViewPerso = findViewById(R.id.listView);
        fabBack =findViewById(R.id.faRtn);

        MaSQLiteDataBase maSqldb = new MaSQLiteDataBase(this);
        Cursor monCursor = maSqldb.lireTable();

        if (monCursor == null || monCursor.getCount() == 0) {
            new AlertDialog.Builder(ResultsActivityList.this)
                    .setTitle("Aucun résultat")
                    .setMessage("La table est vide.")
                    .setPositiveButton("OK", (dialog, which) -> finish())
                    .show();
            if (monCursor != null) monCursor.close();
            return;
        }

        monCursor.moveToFirst();
        ArrayList<HashMap<String,String>> listItem = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> map;

        while (!monCursor.isAfterLast()) {
            map = new HashMap<String,String>();
            map.put("date",monCursor.getString(0));
            map.put("prenom",monCursor.getString(1));
            map.put("imc",monCursor.getString(6));
            map.put("result", monCursor.getString(7));
            map.put("img", String.valueOf(ImcUtils.stickerResId(monCursor.getString(5),monCursor.getString(7))));

            listItem.add(map);
            monCursor.moveToNext();
        }

        monCursor.close();

        SimpleAdapter monAdapter = new SimpleAdapter(this.getBaseContext(),listItem,R.layout.affichage_item,new String[] {"img","date","prenom","imc","result"}, new int[] {R.id.img, R.id.txt1, R.id.txt2,R.id.txt3,R.id.txt4});
        maListViewPerso.setAdapter(monAdapter);
        fabBack.setOnClickListener(v -> finish());
    }

    public void resImc(String title, StringBuffer msg) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg.toString())
                .setPositiveButton("OK", (dialog, which) -> finish())
                .show();
    }
}