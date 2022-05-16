package org.o7planning.nhom3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import org.json.JSONObject;
import org.o7planning.nhom3.api.RetrofitClient;
import org.o7planning.nhom3.model.Assignment;
import org.o7planning.nhom3.model.Doctors;
import org.o7planning.nhom3.model.LoginResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleActivity extends AppCompatActivity {
    private ImageView backView;
    private Button btnSubmit;
    private TextInputEditText textDescription;
    private LoginResponse loginResponse;
    private Doctors.DoctorData.DoctorRecords doctor;

    String date = "", time = "";

    String [] dateItems = new String[7];
    String [] timeItems = {"08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00",
            "13:00", "13:30" , "14:00", "14:30" ,"15:00", "15:30", "16:00", "16:30", "17:00", "17:30" ,"18:00"};
    String [] tempTimeItems = {};
    AutoCompleteTextView autoCompleteDate, autoCompleteTime;
    ArrayAdapter<String> adapterDateItems, adapterTimeItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        autoCompleteDate = findViewById(R.id.dropdown_date_txt);
        autoCompleteTime = findViewById(R.id.dropdown_time_txt);
        textDescription = findViewById(R.id.textDescription);
        backView = (ImageView) findViewById(R.id.btnBack);
        btnSubmit = findViewById(R.id.btnSubmit);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        doctor = (Doctors.DoctorData.DoctorRecords) bundle.get("object_doctor");
        loginResponse = (LoginResponse) getIntent().getSerializableExtra("loginAccount");

        //Get 7 days
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        for (int i = 0; i < 7; i++) {
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, i + 1);
            String day = sdf.format(calendar.getTime());
            dateItems[i] = day;
        }

        adapterDateItems = new ArrayAdapter<String>(this, R.layout.schedule_item, dateItems);
        autoCompleteDate.setAdapter(adapterDateItems);
        autoCompleteDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), autoCompleteDate.getText(), Toast.LENGTH_SHORT).show();
                String item = parent.getItemAtPosition(position).toString();
                date = item;
            }
        });

        adapterTimeItems = new ArrayAdapter<String>(this, R.layout.schedule_item, timeItems);
        autoCompleteTime.setAdapter(adapterTimeItems);
        autoCompleteTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                time = item;
                Toast.makeText(getApplicationContext(), autoCompleteTime.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToDoctorDetail();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitHomePage();
            }
        });
    }

    public void backToDoctorDetail(){
        Intent intent = new Intent(this, DoctorDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_doctor", doctor);
        intent.putExtra("loginAccount", loginResponse);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void submitHomePage(){
        String description = textDescription.getText().toString().trim();

        if(date.equals("") || time.equals("") || description.equals("") ){
            Toast.makeText(this, "Vui lòng điền đủ thông tin" , Toast.LENGTH_SHORT).show();
            return;
        }

        JsonObject assignment = new JsonObject();
        assignment.addProperty("patient", loginResponse.getData().getUser().get_id());
        assignment.addProperty("doctor", doctor.get_id());
        assignment.addProperty("notes", description);
        JsonObject assignmentTime = new JsonObject();
        assignmentTime.addProperty("date", date);
        assignmentTime.addProperty("time", time);
        assignment.add("assignmentTime", assignmentTime);

        Call<Assignment> call = RetrofitClient.getInstance().getApi().createAssignment("Bearer " + loginResponse.getData().getAccessToken(), assignment);

        call.enqueue(new Callback<Assignment>() {
            @Override
            public void onResponse(Call<Assignment> call, Response<Assignment> response) {
                if(response.isSuccessful()){
                    Assignment responseAssignment = response.body();
                    if(responseAssignment.getMessage().equals("Success") && responseAssignment.getData() != null){
                        Toast.makeText(ScheduleActivity.this, "Đặt lịch thành công" , Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ScheduleActivity.this, HomeActivity.class);
                        intent.putExtra("loginAccount", loginResponse);
                        startActivity(intent);
                    }
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        Toast.makeText(ScheduleActivity.this, message, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(ScheduleActivity.this, e.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Assignment> call, Throwable t) {
                Toast.makeText(ScheduleActivity.this, "Call Api Error" , Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
}