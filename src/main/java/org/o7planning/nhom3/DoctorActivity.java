package org.o7planning.nhom3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONObject;
import org.o7planning.nhom3.api.RetrofitClient;
import org.o7planning.nhom3.controller.DoctorAdapter;
import org.o7planning.nhom3.model.Doctors;
import org.o7planning.nhom3.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorActivity extends AppCompatActivity {
    private RecyclerView rcvDoctors;
    private DoctorAdapter doctorAdapter;
    private SearchView searchDoctor;
    private ImageView backView;
    private LoginResponse loginResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor);

        searchDoctor = findViewById(R.id.searchDoctor);
        backView = (ImageView) findViewById(R.id.btnBack);

        loginResponse = (LoginResponse) getIntent().getSerializableExtra("loginAccount");

        backView.setClickable(true);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToAppointmentPage();
            }
        });

        searchDoctor.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                doctorAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                doctorAdapter.getFilter().filter(newText);
                return false;
            }
        });

        getListDoctors();

        rcvDoctors = findViewById(R.id.rcv_doctors);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        rcvDoctors.setLayoutManager(gridLayoutManager);
    }

    public void backToAppointmentPage(){
        Intent intent = new Intent(this, AppointmentActivity.class);
        intent.putExtra("loginAccount", loginResponse);
        startActivity(intent);
    }

    private void getListDoctors(){
        Call<Doctors> call = RetrofitClient.getInstance().getApi().getDoctors("doctor");
        call.enqueue(new Callback<Doctors>() {
            @Override
            public void onResponse(Call<Doctors> call, Response<Doctors> response) {
                if(response.isSuccessful()){
                    Doctors doctors = response.body();
                    if(doctors.getMessage().equals("Success") && doctors.getData() != null){
                        doctorAdapter = new DoctorAdapter( DoctorActivity.this, doctors.getData().getRecords(), loginResponse);
                        rcvDoctors.setAdapter(doctorAdapter);
                    }
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        Toast.makeText(DoctorActivity.this, message, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(DoctorActivity.this, e.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Doctors> call, Throwable t) {
                Toast.makeText(DoctorActivity.this, "Call Api Error" , Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
}