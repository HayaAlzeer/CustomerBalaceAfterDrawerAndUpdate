package com.example.test1.ui.servlist;

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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.test1.CourseAdapter;
import com.example.test1.CourseModal;
import com.example.test1.DataModal;
import com.example.test1.NameAdapter;
import com.example.test1.R;
import com.example.test1.ServAdapter;
import com.example.test1.ServDataModal;
import com.example.test1.databinding.FragmentResultBinding;
import com.example.test1.databinding.FragmentServlistBinding;
import com.example.test1.databinding.FragmentSlideshowBinding;
import com.example.test1.ui.getsystemno.GetsystemnoFragment;
import com.example.test1.ui.home.HomeFragment;
import com.example.test1.ui.update.UpdateFragment;
import com.example.test1.ui.updatser.UpdatserFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServlistFragment extends Fragment  implements ServAdapter.OnItemClicked{

    private RecyclerView courseRV;


    public ArrayList<HashMap<String, String>> collection;


    // variable for our adapter
    // class and array list
    private CourseAdapter adapter;
    private ArrayList<ServDataModal> ServDataModelArrayList;
    private ArrayList<ServDataModal> ServDataModelArrayList1;

    private ArrayList<DataModal> DataModelArrayList;
    // below line is the variable for url from
    // where we will be querying our data.
    String url;

    public LinearLayoutManager layoutManager11;


    private RecyclerView recyclerView11;
Button back;


    private ProgressBar progressBar;
    ArrayList<String> MainListItem;
    ArrayList<String> duplicates;
    Set<String> set;

    private TextView numm , idtvresponse;


    private TextView responseTV;

    private ProgressBar loadingPB;


    private ServlistFragment mContext;
    String username, password, token;


    private FragmentServlistBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ServlistViewModel servlistViewModel =
                new ViewModelProvider(this).get(ServlistViewModel.class);

        binding = FragmentServlistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        SharedPreferences preferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        username = preferences.getString("username", "0");
        password = preferences.getString("password", "haya");
        token =  preferences.getString("token","abc");

       // Toast.makeText(getActivity(), token+"" , Toast.LENGTH_SHORT).show();

        mContext = this;

        loadingPB = binding.idLoadingPB;

        courseRV = binding.recycler;

        numm = binding.numm;
        back = binding.back;


        recyclerView11 = binding.recycler;

        DataModelArrayList = new ArrayList<>();


        MainListItem = new ArrayList<String>();
        set = new HashSet<>();
        duplicates = new ArrayList<String>();


        url = String.valueOf(getArguments().getString("URL"));

        //  Toast.makeText(getApplicationContext(), url  ,Toast.LENGTH_SHORT).show();

        Log.d("ADebugTag", "Value: " + url);

        // initializing our variables.
      /*  courseRV = findViewById(R.id.idRVCourses);
        progressBar = findViewById(R.id.idPB);

        name = findViewById(R.id.test1);
        mob = findViewById(R.id.test2);
        ParNum =  findViewById(R.id.test3);
        idCard = findViewById(R.id.testid);
*/
        // below line we are creating a new array list
        ServDataModelArrayList = new ArrayList<>();
        ServDataModelArrayList1 = new ArrayList<>();

        //  getDataNum();
        getDataServ(url);


        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
                //  dialog.dismiss();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                UpdateFragment GF = new UpdateFragment();
                GF.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, GF, GF.getTag())
                        .commit();

            }
        });

        final TextView textView = binding.textSlideshw;
        servlistViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void getDataServ(String url) {
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {

                Log.d("ADebugTag", "Value: " + "" + response.length());

                loadingPB.setVisibility(View.GONE);
                courseRV.setVisibility(View.VISIBLE);

                if (response.length() == 0 ){

                    numm.setVisibility(View.GONE);
                  //  idtvresponse.setVisibility(View.GONE);

                    Toast.makeText(getActivity(), " لا يوجد بيانات  ", Toast.LENGTH_SHORT).show();


                }
                else{

                    ServDataModelArrayList.clear();

                    for (int i = 0; i < response.length(); i++) {

                        //   Toast.makeText(getActivity().getApplicationContext(), " haya length"+response.length()  ,Toast.LENGTH_SHORT).show();
                        //    Toast.makeText(getApplicationContext(), " haya "  ,Toast.LENGTH_SHORT).show();
                        Log.d("ADebugTag", "length: " + "" + response.length());

                        // creating a new json object and
                        // getting each object from our json array.
                        try {
                            // we are getting each json object.
                            JSONObject responseObj = response.getJSONObject(i);

                            // now we get our response from API in json object format.
                            // in below line we are extracting a string with
                            // its key value from our json object.
                            // similarly we are extracting all the strings from our json object.
                            String courseName = responseObj.getString("arabiC_NAME");
                            String courseTracks = responseObj.getString("mobile");
                            String courseMode = responseObj.getString("balance");
                            String courseImageURL = responseObj.getString("systeM_NO");

                            String systeM_NO = responseObj.getString("systeM_NO");
                            String arabiC_NAME = responseObj.getString("arabiC_NAME");
                            String mobile = responseObj.getString("mobile");
                            String balance = responseObj.getString("balance");
                            String servS_TYPE = responseObj.getString("servS_TYPE");
                            String agreemenT_ID = responseObj.getString("agreemenT_ID");
                            String currencY_ID = responseObj.getString("currencY_ID");
                            String quarter_id = responseObj.getString("quarter_id");
                            String block_id = responseObj.getString("block_id");
                            String parcel_id = responseObj.getString("parcel_id");
                            String id = "";
                            String idcard_no = responseObj.getString("idcard_no");
                            String f_name_a = responseObj.getString("f_name_a");
                            String s_name_a = responseObj.getString("s_name_a");
                            String m_name_a = responseObj.getString("m_name_a");
                            String l_name_a = responseObj.getString("l_name_a");





                            ServDataModelArrayList.add(new ServDataModal(systeM_NO, servS_TYPE, agreemenT_ID, quarter_id, block_id, parcel_id));


                            // Toast.makeText(getActivity(), " system no  "+ systeM_NO  ,Toast.LENGTH_SHORT).show();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }


                    layoutManager11 = new LinearLayoutManager(getActivity());
                    Log.i("autolog", "layoutManager = new LinearLayoutManager(MainActivity.this);");
                    recyclerView11.setLayoutManager(layoutManager11);
                    Log.i("autolog", "recyclerView.setLayoutManager(layoutManager);");



                    ServAdapter recyclerViewAdapter = new ServAdapter(ServDataModelArrayList, getActivity());

                    // Toast.makeText(getApplicationContext(), " DataModelArrayList size "+ DataModelArrayList .size()   ,Toast.LENGTH_SHORT).show();

                    Log.i("autolog", "RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(getApplicationContext(), userList);");
                    recyclerView11.setAdapter(recyclerViewAdapter);
                    Log.i("autolog", "recyclerView.setAdapter(recyclerViewAdapter);");

                    recyclerViewAdapter.setOnClick(mContext);


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Fail to get the data..", Toast.LENGTH_SHORT).show();

            }
        })

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                String authValue = "Bearer " + token ;
                headers.put("Authorization", authValue);
                headers.put("Accept", "application/json; charset=UTF-8");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };
      //  queue.add(jsonArrayRequest);


        jsonArrayRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        queue.add(jsonArrayRequest);
    }

    @Override
    public void onItemClick(int position) {


        ServDataModal aim = ServDataModelArrayList.get(position);

        String agreemenT_id = aim.getAgreemenT_ID();
        String systeMNo = aim.getSysteM_NO();
        String servSType = aim.getServS_TYPE();
        String block_id = aim.getBlock_id();
        String parcel_id = aim.getParcel_id();
        String quarter = aim.getQuarter_id();



        Bundle bundle = new Bundle();
        bundle.putString("agreemenT_id", String.valueOf(agreemenT_id));
        bundle.putString("systeMNo", String.valueOf(systeMNo));
        bundle.putString("servSType", String.valueOf(servSType));
        bundle.putString("block_id", String.valueOf(block_id));
        bundle.putString("quarter", String.valueOf(quarter));
        bundle.putString("parcel_id", String.valueOf(parcel_id));



        UpdatserFragment GF = new UpdatserFragment();
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
