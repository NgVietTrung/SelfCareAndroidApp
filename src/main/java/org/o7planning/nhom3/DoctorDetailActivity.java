package org.o7planning.nhom3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.o7planning.nhom3.model.Doctors;
import org.o7planning.nhom3.model.LoginResponse;

public class DoctorDetailActivity extends AppCompatActivity {
    private TextView textDoctorName, textDoctorSpecialization;
    private ImageView imageDoctor, backView;
    private LoginResponse loginResponse;
    private Doctors.DoctorData.DoctorRecords doctor;
    private Button btnTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_detail);

        backView = (ImageView) findViewById(R.id.btnBack);
        btnTime = findViewById(R.id.btnTime);
        textDoctorName = findViewById(R.id.doctorDetailName);
        textDoctorSpecialization = findViewById(R.id.doctorDetailSpecialization);
        imageDoctor = findViewById(R.id.doctorDetailImage);


        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        doctor = (Doctors.DoctorData.DoctorRecords) bundle.get("object_doctor");
        loginResponse = (LoginResponse) getIntent().getSerializableExtra("loginAccount");

//        imageDoctor.setImageResource(doctor.getImage());
        textDoctorName.setText(doctor.getFullName());
        textDoctorSpecialization.setText(doctor.getSpecialisation());

        backView.setClickable(true);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToDoctorPage();
            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSchedulePage();
            }
        });
    }

    public void backToDoctorPage(){
        Intent intent = new Intent(this, DoctorActivity.class);
        intent.putExtra("loginAccount", loginResponse);
        startActivity(intent);
    }

    public void openSchedulePage(){
        Intent intent = new Intent(this, ScheduleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_doctor", doctor);
        intent.putExtra("loginAccount", loginResponse);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}