package vn.edu.poly.testduan2.Namnn;

import android.graphics.Bitmap;

import java.sql.Blob;

public interface Constant {
    // Database Version
    int DATABASE_VERSION = 1;

    //Table Milk Tea
    String TABLE_MILK_TEA = "Milk";

    String COLUMN_TYPE_MILK_TEA = "Type";

    String COLUMN_NAME_MILK_TEA = "Name";

    String COLUMN_IMAGE_MILK_TEA = "Image";

    String COLUMN_PRICE1_MILK_TEA = "Price1";

    String COLUMN_PRICE2_MILK_TEA = "Price2";

    String COLUMN_TOPPING_MILK_TEA = "Topping";

    String CREATE_TABLE_MILK_TEA = "CREATE TABLE " + TABLE_MILK_TEA + " (" +
            "" + COLUMN_TYPE_MILK_TEA + " VARCHAR," +
            "" + COLUMN_NAME_MILK_TEA + " VARCHAR," +
            "" + COLUMN_IMAGE_MILK_TEA + " BLOB," +
            "" + COLUMN_PRICE1_MILK_TEA + " INTEGER," +
            "" + COLUMN_PRICE2_MILK_TEA + " INTEGER," +
            "" + COLUMN_TOPPING_MILK_TEA + " VARCHAR" +
            ")";


    //Table Bread
    String TABLE_BREAD = "Bread";

    String COLUMN_TYPE_BREAD = "Type";

    String COLUMN_NAME_BREAD = "Name";

    String COLUMN_IMAGE_BREAD = "Image";

    String COLUMN_PRICE1_BREAD = "Price1";

    String COLUMN_PRICE2_BREAD = "Price2";

    String COLUMN_TOPPING_BREAD = "Topping";

    String CREATE_TABLE_BREAD = "CREATE TABLE " + TABLE_BREAD + " (" +
            "" + COLUMN_TYPE_BREAD + " VARCHAR," +
            "" + COLUMN_NAME_BREAD + " VARCHAR," +
            "" + COLUMN_IMAGE_BREAD + " BLOB," +
            "" + COLUMN_PRICE1_BREAD + " INTEGER," +
            "" + COLUMN_PRICE2_BREAD + " INTEGER," +
            "" + COLUMN_TOPPING_BREAD + " VARCHAR" +
            ")";


    //Table Fruit
    String TABLE_FRUIT = "Fruit";

    String COLUMN_TYPE_FRUIT  = "Type";

    String COLUMN_NAME_FRUIT = "Name";

    String COLUMN_IMAGE_FRUIT = "Image";

    String COLUMN_PRICE1_FRUIT = "Price1";

    String COLUMN_PRICE2_FRUIT = "Price2";

    String COLUMN_TOPPING_FRUIT = "Topping";

    String CREATE_TABLE_FRUIT = "CREATE TABLE " + TABLE_FRUIT + " (" +
            "" + COLUMN_TYPE_FRUIT + " VARCHAR," +
            "" + COLUMN_NAME_FRUIT + " VARCHAR," +
            "" + COLUMN_IMAGE_FRUIT + " BLOB," +
            "" + COLUMN_PRICE1_FRUIT + " INTEGER," +
            "" + COLUMN_PRICE2_FRUIT + " INTEGER," +
            "" + COLUMN_TOPPING_FRUIT + " VARCHAR" +
            ")";

}
