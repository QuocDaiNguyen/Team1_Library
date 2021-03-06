package team1.asm_project1_team1.Activities;

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


public class RegisterActivity_TuDC extends AppCompatActivity {

    EditText etFullName, etEmail, etPass, etConfirmPass;
    Button btnRegister, btnRegisterGoogle;
    TextView tvGoBack;
    FirebaseAuth auth;
    ProgressDialog progressDialog;
    String emailCheck = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        etConfirmPass = findViewById(R.id.etConfirmPass);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegisterGoogle = findViewById(R.id.btnGoogleRegister);
        tvGoBack = findViewById(R.id.tvBackLogin);
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        //Go Back Login
        tvGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity_TuDC.this, LoginActivity_TuDC.class));
            }
        });

        //btn Register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerAuth();
            }
        });
    }

    private void PerAuth() {
        String email = etEmail.getText().toString().trim();
        String password = etPass.getText().toString().trim();
        String confirmPassword = etConfirmPass.getText().toString().trim();
        //check form
        if (!email.matches(emailCheck)) {
            etEmail.setError("Email kh??ng h???p l???");
            return;
        } else if (password.isEmpty()) {
            etPass.setError("M???t kh???u kh??ng h???p l???");
            return;
        } else if (!password.equals(confirmPassword)) {
            etConfirmPass.setError("M???t kh???u kh??ng kh???p");
            return;
        } else {
            progressDialog.setMessage("Xin vui l??ng ?????i ...");
            progressDialog.setTitle("??ang kh???i t???o");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
        //create User
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity_TuDC.this, "????ng k?? th??nh c??ng", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity_TuDC.this, LoginActivity_TuDC.class));
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity_TuDC.this, "????ng k?? th???t b???i", Toast.LENGTH_LONG).show();
                    Log.d("ThatBai", task.getException().getMessage());
                }
            }
        });
    }
}