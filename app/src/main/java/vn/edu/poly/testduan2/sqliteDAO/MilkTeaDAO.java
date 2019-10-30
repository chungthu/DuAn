package vn.edu.poly.testduan2.sqliteDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.testduan2.Constant;
import vn.edu.poly.testduan2.database.DatabaseHelper;
import vn.edu.poly.testduan2.model.MilkTea;

public class MilkTeaDAO implements Constant {

    DatabaseHelper databaseHelper;
    Context context;

    public MilkTeaDAO(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    public void Insert(MilkTea milkTea) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TYPE_MILK_TEA, milkTea.getType());
        contentValues.put(COLUMN_NAME_MILK_TEA, milkTea.getTitle());
        contentValues.put(COLUMN_IMAGE_MILK_TEA, milkTea.getImgMilk());
        contentValues.put(COLUMN_PRICE1_MILK_TEA, milkTea.getPrice1());
        contentValues.put(COLUMN_PRICE2_MILK_TEA, milkTea.getPrice2());
        contentValues.put(COLUMN_TOPPING_MILK_TEA, milkTea.getTopping());
        sqLiteDatabase.insert(TABLE_MILK_TEA, null, contentValues);
        sqLiteDatabase.close();
    }

    public long Update(MilkTea milkTea) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TYPE_MILK_TEA, milkTea.getType());
        contentValues.put(COLUMN_NAME_MILK_TEA, milkTea.getTitle());
        contentValues.put(COLUMN_IMAGE_MILK_TEA, milkTea.getImgMilk());
        contentValues.put(COLUMN_PRICE1_MILK_TEA, milkTea.getPrice1());
        contentValues.put(COLUMN_PRICE2_MILK_TEA, milkTea.getPrice2());
        contentValues.put(COLUMN_TOPPING_MILK_TEA, milkTea.getTopping());;
        return sqLiteDatabase.update(TABLE_MILK_TEA, contentValues, COLUMN_NAME_MILK_TEA + "=?", new String[]{String.valueOf(milkTea.getTitle())});
    }

    public boolean delete(MilkTea milkTea) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete(TABLE_MILK_TEA, COLUMN_NAME_MILK_TEA + " = " + milkTea.getTitle(), null);
        if (check != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<MilkTea> getAllMilkTea() {
        List<MilkTea> listMilk = new ArrayList<MilkTea>();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        String SELECT_ALL_MILK_TEA = "SELECT * FROM " + databaseHelper.TABLE_MILK_TEA;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_MILK_TEA, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            MilkTea milkTea = new MilkTea();

            milkTea.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_MILK_TEA)));
            milkTea.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_MILK_TEA)));
            milkTea.setImgMilk(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE_MILK_TEA)));
            milkTea.setPrice1(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE1_MILK_TEA))));
            milkTea.setPrice2(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE2_MILK_TEA))));
            milkTea.setTopping(cursor.getString(cursor.getColumnIndex(COLUMN_TOPPING_MILK_TEA)));

            listMilk.add(milkTea);
            cursor.moveToNext();
        }
        return listMilk;
    }

    public MilkTea Image(byte image){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        String sql = "SELECT * FROM " + databaseHelper.TABLE_MILK_TEA + " WHERE " + databaseHelper.COLUMN_NAME_MILK_TEA + " = " + image;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        MilkTea milkTea = new MilkTea();

        while (!cursor.isAfterLast()){
            milkTea.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_MILK_TEA)));
            milkTea.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_MILK_TEA)));
            milkTea.setImgMilk(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE_MILK_TEA)));
            milkTea.setPrice1(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE1_MILK_TEA))));
            milkTea.setPrice2(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE2_MILK_TEA))));
            milkTea.setTopping(cursor.getString(cursor.getColumnIndex(COLUMN_TOPPING_MILK_TEA)));
            cursor.moveToNext();
        }
        return milkTea;
    }
}
