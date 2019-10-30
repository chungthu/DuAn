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
import vn.edu.poly.testduan2.model.Fruit;

public class FruitDAO implements Constant {

    DatabaseHelper databaseHelper;
    Context context;

    public FruitDAO(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    public void Insert(Fruit fruit) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TYPE_FRUIT, fruit.getType());
        contentValues.put(COLUMN_NAME_FRUIT, fruit.getTitle());
        contentValues.put(COLUMN_IMAGE_FRUIT, fruit.getImgFruit());
        contentValues.put(COLUMN_PRICE1_FRUIT, fruit.getPrice1());
        contentValues.put(COLUMN_PRICE2_FRUIT, fruit.getPrice2());
        contentValues.put(COLUMN_TOPPING_FRUIT, fruit.getTopping());
        sqLiteDatabase.insert(TABLE_FRUIT, null, contentValues);
        sqLiteDatabase.close();
    }

    public long Update(Fruit fruit) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TYPE_FRUIT, fruit.getType());
        contentValues.put(COLUMN_NAME_FRUIT, fruit.getTitle());
        contentValues.put(COLUMN_IMAGE_FRUIT, fruit.getImgFruit());
        contentValues.put(COLUMN_PRICE1_FRUIT, fruit.getPrice1());
        contentValues.put(COLUMN_PRICE2_FRUIT, fruit.getPrice2());
        contentValues.put(COLUMN_TOPPING_FRUIT, fruit.getTopping());
        return sqLiteDatabase.update(TABLE_FRUIT, contentValues, COLUMN_NAME_FRUIT + "=?", new String[]{String.valueOf(fruit.getTitle())});
    }

    public boolean delete(Fruit fruit) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete(TABLE_FRUIT, COLUMN_NAME_FRUIT + " = " + fruit.getTitle(), null);
        if (check != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Fruit> getAllFruit() {
        List<Fruit> listFruit = new ArrayList<Fruit>();
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        String SELECT_ALL_FRUIT = "SELECT * FROM " + databaseHelper.TABLE_FRUIT;
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_FRUIT, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Fruit fruit = new Fruit();

            fruit.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_FRUIT)));
            fruit.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FRUIT)));
            fruit.setImgFruit(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE_FRUIT)));
            fruit.setPrice1(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE1_FRUIT))));
            fruit.setPrice2(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE2_FRUIT))));
            fruit.setTopping(cursor.getString(cursor.getColumnIndex(COLUMN_TOPPING_FRUIT)));

            listFruit.add(fruit);
            cursor.moveToNext();
        }
        return listFruit;
    }

    public Fruit Image(int image){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        String sql = "SELECT * FROM " + databaseHelper.TABLE_FRUIT + " WHERE " + databaseHelper.COLUMN_NAME_FRUIT + " = " + image;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        Fruit fruit = new Fruit();

        while (!cursor.isAfterLast()){
            fruit.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_FRUIT)));
            fruit.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FRUIT)));
            fruit.setImgFruit(cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE_FRUIT)));
            fruit.setPrice1(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE1_FRUIT))));
            fruit.setPrice2(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRICE2_FRUIT))));
            fruit.setTopping(cursor.getString(cursor.getColumnIndex(COLUMN_TOPPING_FRUIT)));
            cursor.moveToNext();
        }
        return fruit;
    }
}
