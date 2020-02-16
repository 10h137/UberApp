package com.example.uberprac;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button mLogin, mRegister;
    private EditText mEmail, mPass;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogin = (Button) findViewById(R.id.signIn);
        mRegister = (Button) findViewById(R.id.register);

        mEmail = (EditText) findViewById(R.id.email);
        mPass = (EditText) findViewById(R.id.pass);


        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!= null){
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        }


        mRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
                return;
            }
                                  }
        );

        mLogin.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v){
                 Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                 startActivity(intent);
                 finish();
                 return;
             }
         }
        );



    }
}
