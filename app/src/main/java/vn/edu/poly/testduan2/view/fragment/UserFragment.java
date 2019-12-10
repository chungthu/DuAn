package vn.edu.poly.testduan2.view.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.common.internal.service.Common;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.Constants;
import vn.edu.poly.testduan2.common.evenBus.EvenLogin;
import vn.edu.poly.testduan2.common.evenBus.EventBusAction;
import vn.edu.poly.testduan2.common.utils.Utils;
import vn.edu.poly.testduan2.net.firebase.FirebaseManager;
import vn.edu.poly.testduan2.net.response.UserResponse;
import vn.edu.poly.testduan2.view.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends BaseFragment {


    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.ll_chPassword)
    LinearLayout llChPassword;
    @BindView(R.id.ll_logout)
    LinearLayout llLogout;
    @BindView(R.id.tv_name_account)
    TextView tvNameAccount;
    private UserResponse userResponse;
    FirebaseManager firebaseManager = new FirebaseManager();

    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initializeViews(View view, Bundle savedInstanceState) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void handleLoginEvent(EvenLogin event) throws IOException {
        switch (event.action) {
            case EventBusAction.LOGIN_SUCCESS:
                userResponse = (UserResponse) event.object;
                tvNameAccount.setText(userResponse.getUsername());
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleUpdateEvent(EvenLogin event) throws IOException {
        switch (event.action) {
            case EventBusAction.UPDATE_USER_SUCCESS:
                AlertDialog.Builder dialog_builder = new AlertDialog.Builder(getContext());
                dialog_builder.setMessage(getString(R.string.key_changePassword_Success));
                dialog_builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog_builder.setCancelable(true);
                    }
                });
                dialog_builder.show();
            default:
                break;
        }
    }

    @OnClick({R.id.ll_chPassword, R.id.ll_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_chPassword:
                changePassword();
                break;
            case R.id.ll_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getString(R.string.key_logout));
                builder.setMessage(getString(R.string.quizLogout));
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Utils.removeKeySharedPreferences(getContext(), Constants.LOGIN_SUCCESS);
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    private void changePassword() {
        final EditText old_password;
        final EditText new_password;
        final EditText reEnter_password;

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_change_password, null);
        old_password = alertLayout.findViewById(R.id.edt_old_password);
        new_password = alertLayout.findViewById(R.id.edt_new_password);
        reEnter_password = alertLayout.findViewById(R.id.edt_re_enter_password);

        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle(getString(R.string.key_changePassword));
        alert.setView(alertLayout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (old_password.getText().toString().equals("") ||
                        new_password.getText().toString().equals("") ||
                        reEnter_password.getText().toString().equals("")) {
                    AlertDialog.Builder dialog_builder = new AlertDialog.Builder(getContext());
                    dialog_builder.setMessage(getString(R.string.key_emtry));
                    dialog_builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog_builder.show();
                } else if (!new_password.getText().toString().equals(reEnter_password.getText().toString())) {
                    AlertDialog.Builder dialog_builder = new AlertDialog.Builder(getContext());
                    dialog_builder.setMessage(getString(R.string.key_Password_does_not_match));
                    dialog_builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog_builder.show();
                } else if (!old_password.getText().toString().equals(userResponse.getPassword())) {
                    AlertDialog.Builder dialog_builder = new AlertDialog.Builder(getContext());
                    dialog_builder.setMessage(getString(R.string.key_Incorrect_password));
                    dialog_builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog_builder.show();
                } else {
                    userResponse.setPassword(new_password.getText().toString().trim());
                    firebaseManager.updateUser(userResponse.getId(), userResponse);
                }
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}
