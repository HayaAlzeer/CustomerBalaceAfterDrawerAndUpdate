package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Login extends AppCompatActivity {

    private TextView responseTV;
    String loginResponse;
    SharedPreferences.Editor editor;
    EditText passwordd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        responseTV = findViewById(R.id.idTVResponse);

        editor = getSharedPreferences("UserData", MODE_PRIVATE).edit();


        Button button = (Button) findViewById(R.id.login);
        EditText username = (EditText) findViewById(R.id.editTextNumber);
        passwordd = (EditText) findViewById(R.id.editTextTextPersonName);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click


                // Toast.makeText(getApplicationContext(),userId.getText().toString().length()+ "     " + userName.getText().toString().length() , Toast.LENGTH_SHORT).show();


                if (username.getText().toString().length() == 0 && passwordd.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), " ادخل اسم المستخدم وكلمة المرور", Toast.LENGTH_SHORT).show();


                } else if (username.getText().toString().length() == 0 && passwordd.getText().toString().length() != 0) {
                    Toast.makeText(getApplicationContext(), " ادخل اسم المستخدم", Toast.LENGTH_SHORT).show();


                } else if (username.getText().toString().length() != 0 && passwordd.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), " ادخل كلمة المرور", Toast.LENGTH_SHORT).show();


                } else {


                    //  Toast.makeText(getApplicationContext(),userId.getText().toString() + "   "+ userName.getText().toString(), Toast.LENGTH_SHORT).show();






                /*
                    Intent go = new Intent(Login.this, Filter.class);
                    Bundle bundle = new Bundle();

                    bundle.putInt("type", 1);
                    go.putExtras(bundle);
                    startActivity(go);

                    */

                    //Toast.makeText(Login.this, username.getText().toString() + "    " + passwordd.getText().toString() , Toast.LENGTH_SHORT).show();

                    postData(username.getText().toString(), passwordd.getText().toString());


                }


            }
        });
    }

    private void postData(String username, String password) {


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gis.duracity.ps/Mobile/api/Auth/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())

                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetofitLogin retrofitlogin = retrofit.create(RetofitLogin.class);

        // passing data from our text fields to our modal class.
        LoginModel modal = new LoginModel(username, password);

        // calling a method to create a post and passing our modal class.
        Call<String> call = retrofitlogin.createPost(modal);

        // on below line we are executing our method.


        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {


                if (response.isSuccessful()) {

                    if (response.body().isEmpty()) {

                        Toast.makeText(Login.this, "فشل تسجيل الدخول", Toast.LENGTH_LONG).show();

                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();


                    } else {


                        Log.i("onSuccess", response.body().toString());

                        Toast.makeText(Login.this,
                                "تم تسجيل الدخول بنجاح", Toast.LENGTH_LONG).show();
                        loginResponse = response.body();
                        responseTV.setText(loginResponse);

                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.putString("token", loginResponse);
                        editor.apply();


                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                startActivity(new Intent(Login.this, MainActivity.class));
                            }
                        }, 500);
                    }


                } else {
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(Login.this, "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                responseTV.setText("Throwable " + t.getLocalizedMessage());
            }
        });
    }

}



