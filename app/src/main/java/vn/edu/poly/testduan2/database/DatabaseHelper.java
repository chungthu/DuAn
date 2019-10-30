package vn.edu.poly.testduan2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import vn.edu.poly.testduan2.Constant;

public class DatabaseHelper extends SQLiteOpenHelper implements Constant {

    public static final String DATABASE_NAME = "dbfood";
    public static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_MILK_TEA);
        sqLiteDatabase.execSQL(CREATE_TABLE_BREAD);
        sqLiteDatabase.execSQL(CREATE_TABLE_FRUIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_MILK_TEA);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_BREAD);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_FRUIT);
        onCreate(sqLiteDatabase);
    }
}
