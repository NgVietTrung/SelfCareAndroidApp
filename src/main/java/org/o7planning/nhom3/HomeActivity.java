package org.o7planning.nhom3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.o7planning.nhom3.model.LoginResponse;

public class HomeActivity extends AppCompatActivity {
    private TextView textHello;
    private ConstraintLayout editProfileLayout, historyLayout, healthIndexLayout, appointmentLayout;
    private LoginResponse loginResponse, loginUpdateResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        textHello = findViewById(R.id.textHello);
        editProfileLayout = findViewById(R.id.editProfileCard);
        historyLayout = findViewById(R.id.historyCard);
        healthIndexLayout = findViewById(R.id.healthIndexCard);
        appointmentLayout = findViewById(R.id.appointmentCard);

        loginResponse = (LoginResponse) getIntent().getSerializableExtra("loginAccount");

        textHello.setText("Xin chào " + loginResponse.getData().getUser().getFullName());


        editProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditProfile();
            }
        });

        appointmentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAppointment();
            }
        });

        healthIndexLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHealthIndex();
            }
        });

        historyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistory();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openEditProfile(){
        Intent intent = new Intent(HomeActivity.this, EditProfileActivity.class);
        intent.putExtra("loginAccount", loginResponse);
        startActivity(intent);
    }

    public void openAppointment(){
        Intent intent = new Intent(this, AppointmentActivity.class);
        intent.putExtra("loginAccount", loginResponse);
        startActivity(intent);
    }

    public void openHealthIndex(){
        Intent intent = new Intent(this, HealthIndexActivity.class);
        startActivity(intent);
    }

    public void openHistory(){
        Intent intent = new Intent(this, HitsoryActivity.class);
        intent.putExtra("loginAccount", loginResponse);
        startActivity(intent);
    }
}