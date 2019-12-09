package vn.edu.poly.testduan2.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.Constants;
import vn.edu.poly.testduan2.common.utils.Utils;
import vn.edu.poly.testduan2.net.firebase.FirebaseManager;

public class WelcomeActivity extends BaseActivity {

    Timer timer = new Timer();
    String id = "";
    FirebaseManager firebaseManager = new FirebaseManager();

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initialize(@Nullable Bundle savedInstanceState) {
        setup();
    }

    @Override
    protected NavigationStyle getNavigationStyle() {
        return NavigationStyle.None;
    }

    private void setup() {
        id = Utils.readSharedPreferences(this, Constants.LOGIN_SUCCESS, "");
        if (!id.equals("")){
            firebaseManager.getUserbyID(id);
        }
        if (id.equals("")) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                    finish();
                }
            }, 1000);
        }else {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                }
            },1000);
        }
    }
}
