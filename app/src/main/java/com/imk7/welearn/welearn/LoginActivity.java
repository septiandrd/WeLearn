package com.imk7.welearn.welearn;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.imk7.welearn.welearn.Model.GetUserResponse;
import com.imk7.welearn.welearn.Model.LoginResponse;
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

        final User user = new User(txUsername.getText().toString(),txPassword.getText().toString());
        Call<LoginResponse> call = userClient.login(user);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    progress.dismiss();
                    SaveSharedPreference.setToken(LoginActivity.this,response.body().getToken());
                    Call<GetUserResponse> callUser = userClient.getUser(response.body().getToken());
                    callUser.enqueue(new Callback<GetUserResponse>() {
                        @Override
                        public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                            String username = txUsername.getText().toString();
                            String name = response.body().getUser().getName();
                            String email = response.body().getUser().getEmail();
                            String phone = response.body().getUser().getNoHP();
                            Intent intent = new Intent(getApplicationContext(),MainMenuActivity.class);
                            intent.putExtra("USERNAME",username);
                            intent.putExtra("NAME",name);
                            intent.putExtra("EMAIL",email);
                            intent.putExtra("PHONE",phone);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<GetUserResponse> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Connection Problem", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    progress.dismiss();
                    Toast.makeText(LoginActivity.this, "Invalid Username / Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Connection Problem", Toast.LENGTH_SHORT).show();
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
