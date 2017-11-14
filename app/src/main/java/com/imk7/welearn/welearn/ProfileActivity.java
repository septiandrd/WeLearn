package com.imk7.welearn.welearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    Intent intent = getIntent();
    String name = intent.getStringExtra("NAME");
    String username = intent.getStringExtra("USERNAME");
    String email = intent.getStringExtra("EMAIL");
    String phone = intent.getStringExtra("PHONE");

    TextView txName, txUsername, txEmail, txPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
        startActivity(intent);
        finish();
    }
}
