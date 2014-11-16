package com.inspirecore.bustruckerapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.inspirecore.bustruckerapp.controls.LoginControl;
import com.inspirecore.bustruckerapp.controls.NetworkUtil;


public class LoginActivity extends ActionBarActivity implements View.OnKeyListener{

    LoginControl loginControl;
    EditText username_et, password_et;
    TextView login_bt;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginControl = new LoginControl(this);

        username_et = (EditText) findViewById(R.id.username);
        password_et = (EditText) findViewById(R.id.password);
        password_et.setOnKeyListener(this);

        login_bt = (TextView) findViewById(R.id.login_bt);
        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        switch (v.getId()) {

            case R.id.password:
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    login();
                }
                break;

        }
        return false;
    }

    public void login(){
        username = username_et.getText().toString();
        password = password_et.getText().toString();

        if (username.trim().length() == 0) {
            Toast.makeText(this, "Enter Your Email", Toast.LENGTH_SHORT).show();
            username_et.requestFocus();
        }
        else if (password.trim().length() == 0) {
            Toast.makeText(this, "Enter Your password", Toast.LENGTH_SHORT).show();
            password_et.requestFocus();
        }else{
            boolean Connected = NetworkUtil.getConnectivityStatusString(this);
            if (Connected) {
                loginControl.doLogin(username, password);
            }else{
                Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
