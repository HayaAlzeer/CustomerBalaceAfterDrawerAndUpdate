package com.example.test1;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitAPI {

    // as we are making a post request to post a data
    // so we are annotating it with post
    // and along with that we are passing a parameter as users
    @POST("CustomerByName")

    //on below line we are creating a method to post our data.
    Call<List<DataModal>> createPost(@Body DataModal dataModal);


}
