package com.learning.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String PREF_NAME = "Preferences";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_PASSWORD = "password";

    private String realLogin = "";
    private String realPassword = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText loginEdit = (EditText) findViewById(R.id.loginEditText);
        final EditText passwordEdit = (EditText) findViewById(R.id.passwordEditText);
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        Button registerButton = (Button) findViewById(R.id.registerButton);

        SharedPreferences preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        realLogin = preferences.getString(KEY_LOGIN, "");
        realPassword = preferences.getString(KEY_PASSWORD, "");

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                SharedPreferences preferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(KEY_LOGIN, login);
                editor.putString(KEY_PASSWORD, password);
                editor.apply();
                // Update login and password
                realLogin = login;
                realPassword = password;
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                if (login.equals(realLogin)) {
                    if (password.equals(realPassword)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra(KEY_LOGIN, login);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
