package com.example.chinmoydash.ddugky;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void  launchEmployerActivity(View view){

        Intent intent = new Intent (MainActivity.this,EmployerActivity.class);
        startActivity(intent);

    }

    public void  launchCandidateActivity(View view){

        Intent intent = new Intent (MainActivity.this,CandidateActivity.class);
        startActivity(intent);

    }


}
