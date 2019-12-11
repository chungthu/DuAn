package vn.edu.poly.testduan2.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.evenBus.EvenLogin;
import vn.edu.poly.testduan2.common.evenBus.EventBusAction;
import vn.edu.poly.testduan2.net.firebase.FirebaseManager;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.edtUser)
    EditText edtUser;
    @BindView(R.id.edtPass)
    EditText edtPass;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tv_confimpassword)
    TextView tvConfimpassword;
    private FirebaseManager firebaseManager = new FirebaseManager();
    public AlertDialog waitingDialog;
    private AlertDialog.Builder dialogLoginFaill;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initialize(@Nullable Bundle savedInstanceState) {
        this.waitingDialog = new SpotsDialog.Builder().setMessage("Loading").setContext(this).build();
        this.dialogLoginFaill = new AlertDialog.Builder(LoginActivity.this);
        dialogLoginFaill.setTitle("Login Faill");
        dialogLoginFaill.setMessage("The username or password is incorrect");
        dialogLoginFaill.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }

    @OnClick({R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            waitingDialog.show();
            firebaseManager.login(this, user, pass);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (waitingDialog.isShowing()) {
                        waitingDialog.dismiss();
                    }
                }
            }, 2000);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleLoginEvent(EvenLogin event) throws IOException {
        switch (event.action) {
            case EventBusAction.LOGIN_SUCCESS:
                waitingDialog.dismiss();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;
            case EventBusAction.LOGIN_FAILL:
                waitingDialog.dismiss();
                dialogLoginFaill.show();
                break;
            default:
                break;
        }
    }


    @Override
    protected NavigationStyle getNavigationStyle() {
        return NavigationStyle.None;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.tv_confimpassword)
    public void onViewClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("Contact manager to reset password!");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}
