package com.example.mymathgaming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.net.DatagramSocket;

public class LoginActivity extends AppCompatActivity {
    private int count = 3;
    private TextView attempts;
    @BindView(R.id.registerTextView)
    TextView mRegisterTextView;
    @BindView(R.id.passwordLoginButton)
    Button mPasswordLoginButton;
    @BindView(R.id.emailEditText)
    EditText mEmailEditText;
    @BindView(R.id.passwordEditText)
    EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPasswordLoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view){
                validate(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString());
            }
        });

    }
    public void validate(String userName, String userPassword){
       if((userName == "android") && (userPassword =="android")){
         Intent intent = new Intent(this, NewsListActivity.class);
         startActivity(intent);
       }else{
           count --;
           attempts.setText("Number of attempts remaining" + String.valueOf(count));
           if(count == 0){
               mPasswordLoginButton.setEnabled(false);
           }
//           Toast.makeText()
       }
    }
}