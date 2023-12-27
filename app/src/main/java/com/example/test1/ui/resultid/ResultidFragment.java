package com.example.test1.ui.resultid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.test1.CourseAdapter;
import com.example.test1.CourseModal;
import com.example.test1.R;
import com.example.test1.databinding.FragmentResultidBinding;
import com.example.test1.databinding.FragmentSlideshowBinding;
import com.example.test1.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultidFragment extends Fragment {

    private RecyclerView courseRV;

    private TableLayout table1;
    private TableLayout table2;

    private TextView name;
    private TextView mob;

    private TextView ParNum , testid;

    // variable for our adapter
    // class and array list
    private CourseAdapter adapter;
    private ArrayList<CourseModal> courseModalArrayList;

    // below line is the variable for url from
    // where we will be querying our data.
    String url ;

    private ProgressBar progressBar;

    String username, password, token;

    Button back;

    private FragmentResultidBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ResultidViewModel resultidViewModel =
                new ViewModelProvider(this).get(ResultidViewModel.class);

        binding = FragmentResultidBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences preferences = this.getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        username = preferences.getString("username", "0");
        password = preferences.getString("password", "haya");
        token =  preferences.getString("token","abc");


        table1 = binding.table1;
        table2 =binding.table2;



        url = String.valueOf(getArguments().getString("URL"));


        // Toast.makeText(getApplicationContext(), url  ,Toast.LENGTH_SHORT).show();
        // initializing our variables.
        courseRV = binding.idRVCourses;
        progressBar = binding.idPB;

        name = binding. test1;
        mob = binding.test2;
        ParNum =  binding.test3;
        testid = binding.testid;
        back = binding.back;


        // below line we are creating a new array list
        courseModalArrayList = new ArrayList<>();
        getData();

        // calling method to
        // build recycler view.
        buildRecyclerView();


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
        resultidViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getData() {
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                progressBar.setVisibility(View.GONE);
                courseRV.setVisibility(View.VISIBLE);

                if (response.length() == 0 ){

                    table1.setVisibility(View.GONE);
                    table2.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "راجع مركز خدمات الجمهور لتحديث بياناتك ... ", Toast.LENGTH_SHORT).show();

                }
                else{
                    for (int i = 0; i < response.length(); i++) {
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

                            String idcard_no = responseObj.getString("idcard_no");

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


                            name.setText(arabiC_NAME);
                            mob.setText(mobile);
                            ParNum.setText(systeM_NO);
                            testid.setText(idcard_no);


                            courseModalArrayList.add(new CourseModal(systeM_NO, arabiC_NAME, mobile, balance, servS_TYPE, agreemenT_ID, currencY_ID, quarter_id, block_id, parcel_id, id));
                            buildRecyclerView();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                String authValue = "Bearer " + token;
                headers.put("Authorization", authValue);
                headers.put("Accept", "application/json; charset=UTF-8");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        }
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

    public void callParentMethod(){
        getActivity().onBackPressed();
    }

}


