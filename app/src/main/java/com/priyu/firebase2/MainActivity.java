package com.priyu.firebase2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.stream.DoubleStream;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2;
    Button btn;
    TextView txt1;
    FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Auth = FirebaseAuth.getInstance();

        ed1 = findViewById(R.id.editTextTextEmailAddress);
        ed2 = findViewById(R.id.editTextTextPassword);
        btn = findViewById(R.id.login);
        txt1 = findViewById(R.id.account);

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterUser.class);
                startActivity(intent);
            }
        });

        if(Auth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),LogOut.class));
            finish();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String smail = ed1.getText().toString();
                final String spass = ed2.getText().toString();
                Auth.signInWithEmailAndPassword(smail,spass).addOnCompleteListener(task -> {
                   if (task.isSuccessful()){
                       Toast.makeText(MainActivity.this,"logged Success ",Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(getApplicationContext(),LogOut.class));
                   }else
                   {
                       Toast.makeText(MainActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();

                   }
                });
            }
        });
        }}