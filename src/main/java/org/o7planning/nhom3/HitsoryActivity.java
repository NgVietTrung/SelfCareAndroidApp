package org.o7planning.nhom3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;
import org.o7planning.nhom3.api.RetrofitClient;
import org.o7planning.nhom3.controller.DoctorAdapter;
import org.o7planning.nhom3.controller.HistoryAdapter;
import org.o7planning.nhom3.model.Doctors;
import org.o7planning.nhom3.model.History;
import org.o7planning.nhom3.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HitsoryActivity extends AppCompatActivity {
    private RecyclerView rcvAssignments;
    private HistoryAdapter historyAdapter;
    private ImageView backView;
    private LoginResponse loginResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hitsory);

        loginResponse = (LoginResponse) getIntent().getSerializableExtra("loginAccount");

        backView = (ImageView) findViewById(R.id.btnBack);
        backView.setClickable(true);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHomePage();
            }
        });

        rcvAssignments = findViewById(R.id.rcv_assignments);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvAssignments.setLayoutManager(linearLayoutManager);

        getListAssignments();

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvAssignments.addItemDecoration(itemDecoration);
    }

    public void backToHomePage(){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("loginAccount", loginResponse);
        startActivity(intent);
    }

    private void getListAssignments(){
        Call<History> call = RetrofitClient.getInstance().getApi().getHistoryAssignments("Bearer " + loginResponse.getData().getAccessToken());
        call.enqueue(new Callback<History>() {
            @Override
            public void onResponse(Call<History> call, Response<History> response) {
                if(response.isSuccessful()){
                    History history = response.body();
                    if(history.getMessage().equals("Success") && history.getData() != null){
                        historyAdapter = new HistoryAdapter( history.getData().getUser().getPatientAssignments());
                        rcvAssignments.setAdapter(historyAdapter);
                    }
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        Toast.makeText(HitsoryActivity.this, message, Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(HitsoryActivity.this, e.getMessage() , Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<History> call, Throwable t) {
                Toast.makeText(HitsoryActivity.this, "Call Api Error" , Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
}