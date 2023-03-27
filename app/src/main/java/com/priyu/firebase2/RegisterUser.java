package com.priyu.firebase2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {
    EditText email ,password;
    Button btn2;
    TextView acc1;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        email = findViewById(R.id.editTextTextEmailAddress2);
        password = findViewById(R.id.editTextTextPassword2);
        btn2 = findViewById(R.id.register);
        acc1 = findViewById(R.id.alreadyy);

        auth = FirebaseAuth.getInstance();

        acc1.setOnClickListener(v -> {
            Intent intent  = new Intent(RegisterUser.this,MainActivity.class);
            startActivity(intent);
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final   String uemail = email.getText().toString();
                final     String upassword = password.getText().toString();

                auth.createUserWithEmailAndPassword(uemail,upassword).addOnCompleteListener(task -> {

                    if (task.isSuccessful()){
                        Toast.makeText(RegisterUser.this, "user created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), LogOut.class));
                    }else {
                        Toast.makeText(RegisterUser.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });



    }
}
