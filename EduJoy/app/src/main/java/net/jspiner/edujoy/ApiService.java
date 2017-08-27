package net.jspiner.edujoy;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    @Multipart
    @POST("/api/foodRecognition")
    Call<String> sendFoodImage(
            @Part MultipartBody.Part media
    );

}
