package vn.edu.poly.testduan2.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import vn.edu.poly.testduan2.common.Constants;

public class Utils {

    public static String readSharedPreferences(Context mContext, String keyName, String defaultValue) {
        SharedPreferences preferences = mContext.getSharedPreferences(Constants.FILE_NAME, Context.MODE_PRIVATE);
//        SharedPreferences sharedPreferences = mContext.getSharedPreferences(keyName, Context.MODE_PRIVATE);
        return preferences.getString(keyName, defaultValue);
    }

    public static void saveSharedPreferences(Context mContext, String keyName, String values) {
        SharedPreferences preferences = mContext.getSharedPreferences(Constants.FILE_NAME, Context.MODE_PRIVATE);
//        SharedPreferences sharedPreferences = mContext.getSharedPreferences(keyName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(keyName, values);
        editor.apply();
    }

    public static void clearSharedPreferences(Context mContext) {
        SharedPreferences preferences = mContext.getSharedPreferences(Constants.FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public static void removeKeySharedPreferences(Context mContext, String keyName) {
        SharedPreferences preferences = mContext.getSharedPreferences(Constants.FILE_NAME, Context.MODE_PRIVATE);
//        SharedPreferences sharedPreferences = mContext.getSharedPreferences(keyName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(keyName);
        editor.apply();
    }
}
