package team1.asm_project1_team1.bookapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import team1.asm_project1_team1.R;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPass;
    Button btnLogin, btnLoginGoogle;
    TextView tvGoRegister;
    FirebaseAuth auth;
    ProgressDialog progressDialog;
    String emailCheck = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.etEmailL);
        etPass = findViewById(R.id.etPassL);
        btnLogin = findViewById(R.id.btnLogin);
        btnLoginGoogle = findViewById(R.id.btnGoogleLogin);
        tvGoRegister = findViewById(R.id.tvGoSignUp);
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
//        check save user
//        if(auth.getCurrentUser() != null){
//            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
//            finish();
//        }

        //Go Register
        tvGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        //btn Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerLogin();
            }
        });
    }

    private void PerLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPass.getText().toString().trim();
        //check form
        if (!email.matches(emailCheck)) {
            etEmail.setError("Email không hợp lệ");
            return;
        } else if (password.isEmpty()) {
            etPass.setError("Mật khẩu không hợp lệ");
            return;
        } else {
            progressDialog.setMessage("Xin vui lòng đợi ...");
            progressDialog.setTitle("Đang đăng nhập");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
        //login
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
                    Log.d("ThatBai", task.getException().getMessage());
                }
            }
        });
    }
}