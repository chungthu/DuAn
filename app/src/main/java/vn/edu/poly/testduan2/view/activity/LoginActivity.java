package vn.edu.poly.testduan2.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import vn.edu.poly.testduan2.R;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.edtUser)
    EditText edtUser;
    @BindView(R.id.edtPass)
    EditText edtPass;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initialize(@Nullable Bundle savedInstanceState) {

    }

    @OnClick({R.id.checkBox, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.checkBox:
                break;
            case R.id.btnLogin:
                login();
                break;
        }
    }


    public void login() {
        String user = edtUser.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();

        if (user.equals("")) {
            edtUser.setError(getString(R.string.error_empty));
        } else if (pass.equals("")) {
            edtPass.setError(getString(R.string.error_emptypass));
        } else {
            if (user.equals("admin") && pass.equals("12345678")) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        }
    }

    @Override
    protected NavigationStyle getNavigationStyle() {
        return NavigationStyle.None;
    }
}
