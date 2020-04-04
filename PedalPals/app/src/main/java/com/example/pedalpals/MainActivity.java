package com.example.pedalpals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    Database db;

    EditText username, password;
    Button login, signup, admin;
    TextView txt_incorrect_user;
    SharedPreferences prefs;
    Boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login_button);
        signup = findViewById(R.id.signup_button);
        admin = findViewById(R.id.admin_button);
        txt_incorrect_user = findViewById(R.id.text_incorrect_user);

        prefs = this.getSharedPreferences("PedalPals", 0);
        isLogin = prefs.getBoolean("userlogin", false);
        gotoSignup();;
        logging();
        adminLogin();

    }

    private void logging() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean res = db.login_User(username.getText().toString(), password.getText().toString());

               if(res){
                   Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                   prefs.edit().putString("username", username.getText().toString()).apply();

                   SharedPreferences.Editor edit = prefs.edit();
                   edit.putBoolean("userlogin", true);
                   edit.apply();

                    Intent i = new Intent(MainActivity.this, Menu.class);
                    startActivity(i);
               }
               else{
                   String s = "Incorrect Username or Password";
                   txt_incorrect_user.setText(s);
                   username.getText().clear();
                   password.getText().clear();
               }
            }
        });
    }

    private void adminLogin(){
        admin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, AdminLogin.class);
                        startActivity(i);
                    }
                }
        );
    }

    private void gotoSignup(){
        signup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Signup.class);
                        startActivity(i);
                    }
                }
        );
    }


}