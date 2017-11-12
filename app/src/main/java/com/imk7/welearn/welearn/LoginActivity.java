package com.imk7.welearn.welearn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.imk7.welearn.welearn.Model.SaveSharedPreference;
import com.imk7.welearn.welearn.Model.User;
import com.imk7.welearn.welearn.Service.UserClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText txUsername, txPassword;

    Retrofit.Builder builer = new Retrofit.Builder()
            .baseUrl("https://welearnapp.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builer.build();

    UserClient userClient = retrofit.create(UserClient.class);

    ProgressDialog progress;

    private static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txUsername = findViewById(R.id.edt_login_username);
        txPassword = findViewById(R.id.edt_login_password);
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress  = new ProgressDialog(view.getContext());
                progress.setMessage("Logging In...");
                progress.show();
                login();
            }
        });

    }

    private void login() {

        User user = new User(txUsername.getText().toString(),txPassword.getText().toString());
        Call<ResponseBody> call = userClient.login(user);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    progress.dismiss();
                    SaveSharedPreference.setUSERNAME(LoginActivity.this,txUsername.getText().toString());
                    Intent intent = new Intent(getApplicationContext(),MainMenuActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    progress.dismiss();
                    Toast.makeText(LoginActivity.this, "Invalid Username / Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ForgotClicked(View v) {
        Toast.makeText(getApplicationContext(), "Sorry, this feature is not available yet.", Toast.LENGTH_SHORT).show();
    }

    public void SocialClicked(View v) {
        Toast.makeText(getApplicationContext(), "Sorry, this feature is not available yet.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),WelcomeScreenActivity.class);
        startActivity(intent);
        finish();
    }


}
