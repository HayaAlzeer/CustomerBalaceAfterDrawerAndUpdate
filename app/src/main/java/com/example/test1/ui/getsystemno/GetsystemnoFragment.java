package com.example.test1.ui.getsystemno;

import android.app.ProgressDialog;
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
import com.android.volley.toolbox.Volley;
import com.example.test1.CourseAdapter;
import com.example.test1.CourseModal;
import com.example.test1.R;
import com.example.test1.databinding.FragmentGetsystemnoBinding;
import com.example.test1.databinding.FragmentSlideshowBinding;
import com.example.test1.ui.home.HomeFragment;
import com.example.test1.ui.servlist.ServlistFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetsystemnoFragment extends Fragment {

    ProgressDialog progress;

    private RecyclerView courseRV;
    HashMap<String, String> queryValues;

    public ArrayList<HashMap<String, String>> collection ;

    private TextView name;
    private TextView mob;

    private TextView ParNum;

    // variable for our adapter
    // class and array list
    private CourseAdapter adapter;
    private ArrayList<CourseModal> courseModalArrayList;
    String systemNo ;
    // below line is the variable for url from
    // where we will be querying our data.
    String url ;
    private TextView idCard ;

    private ProgressBar progressBar;

    String username, password, token;


    Button back;

    private FragmentGetsystemnoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GetsystemnoViewModel getsystemnoViewModel =
                new ViewModelProvider(this).get(GetsystemnoViewModel.class);

        binding = FragmentGetsystemnoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        systemNo = String.valueOf(getArguments().getString("albumart"));;

        // initializing our variables.
        courseRV = binding.idRVCourses;
        progressBar = binding.idPB;
        back =binding.back;

        name = binding.test1;
        mob = binding.test2;
        ParNum =  binding.test3;
        idCard = binding.testid;


        // below line we are creating a new array list
        courseModalArrayList = new ArrayList<>();

        getData(systemNo);

        SharedPreferences preferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        username = preferences.getString("username", "0");
        password = preferences.getString("password", "haya");
        token =  preferences.getString("token","abc");


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

            }
        });

        final TextView textView = binding.textSlideshow;
        getsystemnoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void callParentMethod(){
        getActivity().onBackPressed();
    }

    private void getData(String systemNo) {
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,   "http://gis.duracity.ps/Mobile/api/Customer/SystemNumber/"+ systemNo, null , new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {

                Log.d("ADebugTag", "Value: " +""+response.length()  );

                progressBar.setVisibility(View.GONE);
                courseRV.setVisibility(View.VISIBLE);
                for (int i = 0; i < response.length(); i++) {

                    //    Toast.makeText(getApplicationContext(), " haya length"+response.length()  ,Toast.LENGTH_SHORT).show();
                    //    Toast.makeText(getApplicationContext(), " haya "  ,Toast.LENGTH_SHORT).show();
                    Log.d("ADebugTag", "Value: " +""+response.length()  );

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


                        System.out.println(systeM_NO);
                        System.out.println(systeM_NO);
                        System.out.println(systeM_NO);
                        name.setText( arabiC_NAME);
                        mob.setText(mobile);
                        ParNum.setText(systeM_NO);
                        idCard.setText(idcard_no);
                        courseModalArrayList.add(new CourseModal( systeM_NO,  arabiC_NAME,  mobile,  balance,  servS_TYPE,  agreemenT_ID,  currencY_ID,  quarter_id,  block_id,  parcel_id,  id));
                        // courseModalArrayList.add(new CourseModal( systeM_NO,  arabiC_NAME,  mobile,  balance,  servS_TYPE,  agreemenT_ID,  currencY_ID,  quarter_id,  block_id,  parcel_id,  id,  idcard_no,  f_name_a,  s_name_a,  m_name_a,  l_name_a));

                        buildRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
        })

        ;
        queue.add(jsonArrayRequest);
    }

    private void buildRecyclerView() {

        // initializing our adapter class.
        adapter = new CourseAdapter(courseModalArrayList, getActivity());

        // adding layout manager
        // to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        courseRV.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        courseRV.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        courseRV.setAdapter(adapter);
    }
}
