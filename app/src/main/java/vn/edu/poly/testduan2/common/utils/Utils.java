package vn.edu.poly.testduan2.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import vn.edu.poly.testduan2.common.ConstactChange;
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

    public static String nameBill(){
        return "BILL_"+ConstactChange.USER_RESPONSE.getUsername()+"0"+ConstactChange.USER_RESPONSE.getBill_position();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
