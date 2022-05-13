package org.o7planning.nhom3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.o7planning.nhom3.controller.DoctorAdapter;
import org.o7planning.nhom3.model.LoginResponse;

public class AppointmentActivity extends AppCompatActivity {
    private ImageView backView;
    private ConstraintLayout doctorLayout;
    private LoginResponse loginResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment);

        backView = (ImageView) findViewById(R.id.btnBack);
        doctorLayout = findViewById(R.id.doctorAppointment);

        loginResponse = (LoginResponse) getIntent().getSerializableExtra("loginAccount");

        doctorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDoctor();
            }
        });

        backView.setClickable(true);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHomePage();
            }
        });
    }

    public void openDoctor(){
        Intent intent = new Intent(this, DoctorActivity.class);
        intent.putExtra("loginAccount", loginResponse);
        startActivity(intent);
    }

    public void backToHomePage(){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("loginAccount", loginResponse);
        startActivity(intent);
    }
}