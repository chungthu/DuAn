package vn.edu.poly.testduan2.view.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    Unbinder bind;

    protected abstract int getActivityLayoutId();

    protected abstract void initialize(@Nullable Bundle savedInstanceState);

    protected void initBeforeSetContent(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforeSetContent(savedInstanceState);
        setContentView(getActivityLayoutId());
        bind = ButterKnife.bind(this);
        initialize(savedInstanceState);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) bind.unbind();
    }
}
