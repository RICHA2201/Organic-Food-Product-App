package com.example.bigbasket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView titleTextView, registerTextView;
    private EditText emailEditText, passwordEditText;
    private ImageView logoImageView;
    private Button loginButton;
    private FirebaseAuth mAuth;
    private String email, password;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        titleTextView = findViewById(R.id.title_textview);
        registerTextView = findViewById(R.id.register_textview);

        emailEditText = findViewById(R.id.emailogin_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        logoImageView = findViewById(R.id.logo_imageview);
        loginButton = findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();
        loginButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    emailEditText.setError("Required field...");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    passwordEditText.setError("Required fied...");
                    return;
                }
mAuth.signInWithEmailAndPassword( email,password ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful())
        {
            Toast.makeText(getApplicationContext(),"SUCCESSFULL",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),trial.class));

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
        }

    }
} );
            }
        } );
        registerTextView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),registration.class));
            }
        } );
    }
}