package com.example.test1.ui.updatser;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.test1.CourseModal;
import com.example.test1.Locaton;
import com.example.test1.LocatonPrsl;
import com.example.test1.NameAdapter;
import com.example.test1.R;
import com.example.test1.RetrofitUpdateAPI;
import com.example.test1.RetrofitUpdateSerAPI;
import com.example.test1.SerLocation;
import com.example.test1.UserModal;
import com.example.test1.databinding.FragmentSlideshowBinding;
import com.example.test1.databinding.FragmentUpdatserBinding;
import com.example.test1.ui.servlist.ServlistFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdatserFragment extends Fragment {


    EditText etBlock, etQuarter, etParcel;

    TextView SerId;


    String agreemenT_id, systeMNo, servSType, block_idii, quarter, parcel_id;

    String sertype = "";

    String StringSerType;

    private FragmentUpdatserBinding binding;
    Spinner spinb;
    Spinner spinq;
    Spinner spinp;

 Button button , back  ;
    ArrayList<Locaton> LocationB;
    ArrayList<Locaton> LocationQ;
    ArrayList<LocatonPrsl> LocationPrsl;

    ArrayList<String> CountryName;
    String username, password, token;

    private TextView responseTV;

    String quarter_iddd ;
    String block_iddd;
    String parcel_iddd ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UpdatserViewModel updatserViewModel =
                new ViewModelProvider(this).get(UpdatserViewModel.class);

        binding = FragmentUpdatserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();





        SharedPreferences preferences = this.getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        username = preferences.getString("username", "0");
        password = preferences.getString("password", "haya");
        token = preferences.getString("token", "abc");


        agreemenT_id = String.valueOf(getArguments().getString("agreemenT_id"));
        systeMNo = String.valueOf(getArguments().getString("systeMNo"));

        servSType = String.valueOf(getArguments().getString("servSType"));
        block_idii = String.valueOf(getArguments().getString("block_id"));
        quarter = String.valueOf(getArguments().getString("quarter"));
        parcel_id = String.valueOf(getArguments().getString("parcel_id"));


        // my code
        spinb = (Spinner) binding.blk;
        spinq = (Spinner) binding.qurtr;
        spinp = (Spinner) binding.prsl;

        responseTV = binding.idTVResponse;

        SerId = (TextView) binding.servistype;
        etParcel = (EditText) binding.parcel;
        etQuarter = (EditText) binding.quarter;
        etBlock = (EditText) binding.block;
        button = (Button)binding.button;

        back = (Button)binding.back;

        LocationB = new ArrayList<>();
        LocationQ = new ArrayList<>();
        LocationPrsl = new ArrayList<>();



        etBlock.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        etQuarter.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        etParcel.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);


        switch (servSType) {

            case "001":
                sertype = "مياه";
                break;

            case "005":
                sertype = "نفايات";
                break;
            case "002":
                sertype = "كهرباء";
                break;
            case "003":
                sertype = "اللافتات";
                break;
            case "004":
                sertype = "حرف";
                break;
            case "007":
                sertype = "معارف";
                break;
            case "008":
                sertype = "مخالفات";
                break;
            case "009":
                sertype = "ايجارات - دينار";
                break;
            case "012":
                sertype = "رسوم بناء";
                break;
            case "013":
                sertype = "رسوم مواقف";
                break;
            case "014":
                sertype = "ايجارات_دولار";
                break;
            case "015":
                sertype = "شيكل_معاملات";
                break;
            case "016":
                sertype = "دينار_معاملات";
                break;
            case "017":
                sertype = "مدرج رياضي";
                break;
            case "018":
                sertype = " دينموميتر";
                break;
            case "019":
                sertype = "حدائق";
                break;
            case "020":
                sertype = "املاك شيقل";
                break;
            case "021":
                sertype = "املاك_دينار";
                break;
            case "022":
                sertype = "معاملات يورو";
                break;
            case "023":
                sertype = "متنوعات مياه";
                break;
            case "024":
                sertype = "نفايات-كميات";
                break;

            case "104":
                sertype = "حرف";
                break;


            default:
                throw new IllegalArgumentException("Invalid day of the week: " + sertype);

        }


        etBlock.setText(block_idii);
        etQuarter.setText(quarter);
        etParcel.setText(parcel_id);

        SerId.setText(sertype);


        getblock();

        // getQuarter();

        // getParcel();




        spinb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Locaton country = (Locaton) parent.getSelectedItem();
                //  Toast.makeText(getApplicationContext(), "Country ID: "+country.getId()+",  Country Name : "+country.getName(), Toast.LENGTH_SHORT).show();
             //  int SerType = Integer.parseInt(country.getId());

                String blk = LocationB.get(position).getId();
                etBlock.setText(blk);

                block_idii = blk;
              //  LocationQ = new ArrayList<>();
                getQuarter();

             // Toast.makeText(getActivity(), blk + SerType, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Locaton country = (Locaton) parent.getSelectedItem();
                //  Toast.makeText(getApplicationContext(), "Country ID: "+country.getId()+",  Country Name : "+country.getName(), Toast.LENGTH_SHORT).show();
              //  int SerType = Integer.parseInt(country.getId());

                String qurtr = LocationQ.get(position).getId();
                quarter = qurtr;

                getParcel();

                etQuarter.setText(qurtr);
              //  Toast.makeText(getActivity(), qurtr + SerType, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        spinp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                LocatonPrsl country = (LocatonPrsl) parent.getSelectedItem();
                //  Toast.makeText(getApplicationContext(), "Country ID: "+country.getId()+",  Country Name : "+country.getName(), Toast.LENGTH_SHORT).show();
             //   int SerType = Integer.parseInt(country.getId());

                String prsl = LocationPrsl.get(position).getId();
                etParcel.setText(prsl);
             //   Toast.makeText(getActivity(), prsl + SerType, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callPUTDataMethod();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();

                bundle.putString("URL", String.valueOf( "http://gis.duracity.ps/Mobile/api/Customer/SystemNumber/"+ systeMNo));

                ServlistFragment GF = new ServlistFragment();
                GF.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, GF, GF.getTag())
                        .commit();

            }
        });




        final TextView textView = binding.textSlideshow;
        updatserViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;





    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getblock() {
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.


        CountryName = new ArrayList<>();

        // Create an array to populate the spinner
        ArrayList<String> worldlist = new ArrayList<String>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "http://gis.duracity.ps/Mobile/api/Geo/block/502840" , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                if (response.length() == 0) {


                 //   Toast.makeText(getActivity(), "راجع مركز خدمات الجمهور لتحديث بياناتك ... ", Toast.LENGTH_SHORT).show();

                } else {

                    LocationB.add(new Locaton("0", "اختر الحوض"));

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


                            String id = responseObj.getString("id");
                            String name = responseObj.getString("name");

                            CountryName.add(name);

                            worldlist.add(responseObj.optString("name"));

                            LocationB.add(new Locaton(id, name));


                         //   Toast.makeText(getActivity(), name + id + "haya ", Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    buildRecyclerViewB();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                String authValue = "Bearer " + token;
                headers.put("Authorization", authValue);
                headers.put("Accept", "application/json; charset=UTF-8");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };
        queue.add(jsonArrayRequest);
    }


    private void getQuarter() {

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.



        CountryName = new ArrayList<>();

        // Create an array to populate the spinner
        ArrayList<String> worldlist = new ArrayList<String>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "http://gis.duracity.ps/Mobile/api/Geo/Quarter/"+block_idii, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                if (response.length() == 0) {


               //     Toast.makeText(getActivity(), "راجع مركز خدمات الجمهور لتحديث بياناتك ... ", Toast.LENGTH_SHORT).show();

                } else {

                    LocationQ.clear();
                    LocationQ.add(new Locaton("-1", "اختر الحي "));

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


                            String id = responseObj.getString("id");
                            String name = responseObj.getString("name");

                            CountryName.add(name);

                            worldlist.add(responseObj.optString("name"));

                            LocationQ.add(new Locaton(id, name));


                        //    Toast.makeText(getActivity(), name + id + "haya ", Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                   buildRecyclerViewQ();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                String authValue = "Bearer " + token;
                headers.put("Authorization", authValue);
                headers.put("Accept", "application/json; charset=UTF-8");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };
        queue.add(jsonArrayRequest);
    }


    private void getParcel() {
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.


        CountryName = new ArrayList<>();
        ArrayList<String> countryName = new ArrayList<String>();


        // Locate the WorldPopulation Class
        ArrayList<Locaton> world = new ArrayList<Locaton>();

        // Create an array to populate the spinner
        ArrayList<String> worldlist = new ArrayList<String>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "http://gis.duracity.ps/Mobile/api/Geo/Parcel/"+block_idii+","+quarter , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                if (response.length() == 0) {


                 //   Toast.makeText(getActivity(), "راجع مركز خدمات الجمهور لتحديث بياناتك ... ", Toast.LENGTH_SHORT).show();

                } else {

                    LocationPrsl.clear();
                    LocationPrsl.add(new LocatonPrsl("-1", "اختر القطعة  "));

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


                            String id = responseObj.getString("id");
                            String name = responseObj.getString("name");

                            CountryName.add(name);

                            worldlist.add(responseObj.optString("name"));

                            LocationPrsl.add(new LocatonPrsl(id, name));


                         //   Toast.makeText(getActivity(), name + id + "haya ", Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                   buildRecyclerViewP();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                String authValue = "Bearer " + token;
                headers.put("Authorization", authValue);
                headers.put("Accept", "application/json; charset=UTF-8");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };
        queue.add(jsonArrayRequest);
    }

    public void callParentMethod() {
        getActivity().onBackPressed();
    }

    private void buildRecyclerViewB() {
        ArrayAdapter<Locaton> adapterb = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, LocationB);
        adapterb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinb.setAdapter(adapterb);
        int res = 0;
        String compareValuee = block_idii;
        // Toast.makeText(getActivity(), compareValuee +" compareValuee ", Toast.LENGTH_SHORT).show();
        for (Locaton element : LocationB) {
            if (element.getId().equals(block_idii)) {

                res = adapterb.getPosition(element);

            //    Toast.makeText(getActivity(), res + "position ", Toast.LENGTH_SHORT).show();

            }
        }

             //     int spinnerPosition = adapter.getPosition(res);

             // on below line we are setting selection for our spinner to spinner position.
            spinb.setSelection(res);



    }

    private void buildRecyclerViewQ() {
        ArrayAdapter<Locaton> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, LocationQ);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinq.setAdapter(adapter);
        int res = 0;

        // Toast.makeText(getActivity(), compareValuee +" compareValuee ", Toast.LENGTH_SHORT).show();
        for (Locaton element : LocationQ) {
            if (element.getId().equals(quarter)) {

                res = adapter.getPosition(element);

           //     Toast.makeText(getActivity(), res + "position ", Toast.LENGTH_SHORT).show();

            }
        }

            //     int spinnerPosition = adapter.getPosition(res);

            // on below line we are setting selection for our spinner to spinner position.
            spinq.setSelection(res);







    }


    private void buildRecyclerViewP() {
        ArrayAdapter<LocatonPrsl> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, LocationPrsl);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinp.setAdapter(adapter);
        int res = 0;
        // Toast.makeText(getActivity(), compareValuee +" compareValuee ", Toast.LENGTH_SHORT).show();
        for (LocatonPrsl element : LocationPrsl) {
            if (element.getId().equals(parcel_id)) {

                res = adapter.getPosition(element);

          //      Toast.makeText(getActivity(), res + "position ", Toast.LENGTH_SHORT).show();

            }

        }
            //     int spinnerPosition = adapter.getPosition(res);

            // on below line we are setting selection for our spinner to spinner position.
            spinp.setSelection(res);





        }

    private void callPUTDataMethod() {

        // below line is for displaying our progress bar.

        // on below line we are creating a retrofit
        // builder and passing our base url
      //  Toast.makeText(getActivity(), systeMNo +"" + servSType , Toast.LENGTH_SHORT).show();

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();





        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://gis.duracity.ps/Mobile/api/")

                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())

                // at last we are building our retrofit builder.
                .build();
        MediaType mediaType = MediaType.parse("application/json");
      //  RequestBody body = RequestBody.create(mediaType, "{\n  \"f_name_a\": \"ffg5555ggfff\",\n  \"s_name_a\": \"ياسر\",\n  \"m_name_a\": \"يا65465سر\",\n  \"l_name_a\": \"ياسر\",\n  \"idcard_type\": \"001\",\n  \"idcard_no\": \"936155027\",\n  \"zone_id\": \"502840\",\n  \"block_id\": \"11\",\n  \"quarter_id\": \"1\",\n  \"parcel_id\": \"2\",\n \n  \"mobile\": \"05620004000\"\n  \n}");


        // below the line is to create an instance for our retrofit api class.
        RetrofitUpdateSerAPI retrofitAPI = retrofit.create(RetrofitUpdateSerAPI.class);





        String  zone_id  ="502840";

         quarter_iddd = etQuarter.getText().toString();
         block_iddd = etBlock.getText().toString();
         parcel_iddd = etParcel.getText().toString();

if (block_iddd.equalsIgnoreCase("0"))
{
    block_iddd ="";

}
    if (quarter_iddd.equalsIgnoreCase("-1"))
    {
        quarter_iddd = "";
    }

        if (parcel_iddd.equalsIgnoreCase("-1"))
        {
            parcel_iddd= "";
        }

     //   Toast.makeText(getActivity(), agreemenT_id  +"  " +  systeMNo +"  " + servSType +"  " + zone_id +"  " + quarter_iddd +"  " + block_iddd +"  " + parcel_iddd , Toast.LENGTH_SHORT).show();


      //  responseTV.setText(agreemenT_id  +"  " +  systeMNo +"  " + servSType +"  " + zone_id +"  " + quarter_iddd +"  " + block_iddd+"  " + parcel_iddd  );

        // passing data from our text fields to our modal class.
        SerLocation modal = new SerLocation(zone_id, block_iddd, quarter_iddd, parcel_iddd);

        // calling a method to create an update and passing our modal class.
        Call<SerLocation> call = retrofitAPI.updateData(agreemenT_id, username, servSType, systeMNo, modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<SerLocation>() {
            @Override
            public void onResponse(Call<SerLocation> call, retrofit2.Response<SerLocation> response) {
            //    Toast.makeText(getActivity(), "Data updated to API", Toast.LENGTH_SHORT).show();

                // below line is for hiding our progress bar.

                // on below line we are setting empty
                // text to our both edit text.
                //jobEdt.setText("");
                //userNameEdt.setText("");

                // we are getting a response from our body and
                // passing it to our modal class.
                SerLocation responseFromAPI = response.body();

                // on below line we are getting our data from modal class
                // and adding it to our string.
                String responseString = "Response Code : " + response.code() + responseFromAPI  ;

                // below line we are setting our string to our text view.
                responseTV.setText("  تم تحديث البيانات " );

            }

            @Override
            public void onFailure(Call<SerLocation> call, Throwable t) {
                responseTV.setText("Error found is : " + t.getMessage());

            }


        });
    }




}



