package com.example.test1;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitUpdateAPI {

    @PUT("Customer/UpdateCustomer/{cust_no}")

        // on below line we are creating a method to put our data.
    Call<UserModal> updateData(@Path("cust_no") String api_key, @Query("IUSER_ID") String userid,@Body UserModal courseModal );




}
