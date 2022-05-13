package org.o7planning.nhom3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;
import org.o7planning.nhom3.api.RetrofitClient;
import org.o7planning.nhom3.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView textSignUp;
    private TextInputEditText emailTxt, passwordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnLogin = findViewById(R.id.btnLogin);
        textSignUp = (TextView) findViewById(R.id.textSignUp);
        emailTxt = (TextInputEditText) findViewById(R.id.textEmail);
        passwordTxt = (TextInputEditText) findViewById(R.id.textPassword);

        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });
    }

    public void openHomePage(){
        String email = emailTxt.getText().toString().trim();
        String password = passwordTxt.getText().toString().trim();

        if(email.equals("") || password.equals("")){
            Toast.makeText(this, "Vui lòng điền đủ thông tin" , Toast.LENGTH_SHORT).show();
            return;
        }

        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().authLogin(email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    LoginResponse loginResponse = response.body();
                    if(loginResponse.getMessage().equals("Success") && loginResponse.getData() != null){
                        Toast.makeText(LoginActivity.this, loginResponse.getData().getUser().getFullName() , Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("loginAccount", loginResponse);
                        startActivity(intent);
                    }
                }
                else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Call Api Error" , Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    public void openSignUp(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}