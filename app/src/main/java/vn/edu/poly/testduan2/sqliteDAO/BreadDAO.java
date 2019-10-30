package vn.edu.poly.testduan2.sqliteDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.testduan2.Constant;
import vn.edu.poly.testduan2.database.DatabaseHelper;
import vn.edu.poly.testduan2.model.Bread;
import vn.edu.poly.testduan2.model.MilkTea;

public class BreadDAO implements Constant {

    DatabaseHelper databaseHelper;
    Context context;

    public BreadDAO(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    public void Insert(Bread bread) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TYPE_BREAD, bread.getType());
        contentValues.put(COLUMN_NAME_BREAD, bread.getTitle());
        contentValues.put(COLUMN_IMAGE_BREAD, bread.getImgBread());
        contentValues.put(COLUMN_PRICE1_BREAD, bread.getPrice1());
        contentValues.put(COLUMN_PRICE2_BREAD, bread.getPrice2());
        contentValues.put(COLUMN_TOPPING_BREAD, bread.getTopping());
        sqLiteDatabase.insert(TABLE_BREAD, null, contentValues);
        sqLiteDatabase.close();
    }

    public long Update(Bread bread) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TYPE_BREAD, bread.getType());
        contentValues.put(COLUMN_NAME_BREAD, bread.getTitle());
        contentValues.put(COLUMN_IMAGE_BREAD, bread.getImgBread());
        contentValues.put(COLUMN_PRICE1_BREAD, bread.getPrice1());
        contentValues.put(COLUMN_PRICE2_BREAD, bread.getPrice2());
        contentValues.put(COLUMN_TOPPING_BREAD, bread.getTopping());
        return sqLiteDatabase.update(TABLE_BREAD, contentValues, COLUMN_NAME_BREAD + "=?", new String[]{String.valueOf(bread.getTitle())});
    }

    public boolean delete(Bread bread) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete(TABLE_BREAD, COLUMN_NAME_BREAD + " = " + bread.getTitle(), null);
        if (check != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Bread> getAllBread() {
        List<Bread> listBread = new ArrayList<Bread>();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        String SELECT_ALL_BREAD = "SELECT * FROM " + databaseHelper.TABLE_BREAD;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_BREAD, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Bread bread = new Bread();

            bread.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_BREAD)));
            bread.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_BREAD)));
            bread.setImgBread(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE_BREAD)));
            bread.setPrice1(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE1_BREAD))));
            bread.setPrice2(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE2_BREAD))));
            bread.setTopping(cursor.getString(cursor.getColumnIndex(COLUMN_TOPPING_BREAD)));

            listBread.add(bread);
            cursor.moveToNext();
        }
        return listBread;
    }

    public Bread Image(int image){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        String sql = "SELECT * FROM " + databaseHelper.TABLE_BREAD + " WHERE " + databaseHelper.COLUMN_IMAGE_BREAD + " = " + image;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        Bread bread = new Bread();

        while (!cursor.isAfterLast()){
            bread.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_BREAD)));
            bread.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_BREAD)));
            bread.setImgBread(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE_BREAD)));
            bread.setPrice1(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE1_BREAD))));
            bread.setPrice2(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE2_BREAD))));
            bread.setTopping(cursor.getString(cursor.getColumnIndex(COLUMN_TOPPING_BREAD)));
            cursor.moveToNext();
        }
        return bread;
    }
}
