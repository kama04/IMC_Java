package com.example.imc;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MaSQLiteDataBase extends SQLiteOpenHelper {
    public static final String BASE_NOM = "ResultsIMC.db";
    public static final int BASE_VERSION = 2;

    public static final String NOM_TABLE = "T_results";
    public static final String COL0 = "DATE";
    public static final String COL1 = "PRENOM";
    public static final String COL2 = "TAILLE";
    public static final String COL3 = "POIDS";
    public static final String COL4 = "AGE";
    public static final String COL5 = "GENDER";
    public static final String COL6 = "IMC";
    public static final String COL7 = "CATEGORIE";

    public MaSQLiteDataBase (Context context){

        super (context, BASE_NOM, null, BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String strSql = "CREATE TABLE "+ NOM_TABLE +
        " ("
        + COL0 + " text,"
        + COL1 + " text not null,"
        + COL2 + " real not null,"
        + COL3 + " real not null,"
        + COL4 + " integer not null,"
        + COL5 + " text not null,"
        + COL6 + " real not null,"
        + COL7 + " text not null);";
        Log.d("DB", "strSql" + strSql);
        db.execSQL(strSql);
        Log.d("DB", "Create DB OK: " + NOM_TABLE);
    }
    public void insertionRESULTS(String DATE, String PRENOM, Double TAILLE, Double POIDS, Integer AGE, String GENDER, Double IMC,String CATEGORIE){
        DATE = DATE.replace("'", " ");
        PRENOM = PRENOM.replace("'", " ");
        GENDER = GENDER.replace("'", " ");
        CATEGORIE = CATEGORIE.replace("'", " ");

        String strSql = "INSERT INTO " + NOM_TABLE + "(" + COL0 + "," + COL1 + "," + COL2 + "," + COL3 + "," + COL4 + "," + COL5 + "," + COL6 + "," + COL7 + ")"
                + "values ('" + DATE + "','" + PRENOM + "','" + TAILLE + "','" + POIDS + "','" + AGE + "','" + GENDER + "','" + IMC + "','" + CATEGORIE + "');";

        Log.d("db", "insertionRESULTS: " + strSql);
        getWritableDatabase().execSQL(strSql);
        Log.d("DB", "insertionRESULTS OK: " + NOM_TABLE);

    }
    public Cursor lireTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor monCursor = db.rawQuery("SELECT * FROM " + NOM_TABLE, null);
        return monCursor;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NOM_TABLE);
        onCreate(db);
    }
}
