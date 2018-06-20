package com.example.matheus.ufrjacessivel.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Matheus on 2/24/2018.
 */

public class DadosOpenHelper extends SQLiteOpenHelper {

    public DadosOpenHelper(Context context){
        super(context,"TABELA", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptDLL.getCreateTableLocal());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
