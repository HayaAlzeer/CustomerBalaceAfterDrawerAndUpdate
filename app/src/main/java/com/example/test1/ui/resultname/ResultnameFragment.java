package com.example.test1.ui.resultname;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.DataModal;
import com.example.test1.NameAdapter;
import com.example.test1.R;
import com.example.test1.RecyclerViewAdapter;
import com.example.test1.RetrofitAPI;
import com.example.test1.databinding.FragmentResultnameBinding;
import com.example.test1.databinding.FragmentSlideshowBinding;
import com.example.test1.ui.getsystemno.GetsystemnoFragment;
import com.example.test1.ui.home.HomeFragment;
import com.example.test1.ui.result.ResultFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultnameFragment extends Fragment  implements NameAdapter.OnItemClicked{

    private TextView responseTV;

    private TextView numm , idtvresponse;
    private ProgressBar loadingPB;

    public LinearLayoutManager layoutManager;
    public LinearLayoutManager manager;

    private RecyclerViewAdapter adapter2;
    private RecyclerView recyclerView;
    private RecyclerView courseRV;

    List<DataModal> userList = null;

    String fname ;
    String sname;
    String thname ;
    private ResultnameFragment mContext;
    private ArrayList<DataModal> DataModelArrayList;

    String foname;

    Button back;
    String username, password, token;
    private FragmentResultnameBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ResultnameViewModel resultnameViewModel  =
                new ViewModelProvider(this).get(ResultnameViewModel.class);

        binding = FragmentResultnameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        mContext = this;

        loadingPB = binding.idLoadingPB;

        courseRV = binding.recycler;
        responseTV = binding.idTVResponse;

        numm = binding.numm;
        idtvresponse = binding.idTVResponse;

        recyclerView = binding.recycler;

        back = binding.back;




        fname = String.valueOf(getArguments().getString("fname"));
        sname = String.valueOf(getArguments().getString("sname"));
        thname = String.valueOf(getArguments().getString("thname"));
        foname = String.valueOf(getArguments().getString("foname"));

        DataModelArrayList = new ArrayList<>();


        SharedPreferences preferences = this.getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        username = preferences.getString("username", "0");
        password = preferences.getString("password", "haya");
        token =  preferences.getString("token","abc");

      //  Toast.makeText(getActivity(), token+"" , Toast.LENGTH_SHORT).show();

        postData(fname, sname, thname, foname);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                HomeFragment GF = new HomeFragment();
                GF.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, GF, GF.getTag())
                        .commit();

             //  Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();

            }
        });


        final TextView textView = binding.textSlideshow;
        resultnameViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void postData(String fname, String sname, String thname, String foname) {

        // below line is for displaying our progress bar.
        loadingPB.setVisibility(View.VISIBLE);



        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();









        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)

                .baseUrl("http://gis.duracity.ps/Mobile/api/Customer/")
                // .baseUrl("http://10.0.0.17:8563/Mobile/api/Customer/")

                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // passing data from our text fields to our modal class.
        DataModal modal = new DataModal(fname, sname, foname, thname);

        // calling a method to create a post and passing our modal class.
        Call<List<DataModal>> call = retrofitAPI.createPost(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<List<DataModal>>() {



            @Override
            public void onResponse(Call<List<DataModal>> call, Response<List<DataModal>> response) {
                // this method is called when we get response from our api.
                //    Toast.makeText(ResultName.this, "Data added to API", Toast.LENGTH_SHORT).show();



                // below line is for hiding our progress bar.
                loadingPB.setVisibility(View.GONE);

                // on below line we are setting empty text
                // to our both edit text.
                //  jobEdt.setText("");
                //  nameEdt.setText("");

                // we are getting response from our body
                // and passing it to our modal class.
                userList  = response.body();

                userList.size();

                if (userList.size() == 0 ){

                    numm.setVisibility(View.GONE);
                    idtvresponse.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), " لا يوجد بيانات ... ", Toast.LENGTH_SHORT).show();



                }else {

                    DataModelArrayList.clear();

                    for (int o = 0 ; o < userList.size() ;  o++){

                        //   Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(o).getSysteM_NO()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();

                 /*   Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(o).getF_name_a()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(o).getS_name_a()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();

                    Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(o).getM_name_a()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(o).getL_name_a()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();
*/


                        DataModelArrayList.add(new DataModal(userList.get(o).getF_name_a().toString(), userList.get(o).getS_name_a().toString(), userList.get(o).getM_name_a().toString(), userList.get(o).getL_name_a().toString(), userList.get(o).getSysteM_NO().toString()));

                        int listSize = DataModelArrayList.size();

                        for (int i = 0; i<listSize; i++){
                            Log.i("f name: ", String.valueOf(userList.get(i).getF_name_a().toString()));
                            Log.i("s name: ", String.valueOf(userList.get(i).getM_name_a().toString()));
                            Log.i("m name: ", String.valueOf(userList.get(i).getS_name_a().toString()));
                            Log.i("l name: ", String.valueOf(userList.get(i).getL_name_a().toString()));

                        }

                        Log.i("autolog", "RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);");



                        // buildRecyclerView();
                    }

                    layoutManager = new LinearLayoutManager(getActivity());
                    Log.i("autolog", "layoutManager = new LinearLayoutManager(MainActivity.this);");
                    recyclerView.setLayoutManager(layoutManager);
                    Log.i("autolog", "recyclerView.setLayoutManager(layoutManager);");

                    NameAdapter recyclerViewAdapter =new NameAdapter(DataModelArrayList, getActivity());
                    Log.i("autolog", "RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(getApplicationContext(), userList);");
                    recyclerView.setAdapter(recyclerViewAdapter);
                    Log.i("autolog", "recyclerView.setAdapter(recyclerViewAdapter);");

                    recyclerViewAdapter.setOnClick(mContext);


                }

                // on below line we are getting our data from modal class and adding it to our string.
                //  String responseString = "Response Code : " + response.code() + "\nName : " + userList.get(0).getF_name_a() + "\n";

            /*
                Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(0).getF_name_a()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();
                Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(0).getS_name_a()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();

                Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(0).getM_name_a()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();
                Toast.makeText(ResultName.this, "Response Code : " + response.code() + "\nName : " + userList.get(0).getL_name_a()  + userList.size()+ "\n", Toast.LENGTH_SHORT).show();

           */




                //  buildRecyclerView();

                // below line we are setting our
                // string to our text view.
                //  responseTV.setText("result");
            }

            @Override
            public void onFailure(Call<List<DataModal>>  call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(int position) {

        DataModal aim = DataModelArrayList.get(position);

        String trackName = aim.getF_name_a();
        String collectionName = aim.getS_name_a();
        String artistName = aim.getL_name_a();
        String genreName = aim.getM_name_a();
        String albumArt = aim.getSysteM_NO();

        Bundle bundle = new Bundle();
        bundle.putString("track", String.valueOf(trackName));
        bundle.putString("collection", String.valueOf(collectionName));
        bundle.putString("artist", String.valueOf(artistName));
        bundle.putString("genre", String.valueOf(genreName));
        bundle.putString("albumart", String.valueOf(albumArt));


        GetsystemnoFragment GF = new GetsystemnoFragment();
        GF.setArguments(bundle);
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, GF, GF.getTag())
                .commit();




    }

    public void callParentMethod(){
        getActivity().onBackPressed();
    }


}