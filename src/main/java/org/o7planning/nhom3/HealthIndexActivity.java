package org.o7planning.nhom3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HealthIndexActivity extends AppCompatActivity {
    private ImageView backView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_index);

        backView = (ImageView) findViewById(R.id.btnBack);
        backView.setClickable(true);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHomePage();
            }
        });
    }

    public void backToHomePage(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}