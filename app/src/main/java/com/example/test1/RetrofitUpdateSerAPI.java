package com.example.test1;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitUpdateSerAPI {

    @PUT("Customer/UpdateService/{Servs_No}")

        // on below line we are creating a method to put our data.
    Call<SerLocation> updateData(@Path("Servs_No") String api_key, @Query("IUSER_ID") String userid, @Query("serviceType") String srvstype, @Query("systemNo") String systmno, @Body SerLocation serLocation );




}
