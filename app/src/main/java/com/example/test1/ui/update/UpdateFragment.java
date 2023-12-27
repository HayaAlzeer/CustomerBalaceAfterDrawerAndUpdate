package com.example.test1.ui.update;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.test1.R;
import com.example.test1.databinding.FragmentSlideshowBinding;
import com.example.test1.databinding.FragmentUpdateBinding;
import com.example.test1.ui.result.ResultFragment;
import com.example.test1.ui.resultid.ResultidFragment;
import com.example.test1.ui.updatecus.UpdatecusFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateFragment extends Fragment {


    EditText EtText;
    Button Search;

    String username, password, token;


    private FragmentUpdateBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UpdateViewModel updateViewModel =
                new ViewModelProvider(this).get(UpdateViewModel.class);

        binding = FragmentUpdateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        if (container != null) {
            container.removeAllViews();
        }

        SharedPreferences preferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        username = preferences.getString("username", "0");
        password = preferences.getString("password", "haya");
        token =  preferences.getString("token","abc");


        EtText = binding.editText;

       String systemNo = EtText.getText().toString();

        Search = binding.search;

        Search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               // Toast.makeText(getActivity(), EtText.getText().toString() , Toast.LENGTH_SHORT).show();

                getData("http://gis.duracity.ps/Mobile/api/Customer/SystemNumber/Name/"+ EtText.getText().toString());

                /*
                  Bundle bundle = new Bundle();

                bundle.putString("URL", String.valueOf( "http://10.0.0.17/MobileTest/api/Customer/SystemNumber/Name/"+ EtText.getText().toString() ));
                bundle.putString("System_No", String.valueOf( EtText.getText().toString() ));





              UpdatecusFragment GF = new UpdatecusFragment();
                GF.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, GF, GF.getTag())
                        .commit();

                 */


            }
        });








        final TextView textView = binding.textSlideshow;
        updateViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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

    private void getData(String url) {
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.




        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,   "http://gis.duracity.ps/Mobile/api/Customer/SystemNumber/Name/"+ EtText.getText().toString(), null , new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                Log.d("ADebugTag", "Value: " +""+response.toString()  );



                try {


                    JSONObject responseObj = new JSONObject(String.valueOf(response));



                    String arabic_name = responseObj.getString("arabic_name");
                    String mobile = responseObj.getString("mobile");
                    String email = responseObj.getString("email");
                    String building_id = responseObj.getString("building_id");
                    String zone_id = responseObj.getString("zone_id");
                    String flat_id = responseObj.getString("flat_id");
                    String street_id = responseObj.getString("street_id");
                    String quarter_id = responseObj.getString("quarter_id");
                    String block_id = responseObj.getString("block_id");

                    String parcel_id = responseObj.getString("parcel_id");

                    String idcard_type = responseObj.getString("idcard_type");

                    String idcard_no = responseObj.getString("idcard_no");
                    String f_name_a = responseObj.getString("f_name_a");
                    String s_name_a = responseObj.getString("s_name_a");
                    String m_name_a = responseObj.getString("m_name_a");
                    String l_name_a = responseObj.getString("l_name_a");





                    Bundle bundle = new Bundle();

                    bundle.putString("URL", String.valueOf( "http://gis.duracity.ps/Mobile/api/Customer/SystemNumber/Name/"+ EtText.getText().toString() ));
                    bundle.putString("System_No", String.valueOf( EtText.getText().toString() ));
                    bundle.putString("arabic_name", String.valueOf( arabic_name ));
                    bundle.putString("mobile", String.valueOf( mobile ));
                    bundle.putString("email", String.valueOf( email ));
                    bundle.putString("building_id", String.valueOf( building_id ));
                    bundle.putString("zone_id", String.valueOf( zone_id ));

                    bundle.putString("flat_id", String.valueOf( flat_id ));
                    bundle.putString("street_id", String.valueOf( street_id ));
                    bundle.putString("street_id", String.valueOf( street_id ));
                    bundle.putString("quarter_id", String.valueOf( quarter_id ));
                    bundle.putString("block_id", String.valueOf( block_id ));
                    bundle.putString("parcel_id", String.valueOf( parcel_id ));


                    bundle.putString("idcard_type", String.valueOf( idcard_type ));
                    bundle.putString("idcard_no", String.valueOf( idcard_no ));

                    bundle.putString("f_name_a", String.valueOf( f_name_a ));
                    bundle.putString("s_name_a", String.valueOf( s_name_a ));
                    bundle.putString("m_name_a", String.valueOf( m_name_a ));
                    bundle.putString("l_name_a", String.valueOf( l_name_a));












                    UpdatecusFragment GF = new UpdatecusFragment();
                    GF.setArguments(bundle);
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction()
                            .replace(R.id.nav_host_fragment_content_main, GF, GF.getTag())
                            .commit();

                } catch (JSONException e) {
                    e.printStackTrace();
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
        //queue.add(jsonArrayRequest);

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

}