package com.example.moviejava.API;

public class Common {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    public static APIService getAPIService(){
        return RetrofitClient.getRetrofitClient(BASE_URL).create(APIService.class);
    }
}
