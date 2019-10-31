package vn.edu.poly.testduan2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUser;
    private EditText edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
    }

    public void Login(View view) {
        save();
    }

    public void save() {
        String user = edtUser.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();

        if (user.equals("")) {
            edtUser.setError(getString(R.string.error_empty));
        } else if (pass.equals("")) {
            edtPass.setError(getString(R.string.error_emptypass));
        } else {
            if (user.equals("admin") && pass.equals("12345678")) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            } else {
                startActivity(new Intent(getApplicationContext(), Menu2Activity.class));
            }
        }
    }
}
