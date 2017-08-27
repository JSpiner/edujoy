package net.jspiner.edujoy;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Network {

    private static ApiService apiService = null;

    public static ApiService getService(){
        if(apiService == null){
            apiService = generateNewService();
        }
        return apiService;
    }

    private static ApiService generateNewService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.17.83.251/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }

}
