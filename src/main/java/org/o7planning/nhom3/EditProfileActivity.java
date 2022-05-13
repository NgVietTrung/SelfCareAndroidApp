package org.o7planning.nhom3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;
import org.o7planning.nhom3.api.RetrofitClient;
import org.o7planning.nhom3.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView backView;
    private Button btnUpdateProfile;
    private LoginResponse loginResponse;
    private TextInputEditText textUsername, textPassword, textNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        btnUpdateProfile = findViewById(R.id.btnUpdateProfile);
        backView = (ImageView) findViewById(R.id.btnBack);
        textUsername = (TextInputEditText) findViewById(R.id.textUpdateUsername);
        textPassword = (TextInputEditText) findViewById(R.id.textUpdatePassword);
        textNewPassword = (TextInputEditText) findViewById(R.id.textUpdateNewPassword);

        loginResponse = (LoginResponse) getIntent().getSerializableExtra("loginAccount");
        textUsername.setText(loginResponse.getData().getUser().getFullName());

        backView.setClickable(true);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHomePage();
            }
        });

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
    }

    public void backHomePage(){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("loginAccount", loginResponse);
        startActivity(intent);
    }

    public void updateProfile(){
        String username = textUsername.getText().toString().trim();
        String password = textPassword.getText().toString().trim();
        String newPassword = textNewPassword.getText().toString().trim();

        if(username.equals("") || password.equals("") || newPassword .equals("") ){
            Toast.makeText(this, "Vui lòng điền đủ thông tin" , Toast.LENGTH_SHORT).show();
            return;
        }

        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().authUpdateProfile(
                "Bearer " + loginResponse.getData().getAccessToken(),
                username, password, newPassword);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    LoginResponse loginResponseUpdate = response.body();
                    if(loginResponse.getMessage().equals("Success") && loginResponse.getData() != null){
                        Toast.makeText(EditProfileActivity.this, "Cập nhật thông tin thành công" , Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
                        loginResponse = loginResponseUpdate;
                        intent.putExtra("loginAccount", loginResponse);
                        startActivity(intent);
                    }
                }
                else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        Toast.makeText(EditProfileActivity.this, message, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(EditProfileActivity.this, e.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Kết nối thật bại" , Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
}