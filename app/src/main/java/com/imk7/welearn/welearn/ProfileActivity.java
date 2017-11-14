package com.imk7.welearn.welearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView txName, txUsername, txEmail, txPhone;
    String name;
    String username;
    String email;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        username = intent.getStringExtra("USERNAME");
        email = intent.getStringExtra("EMAIL");
        phone = intent.getStringExtra("PHONE");

        txName = findViewById(R.id.profile_name);
        txUsername = findViewById(R.id.profile_username);
        txEmail = findViewById(R.id.profile_email);
        txPhone = findViewById(R.id.profile_phone);

        txName.setText(name);
        txUsername.setText(username);
        txEmail.setText(email);
        txPhone.setText(phone);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MainMenuActivity.class);
        intent.putExtra("USERNAME",username);
        intent.putExtra("NAME",name);
        intent.putExtra("EMAIL",email);
        intent.putExtra("PHONE",phone);
        startActivity(intent);
        finish();
    }
}
