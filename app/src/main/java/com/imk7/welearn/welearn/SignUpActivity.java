package com.imk7.welearn.welearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;

import com.imk7.welearn.welearn.Model.SaveSharedPreference;
import com.imk7.welearn.welearn.Model.SignUpResponse;
import com.imk7.welearn.welearn.Model.User;
import com.imk7.welearn.welearn.Service.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    Button btnRegister;
    EditText txNama1,txNama2,txPhone,txEmail,txUsername,txPass1,txPass2;
    CheckBox cbAgree;
    String name;
    ProgressDialog progress;

    Retrofit.Builder builer = new Retrofit.Builder()
            .baseUrl("http://192.168.100.7/")
            .addConverterFactory(GsonConverterFactory.create());

//    .baseUrl("https://welearnapp.000webhostapp.com/")
    Retrofit retrofit = builer.build();

    UserClient userClient = retrofit.create(UserClient.class);

    public static boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnRegister = findViewById(R.id.btn_register);
        txNama1 = findViewById(R.id.edt_reg_firstname);
        txNama2 = findViewById(R.id.edt_reg_lastname);
        txPhone = findViewById(R.id.edt_reg_phone);
        txEmail = findViewById(R.id.edt_reg_email);
        txUsername = findViewById(R.id.edt_reg_username);
        txPass1 = findViewById(R.id.edt_reg_password);
        txPass2 = findViewById(R.id.edt_reg_confpassword);
        cbAgree = findViewById(R.id.cb_agree);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(txNama1.getText()) || TextUtils.isEmpty(txNama2.getText()) || TextUtils.isEmpty(txPhone.getText())
                        || TextUtils.isEmpty(txEmail.getText()) || TextUtils.isEmpty(txPass1.getText()) || TextUtils.isEmpty(txPass2.getText())) {
                    if (TextUtils.isEmpty(txNama1.getText())) {
                        txNama1.setError("This field is required!");
                    }
                    if (TextUtils.isEmpty(txNama2.getText())) {
                        txNama2.setError("This field is required!");
                    }
                    if (TextUtils.isEmpty(txPhone.getText())) {
                        txPhone.setError("This field is required!");
                    }
                    if (TextUtils.isEmpty(txEmail.getText())) {
                        txEmail.setError("This field is required!");
                    }
                    if (TextUtils.isEmpty(txUsername.getText())) {
                        txUsername.setError("This field is required!");
                    }
                    if (TextUtils.isEmpty(txPass1.getText())) {
                        txPass1.setError("This field is required!");
                    }
                    if (TextUtils.isEmpty(txPass2.getText())) {
                        txPass2.setError("This field is required!");
                    }
                } else if ( !isValidEmail(txEmail.getText()) ) {
                    txEmail.setError("Please enter a valid email address!");
                } else if ( !isValidMobile(txPhone.getText().toString())) {
                    txPhone.setError("Please enter a valid phone number!");
                } else if (!txPass1.getText().toString().equals(txPass2.getText().toString())) {
                    txPass2.setError("Password doesn't match!");
                } else {
                    if (!cbAgree.isChecked()) {
                        Toast.makeText(getApplicationContext(), "Please check the Registration Agreement!", Toast.LENGTH_SHORT).show();
                    } else {
                        progress  = new ProgressDialog(v.getContext());
                        progress.setMessage("Signing Up...");
                        progress.show();
                        signUp();
                    }
                }
            }
        });

    }

    private void signUp() {

        User user = new User();
        name = txNama1.getText().toString() + " " + txNama2.getText().toString();
        user.setName(name);
        user.setNoHP("0"+txPhone.getText().toString());
        user.setEmail(txEmail.getText().toString());
        user.setPassword(txPass1.getText().toString());
        user.setUsername(txUsername.getText().toString());

        Call<SignUpResponse> call = userClient.signup(user);

        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                String code = response.body().getCode();
                if(code.equals("SUCCESS")) {
                    progress.dismiss();
                    SaveSharedPreference.setToken(SignUpActivity.this,txUsername.getText().toString());
                    Intent intent = new Intent(getApplicationContext(),MainMenuActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    progress.dismiss();
                    Toast.makeText(SignUpActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(SignUpActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

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


