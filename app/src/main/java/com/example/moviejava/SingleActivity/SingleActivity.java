package com.example.moviejava.SingleActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviejava.R;
import com.example.moviejava.API.MainActivityViewModel;

import dmax.dialog.SpotsDialog;

public class SingleActivity extends AppCompatActivity {

    private ImageView back_btn;
    TextView textTitle, txtBody,txtUserId, txtId;


    MainActivityViewModel mainActivityViewModel;
    AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        back_btn = findViewById(R.id.back_btn);
        textTitle = findViewById(R.id.txtTitle);
        txtBody = findViewById(R.id.txtBody);
        txtUserId = findViewById(R.id.txtUserId);
        txtId = findViewById(R.id.txtId);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        dialog = new SpotsDialog.Builder().setContext(this).setCancelable(false).build();
        dialog.show();

        mainActivityViewModel.getUserData().observe(this, postModels -> {
            textTitle.setText("Title: " + String.valueOf(postModels.get(0).getTitle()));
            txtUserId.setText("User id: " +String.valueOf(postModels.get(0).getUserId()));
            txtBody.setText("Body: " +String.valueOf(postModels.get(0).getBody()));
            txtId.setText("Id: " +String.valueOf(postModels.get(0).getId()));
            dialog.dismiss();
        });



    }
}