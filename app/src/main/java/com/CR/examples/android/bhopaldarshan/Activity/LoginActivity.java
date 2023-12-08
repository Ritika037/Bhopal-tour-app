package com.CR.examples.android.bhopaldarshan.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.CR.examples.android.bhopaldarshan.R;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private boolean passwordShow=false;
    FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//          Intent i=new Intent(getApplicationContext(),Homescreen.class);
//          startActivity(i);
//          finish();
//        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText usernameET=findViewById(R.id.username);
        mAuth=FirebaseAuth.getInstance();
        final ProgressBar pbr=findViewById(R.id.progressbar);
        final Button signin=findViewById(R.id.signinbtn);
        final EditText passwordET=findViewById(R.id.password);
        final TextView signup=findViewById(R.id.signupbtn);
        final ImageView passwordicon=findViewById(R.id.passworicon);
        passwordicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passwordShow){
                    passwordShow=false;
                    passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordicon.setImageResource(R.drawable.show);
                }
                else{
                    passwordShow=true;
                    passwordET.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordicon.setImageResource(R.drawable.hide);
                }
                // moving at the last of the cursor//
                passwordET.setSelection(passwordET.length());
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pbr.setVisibility(view.VISIBLE);
                String Email=usernameET.getText().toString().trim();
                String Password=passwordET.getText().toString().trim();
                if(TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password)){
                    Toast.makeText(LoginActivity.this, "Enter All The Values", Toast.LENGTH_SHORT).show();
                    pbr.setVisibility(view.GONE);
                }
                else{
                    mAuth.signInWithEmailAndPassword(Email,Password)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    pbr.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Intent init=new Intent(LoginActivity.this,MainActivity.class);
                                        startActivity(init);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(LoginActivity.this, "Authentication failed. Please Try Again",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }
}