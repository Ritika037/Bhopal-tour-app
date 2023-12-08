package com.CR.examples.android.bhopaldarshan.Activity;

import androidx.annotation.NonNull;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.CR.examples.android.bhopaldarshan.R;

public class RegisterActivity extends AppCompatActivity {
    private boolean passwordiconshowing=false;
    FirebaseAuth mAuth;

    private boolean conpasswordiconshowing=false;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            Intent i=new Intent(getApplicationContext(),Homescreen.class);
//            startActivity(i);
//            finish();
//        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        final EditText name=findViewById(R.id.name);
        final ProgressBar pbr=findViewById(R.id.progressbar);
        final EditText email=findViewById(R.id.email);
        final EditText phone=findViewById(R.id.mobile);
        final EditText password=findViewById(R.id.password);
        final ImageView passwordicon=findViewById(R.id.passworicon);
        final ImageView conpasswordicon=findViewById(R.id.conpassworicon);
        final EditText conpassword=findViewById(R.id.conpassword);
        final Button signupbtn=findViewById(R.id.signupbtn);
        final TextView signin=findViewById(R.id.regsignin);
        passwordicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passwordiconshowing){
                    passwordiconshowing=false;
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordicon.setImageResource(R.drawable.hide);
                }
                else{
                    passwordiconshowing=true;
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordicon.setImageResource(R.drawable.show);
                }
                // moving at the last of the cursor//
                password.setSelection(password.length());
            }
        });
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                pbr.setVisibility(view.VISIBLE);
                final String Name=name.getText().toString();
                final String Password=password.getText().toString();
                final String Email=email.getText().toString().trim();
                final String Phone=phone.getText().toString();
                String Conpassword=conpassword.getText().toString();
                if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(Password) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(Phone)||TextUtils.isEmpty(Conpassword)){
                    Toast.makeText(RegisterActivity.this, "Please Enter All The values", Toast.LENGTH_SHORT).show();
                    pbr.setVisibility(View.GONE);
                } else if (!Password.equals(Conpassword) || Password.length()<6) {
                    conpassword.setError("Passwords Are Not Matching or too small");
                    pbr.setVisibility(View.GONE);
                } else if (Phone.length()<10 || Phone.length()>10 ){
                    phone.setError("Number should be 10 digits");
                    pbr.setVisibility(View.GONE);
                } else{

                    mAuth.createUserWithEmailAndPassword(Email,Password)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    pbr.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Users data=new Users(Name,Email,Phone,Password);
                                        String id=task.getResult().getUser().getUid();
                                        Log.d("data","id"+id);
                                        DatabaseReference datareference=FirebaseDatabase.getInstance().getReference("Users");
                                        datareference.child(id).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()) {
                                                    name.setText("");
                                                    email.setText("");
                                                    phone.setText("");
                                                    password.setText("");
                                                    conpassword.setText("");
                                                    Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                                }
                                                else{
                                                    Toast.makeText(RegisterActivity.this, "Authentication failed.1",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(RegisterActivity.this, "Authentication failed. user",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }

        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });
         conpasswordicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conpasswordiconshowing){
                    conpasswordiconshowing=false;
                    conpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    conpasswordicon.setImageResource(R.drawable.hide);
                }
                else{
                    conpasswordiconshowing=true;
                    conpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    conpasswordicon.setImageResource(R.drawable.show);
                }
                // moving at the last of the cursor//
                conpassword.setSelection(conpassword.length());
            }

        });
    }
}