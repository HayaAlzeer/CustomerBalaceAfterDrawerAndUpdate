package com.example.test1;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface RetofitLogin {

    // as we are making a post request to post a data
    // so we are annotating it with post
    // and along with that we are passing a parameter as users
    @POST("login")

    //on below line we are creating a method to post our data.
    Call<String> createPost(@Body LoginModel loginModel);

}
