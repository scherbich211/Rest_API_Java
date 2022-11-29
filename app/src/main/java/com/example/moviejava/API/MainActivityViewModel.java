package com.example.moviejava.API;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviejava.API.MainRepo;
import com.example.moviejava.API.PostModel;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final MainRepo mainRepo;

    public MainActivityViewModel() {
        mainRepo = new MainRepo();
    }

    public LiveData<List<PostModel>> getPostList(){
        return mainRepo.getPostModelLiveData();
    }

    public LiveData<List<PostModel>> getUserData(){
        return mainRepo.getPostModelNumberLiveData();
    }
}
