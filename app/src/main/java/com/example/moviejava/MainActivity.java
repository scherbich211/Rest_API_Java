package com.example.moviejava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;

import com.example.moviejava.MainAdapter.PostListAdapter;
import com.example.moviejava.API.MainActivityViewModel;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel mainActivityViewModel;

    RecyclerView recyclerPostList;
    PostListAdapter adapter;
    LinearLayoutManager layoutManager;
    AlertDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        dialog = new SpotsDialog.Builder().setContext(this).setCancelable(false).build();
        dialog.show();

        recyclerPostList = findViewById(R.id.recyclerPostList);
        layoutManager = new LinearLayoutManager(this);
        recyclerPostList.setLayoutManager(layoutManager);

        mainActivityViewModel.getPostList().observe(this, postModels -> {

            adapter = new PostListAdapter(this, postModels);
            adapter.notifyDataSetChanged();
            recyclerPostList.setAdapter(adapter);
            dialog.dismiss();
        });
    }
}