package com.android.mobile.findcure.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.mobile.findcure.R;
import com.google.firebase.auth.FirebaseAuth;

public class VerifyActivity extends AppCompatActivity {

    Button verifyBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        verifyBtn = findViewById(R.id.verifyBtn);
        mAuth = FirebaseAuth.getInstance();


        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VerifyActivity.this, Dashboard.class));
                finish();
            }
        });
    }
}