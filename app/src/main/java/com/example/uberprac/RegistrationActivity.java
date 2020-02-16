package com.example.uberprac;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private Button mRegister;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private EditText mEmail, mPass;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mRegister = (Button) findViewById(R.id.register);
        mEmail = (EditText) findViewById(R.id.email);
        mPass = (EditText) findViewById(R.id.pass1);

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!= null){
                    Intent intent = new Intent(RegistrationActivity.this, MapsActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        mRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View w){
               final String email = mEmail.getText().toString();
               final String pass = mPass.getText().toString();
               mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(!task.isSuccessful()){
                           Toast.makeText(RegistrationActivity.this, "sign up error", Toast.LENGTH_SHORT).show();
                       }else{
                           String user_id = mAuth.getCurrentUser().getUid();
                           DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("Users");
                       }
                   }
               })


            }
        });

    }



}
