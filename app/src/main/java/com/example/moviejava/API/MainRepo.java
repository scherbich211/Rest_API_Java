package com.example.moviejava.API;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepo {

    private  static final String TAG = "MainRepo";
    public static int g_selected_post = 1;

    private APIService apiService;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainRepo() {
        this.apiService = Common.getAPIService();
    }

    public MutableLiveData<List<PostModel>> getPostModelLiveData(){
        MutableLiveData<List<PostModel>> data = new MutableLiveData<>();
        compositeDisposable.add(apiService.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Consumer<List<PostModel>>() {
                      @Override
                      public void accept(List<PostModel> postModels) throws Exception {
                            if(postModels != null){
                                data.setValue(postModels);
                            }
                      }
                  }));
        return data;
    }

    public MutableLiveData<List<PostModel>> getPostModelNumberLiveData(){
        MutableLiveData<List<PostModel>> data = new MutableLiveData<>();
        apiService.getUserById(MainRepo.g_selected_post).enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                Log.e(TAG, "onResponse: " + response.code());
                if(response.isSuccessful()){
                    data.setValue(response.body());
                }else{
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Log.e(TAG, "onError: " + t.getMessage());
                data.setValue(null);
            }
        });

        return data;
    }
}
