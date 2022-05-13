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

public class SignUpActivity extends AppCompatActivity {
    private Button btnSignUp;
    private TextView textLogin;
    private TextInputEditText fullNameTxt, emailTxt, passwordTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        btnSignUp = findViewById(R.id.btnSignUp);
        textLogin = (TextView) findViewById(R.id.textLogin);
        fullNameTxt = (TextInputEditText) findViewById(R.id.textFullnameSignUp);
        emailTxt = (TextInputEditText) findViewById(R.id.textEmailSignUp);
        passwordTxt = (TextInputEditText) findViewById(R.id.textPasswordSignUp);

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRegister();
            }
        });

    }

    public void submitRegister(){
        String fullName = fullNameTxt.getText().toString().trim();
        String email = emailTxt.getText().toString().trim();
        String password = passwordTxt.getText().toString().trim();

        if(fullName.equals("") || email.equals("") || password.equals("")){
            Toast.makeText(this, "Vui lòng điền đủ thông tin" , Toast.LENGTH_SHORT).show();
            return;
        }

        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().authRegister(fullName, email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    LoginResponse loginResponse = response.body();
                    if(loginResponse.getMessage().equals("Success") && loginResponse.getData() != null){
                        Toast.makeText(SignUpActivity.this, "Success" , Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(SignUpActivity.this, e.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Call Api Error" , Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
}