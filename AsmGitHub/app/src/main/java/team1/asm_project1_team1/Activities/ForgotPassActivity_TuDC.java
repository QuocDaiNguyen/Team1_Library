package team1.asm_project1_team1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import team1.asm_project1_team1.R;

public class ForgotPassActivity_TuDC extends AppCompatActivity {

    EditText emailconfirm;
    Button emailButton, loginGoogle;
    TextView backlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);


        emailconfirm = findViewById(R.id.etEmail);
        emailButton = findViewById(R.id.btnEmail);
        loginGoogle = findViewById(R.id.btnGoogle);

        backlogin = findViewById(R.id.tvBackLogin);


        emailButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String email = emailconfirm.getText().toString();

                Intent intent = new Intent(ForgotPassActivity_TuDC.this, ResetPassEmail_TuDC.class);
                startActivity(intent);
            }
        });
        loginGoogle.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassActivity_TuDC.this, team1.asm_project1_team1.Activities.LoginActivity_TuDC.class);
                startActivity(intent);
            }
        });

        backlogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassActivity_TuDC.this, LoginActivity_TuDC.class);
                startActivity(intent);
            }
        });

    }
}