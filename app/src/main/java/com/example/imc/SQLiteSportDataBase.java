package com.example.imc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteSportDataBase extends SQLiteOpenHelper {

    public static final String BASE_NOM = "Sports.db";
    public static final int BASE_VERSION = 2;

    public static final String NOM_TABLE = "T_sports";

    public static final String COL0 = "DATE";
    public static final String COL1 = "NOM";
    public static final String COL2 = "DUREE";
    public static final String COL3 = "COMMENTAIRE";

    public SQLiteSportDataBase(Context context) {
        super(context, BASE_NOM, null, BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "CREATE TABLE " + NOM_TABLE + " ("
                + COL0 + " TEXT, "
                + COL1 + " TEXT NOT NULL, "
                + COL2 + " INTEGER NOT NULL, "
                + COL3 + " TEXT NOT NULL);";

        Log.d("DB", "Create SQL: " + strSql);
        db.execSQL(strSql);
    }

    public void insertionSport(String date, String nom, int duree, String commentaire) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL0, date);
        values.put(COL1, nom);
        values.put(COL2, duree);
        values.put(COL3, commentaire);

        long result = db.insert(NOM_TABLE, null, values);

        if (result == -1) {
            Log.e("DB", "Erreur insertion sport");
        } else {
            Log.d("DB", "Insertion sport OK");
        }
    }

    public Cursor lireTable() {
        SQLiteDatabase db = getReadableDatabase();

        return db.query(
                NOM_TABLE,
                null,
                null,
                null,
                null,
                null,
                COL0 + " DESC"
        );
    }

    public Cursor lireTable(String nom) {
        SQLiteDatabase db = getReadableDatabase();

        return db.query(
                NOM_TABLE,
                null,
                COL1 + " LIKE ?",
                new String[]{"%" + nom + "%"},
                null,
                null,
                COL0 + " DESC"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NOM_TABLE);
        onCreate(db);
    }
}