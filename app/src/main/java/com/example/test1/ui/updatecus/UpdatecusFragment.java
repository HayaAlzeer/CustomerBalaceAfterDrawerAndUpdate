package com.example.test1.ui.updatecus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.test1.CourseAdapter;
import com.example.test1.CourseModal;
import com.example.test1.DocType;
import com.example.test1.Locaton;
import com.example.test1.LocatonPrsl;
import com.example.test1.R;
import com.example.test1.RetrofitUpdateAPI;
import com.example.test1.UserModal;
import com.example.test1.databinding.FragmentGetsystemnoBinding;
import com.example.test1.databinding.FragmentSlideshowBinding;
import com.example.test1.databinding.FragmentUpdatecusBinding;
import com.example.test1.ui.home.HomeFragment;
import com.example.test1.ui.servlist.ServlistFragment;
import com.example.test1.ui.update.UpdateFragment;

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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdatecusFragment extends Fragment {

    private FragmentUpdatecusBinding binding;


    RadioGroup etISDen;
    EditText  etFname, etSname, etThname, etLname, etMobile, etDocNo, etNote ,etemail, etstreet_id;
    TextView   etCusNo;
    EditText etBlock, etQuarter, etParcel, etBulidNum, etBulidName, etFlatNum, etFloorNum;
    Spinner etDocType;
    RadioButton owner, user;

    TextView tv12, textView2, tv2, tv3, tv4, tv5, tv6, tv9, tv7, tv10;

    Button EditSerButton, updatedata;


    int DocumntType = 001;
    int IsBen = 1;

    String Fname, Sname, Thname, Lname, DocNo, Note, CreateBy, CreateAt, BulidName;
    int Mobile;
    int CusNo;
    int Block, Quarter, Parcel, BulidNum, FloorNum, FlatNum, CusID, SerID;

    String UserId;
    String UserName;

    String url;

    String systeM_NO;

    Spinner spin;


    private ProgressBar loadingPB;
    private TextView responseTV;


    Spinner spinb;
    Spinner spinq;
    Spinner spinp;


    ArrayList<Locaton> LocationB;
    ArrayList<Locaton> LocationQ;
    ArrayList<LocatonPrsl> LocationPrsl;



    String  block_idii, quarter, parcel_id, token;
    String username, password;

    Button back;
    String arabic_name , mobile ,email , building_id , zone_id , flat_id , street_id , quarter_id , block_id , idcard_type , idcard_no , f_name_a ,s_name_a , m_name_a , l_name_a ;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UpdatecusViewModel updatecusViewModel =
                new ViewModelProvider(this).get(UpdatecusViewModel.class);

        binding = FragmentUpdatecusBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        loadingPB = binding.idPBLoading;
        responseTV = binding.idTVResponse;

        etCusNo = (TextView) binding.CustomerNO;
        etFname = (EditText) binding.Fname;
        etSname = (EditText) binding.SName;
        etThname = (EditText) binding.ThName;
        etLname = (EditText) binding.FoName;
        etMobile = (EditText) binding.mobile;
        etDocNo = (EditText) binding.DocNo;
        etDocType = (Spinner) binding.DocType;
        etISDen = (RadioGroup) binding.IsBen;
        owner = (RadioButton) binding.owner;
        user = (RadioButton) binding.user;

        back = (Button) binding.back;

        etemail= (EditText) binding.email;
        etstreet_id   = (EditText) binding.streetid;


        etParcel = (EditText) binding.parcel;
        etQuarter = (EditText) binding.quarter;
        etBulidName = (EditText) binding.BName;
        etBulidNum = (EditText) binding.Bnum;

        etBlock = (EditText) binding.block;
        etFlatNum = (EditText) binding.FlatNum;
        etFloorNum = (EditText) binding.FloorNum;

        tv12 = (TextView) binding.tv12;
        textView2 = (TextView) binding.textView2;
        tv2 = (TextView) binding.tv2;
        tv3 = (TextView) binding.tv3;
        tv4 = (TextView) binding.tv4;
        tv5 = (TextView) binding.tv5;
        tv6 = (TextView) binding.tv6;
        tv9 = (TextView) binding.tv9;
        tv7 = (TextView) binding.tv7;
        tv10 = (TextView) binding.tv10;

        EditSerButton = (Button) binding.AddSer;
        updatedata = (Button) binding.button;

        spinb = (Spinner) binding.blk;
        spinq = (Spinner) binding.qurtr;
        spinp = (Spinner) binding.prsl;


        LocationB = new ArrayList<>();
        LocationQ = new ArrayList<>();
        LocationPrsl = new ArrayList<>();



        SharedPreferences preferences = this.getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);

        username = preferences.getString("username", "0");
        password = preferences.getString("password", "haya");
        token = preferences.getString("token", "abc");

        etBlock.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        etMobile.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        etBulidNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        etFlatNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        etFloorNum.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        etQuarter.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        etParcel.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);




        url = String.valueOf(getArguments().getString("URL"));
        systeM_NO = String.valueOf(getArguments().getString("System_No"));


        arabic_name = String.valueOf(getArguments().getString("arabic_name"));
        mobile =   String.valueOf(getArguments().getString("mobile"));
        email  =  String.valueOf(getArguments().getString("email"));
        building_id =  String.valueOf(getArguments().getString("building_id"));
        zone_id = String.valueOf(getArguments().getString("zone_id"));

        flat_id =  String.valueOf(getArguments().getString("flat_id"));
        street_id = String.valueOf(getArguments().getString("street_id"));

        parcel_id =  String.valueOf(getArguments().getString("parcel_id"));

        quarter_id =  String.valueOf(getArguments().getString("quarter_id"));
        block_id = String.valueOf(getArguments().getString("block_id"));
        idcard_type = String.valueOf(getArguments().getString("idcard_type"));

        idcard_no = String.valueOf(getArguments().getString("idcard_no"));

        f_name_a = String.valueOf(getArguments().getString("f_name_a"));
        s_name_a = String.valueOf(getArguments().getString("s_name_a"));
        m_name_a = String.valueOf(getArguments().getString("m_name_a"));
        l_name_a = String.valueOf(getArguments().getString("l_name_a"));



        block_idii = block_id;
        quarter = quarter_id;

        etCusNo.setText(systeM_NO);

       // Toast.makeText(getActivity(), block_idii + "block ", Toast.LENGTH_SHORT).show();

        etFname.setText(f_name_a);
        etSname.setText(s_name_a);
        etThname.setText(m_name_a);
        etLname.setText(l_name_a);
        etMobile.setText(mobile);
        etDocNo.setText(idcard_no);





        etParcel.setText(parcel_id);
        etQuarter.setText(quarter_id);
        etBulidName.setText("");
        etBulidNum.setText(building_id);

        etBlock.setText(block_id);
        etFlatNum.setText(flat_id);
        etFloorNum.setText("");
        etemail.setText(email);
        etstreet_id.setText(street_id);





        //Getting the instance of Spinner and applying OnItemSelectedListener on it
         spin = (Spinner) binding.DocType;
        //calling DbAdapter


        ArrayList<DocType> countryList = new ArrayList<>();
        //Add countries

        countryList.add(new DocType("001", "بطاقة هوية "));
        countryList.add(new DocType("002", "جواز سفر أردني"));
        countryList.add(new DocType("003", "هوية اسرائيلية"));

        //fill data in spinner
        ArrayAdapter<DocType> adapter = new ArrayAdapter<DocType>(getActivity(), android.R.layout.simple_spinner_dropdown_item, countryList);
        spin.setAdapter(adapter);



        if (idcard_type.equalsIgnoreCase(""))
        {
            idcard_type = "001";
            Toast.makeText(getActivity(), "" , Toast.LENGTH_SHORT).show();
        }

        int compareValue = Integer.parseInt(idcard_type);
        if (compareValue == 1) {
            spin.setSelection(0);
        } else if (compareValue == 2) {
            spin.setSelection(1);
        } else
            spin.setSelection(2);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                DocType country = (DocType) parent.getSelectedItem();
                // Toast.makeText(getApplicationContext(), "Country ID: " + country.getId() + ",  Country Name : " + country.getName(), Toast.LENGTH_SHORT).show();
                DocumntType = Integer.parseInt(country.getId());


                if (DocumntType == 1) {
                    etDocNo.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                    etDocNo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});

                    etDocNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (hasFocus) {
                                if (etDocNo.getText().toString().trim().length() < 9)
                                    etDocNo.setError("عدد الخانات يجب ان يكون 9 ");
                                else
                                    etDocNo.setError(null);
                            }
                        }
                    });


                } else if (DocumntType == 2) {
                    etDocNo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                    etDocNo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});

                } else {
                    etDocNo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                    etDocNo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12)});

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        getblock();

        //getQuarter();

        //getParcel();




        spinb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Locaton country = (Locaton) parent.getSelectedItem();
                //  Toast.makeText(getApplicationContext(), "Country ID: "+country.getId()+",  Country Name : "+country.getName(), Toast.LENGTH_SHORT).show();
             //   int SerType = Integer.parseInt(country.getId());

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
             //   int SerType = Integer.parseInt(country.getId());

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
              //  int SerType = Integer.parseInt(country.getId());

                String prsl = LocationPrsl.get(position).getId();
                etParcel.setText(prsl);
               // Toast.makeText(getActivity(), prsl + SerType, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        EditSerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();

                bundle.putString("URL", String.valueOf( "http://gis.duracity.ps/Mobile/api/Customer/SystemNumber/"+ systeM_NO));

                ServlistFragment GF = new ServlistFragment();
                GF.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, GF, GF.getTag())
                        .commit();

            }
        });

        updatedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etFname.getText().toString()) && TextUtils.isEmpty(etLname.getText().toString())) {

                    // displaying a toast message if the edit text is empty.
                    Toast.makeText(getActivity(), "Please enter your data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // calling a method to update data in our API.
                callPUTDataMethod();



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

        final TextView textView = binding.textSlideshow;
        updatecusViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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

        etSname.setText(url);



        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,   "http://gis.duracity.ps/Mobile/api/Customer/SystemNumber/Name/"+systeM_NO, null , new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                Log.d("ADebugTag", "Value: " +""+response.toString()  );

                etThname.setText(response.toString() );



                    //    Toast.makeText(getApplicationContext(), " haya length"+response.length()  ,Toast.LENGTH_SHORT).show();
                    //    Toast.makeText(getApplicationContext(), " haya "  ,Toast.LENGTH_SHORT).show();
                 //   Log.d("ADebugTag", "Value: " +""+response.length()  );

                    // creating a new json object and
                    // getting each object from our json array.
                    try {
                        // we are getting each json object.



                       // JSONObject responseObj = jArr.getJSONObject(0);

                        JSONObject responseObj = new JSONObject(String.valueOf(response));

                      //  etLname.setText(responseObj.getString("arabic_name"));


                        // now we get our response from API in json object format.
                        // in below line we are extracting a string with
                        // its key value from our json object.
                        // similarly we are extracting all the strings from our json object.



                      //  systeM_NO = responseObj.getString("systeM_NO");
                        String arabic_name = responseObj.getString("arabic_name");
                        String mobile = responseObj.getString("mobile");
                        String email = responseObj.getString("email");
                        String building_id = responseObj.getString("building_id");
                        String zone_id = responseObj.getString("zone_id");
                        String flat_id = responseObj.getString("flat_id");
                        String street_id = responseObj.getString("street_id");
                        String quarter_id = responseObj.getString("quarter_id");
                        String block_id = responseObj.getString("block_id");


                        parcel_id = responseObj.getString("parcel_id");
                        block_idii = block_id;
                        quarter = quarter_id;


                        String idcard_type = responseObj.getString("idcard_type");

                        String idcard_no = responseObj.getString("idcard_no");
                        String f_name_a = responseObj.getString("f_name_a");
                        String s_name_a = responseObj.getString("s_name_a");
                        String m_name_a = responseObj.getString("m_name_a");
                        String l_name_a = responseObj.getString("l_name_a");


                     //   Toast.makeText(getActivity(), f_name_a + s_name_a + m_name_a + l_name_a +"haya " + block_idii + "block", Toast.LENGTH_SHORT).show();

                      //  etCusNo.setText(systeM_NO);
                        etFname.setText(f_name_a);
                        etSname.setText(s_name_a);
                        etThname.setText(m_name_a);
                        etLname.setText(l_name_a);
                        etMobile.setText(mobile);
                        etDocNo.setText(idcard_no);





                        etParcel.setText(parcel_id);
                        etQuarter.setText(quarter_id);
                        etBulidName.setText("");
                        etBulidNum.setText(building_id);

                        etBlock.setText(block_id);
                        etFlatNum.setText(flat_id);
                        etFloorNum.setText("");
                        etemail.setText(email);
                        etstreet_id.setText(street_id);

                        if (idcard_type.equalsIgnoreCase(""))
                        {
                            idcard_type = "001";
                        }

                        int compareValue = Integer.parseInt(idcard_type);
                        if (compareValue == 1) {
                            spin.setSelection(0);
                        } else if (compareValue == 2) {
                            spin.setSelection(1);
                        } else
                            spin.setSelection(2);



                        System.out.println(systeM_NO);
                        System.out.println(systeM_NO);
                        System.out.println(systeM_NO);



                        getblock();

                     //   Toast.makeText(getActivity(), block_idii + "block" , Toast.LENGTH_SHORT).show();




                        // getQuarter();

                        // getParcel();




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Fail to get the data..", Toast.LENGTH_SHORT).show();

            }
        });
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


    private void callPUTDataMethod() {

        // below line is for displaying our progress bar.
        loadingPB.setVisibility(View.VISIBLE);

        // on below line we are creating a retrofit
        // builder and passing our base url
     //   Toast.makeText(getActivity(), systeM_NO +"" , Toast.LENGTH_SHORT).show();

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
        RequestBody body = RequestBody.create(mediaType, "{\n  \"f_name_a\": \"ffg5555ggfff\",\n  \"s_name_a\": \"ياسر\",\n  \"m_name_a\": \"يا65465سر\",\n  \"l_name_a\": \"ياسر\",\n  \"idcard_type\": \"001\",\n  \"idcard_no\": \"936155027\",\n  \"zone_id\": \"502840\",\n  \"block_id\": \"11\",\n  \"quarter_id\": \"1\",\n  \"parcel_id\": \"2\",\n \n  \"mobile\": \"05620004000\"\n  \n}");


        // below the line is to create an instance for our retrofit api class.
        RetrofitUpdateAPI retrofitAPI = retrofit.create(RetrofitUpdateAPI.class);




        String mobile = etMobile.getText().toString();
        String idcard_type;

        if (DocumntType == 1){
             idcard_type = "001";
        } else if (DocumntType == 2) {
             idcard_type = "002";
        }else {
             idcard_type = "003";
        }

      //  Toast.makeText(getActivity(),idcard_type +"idcard_type ", Toast.LENGTH_SHORT  ).show();
        String  zone_id  ="502840";
        String idcard_no = etDocNo.getText().toString();
        String f_name_a = etFname.getText().toString();
        String s_name_a = etSname.getText().toString();
        String m_name_a = etThname.getText().toString();
        String l_name_a = etLname.getText().toString();
        String quarter_id = etQuarter.getText().toString();
        String block_id = etBlock.getText().toString();
        String parcel_id = etParcel.getText().toString();
        String street_id = etstreet_id.getText().toString();
        String building_id = etBulidNum.getText().toString();
        String flat_id = etFlatNum.getText().toString();
        String email =etemail.getText().toString();




        if (block_id.equalsIgnoreCase("0"))
        {
            block_id ="";

        }
        if (quarter_id.equalsIgnoreCase("-1"))
        {
            quarter_id = "";
        }

        if (parcel_id.equalsIgnoreCase("-1"))
        {
            parcel_id= "";
        }


/*
        String mobile = "0599551775";
        String idcard_type = "001";
        String  zone_id  ="502840";
        String quarter_id = "1";
        String block_id = "11";
        String parcel_id = "2";
        String idcard_no = "936155027";
        String f_name_a = "haya";
        String s_name_a = "f";
        String m_name_a ="m";
        String l_name_a = "z";
*/


        // passing data from our text fields to our modal class.
        UserModal modal = new UserModal(f_name_a, s_name_a, m_name_a, l_name_a, idcard_type, idcard_no, zone_id, block_id, quarter_id, parcel_id, mobile, street_id, building_id, flat_id, email);

        // calling a method to create an update and passing our modal class.
        Call<UserModal> call = retrofitAPI.updateData( systeM_NO, username, modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<UserModal>() {
            @Override
            public void onResponse(Call<UserModal> call, retrofit2.Response<UserModal> response) {
              //  Toast.makeText(getActivity(), "Data updated to API", Toast.LENGTH_SHORT).show();

                // below line is for hiding our progress bar.
                loadingPB.setVisibility(View.GONE);

                // on below line we are setting empty
                // text to our both edit text.
                //jobEdt.setText("");
                //userNameEdt.setText("");

                // we are getting a response from our body and
                // passing it to our modal class.
                UserModal responseFromAPI = response.body();

                // on below line we are getting our data from modal class
                // and adding it to our string.

                String responseString = "  تم تحديث البيانات  " ;


              //  String responseString = "Response Code : " + response.code() + responseFromAPI  ;
                Log.d("ADebugTag", "Value: " + ""+ f_name_a +  s_name_a + m_name_a+  l_name_a+ idcard_type+ idcard_no+ zone_id+  mobile +  street_id+  building_id+   flat_id+ email  );

                // below line we are setting our string to our text view.
                responseTV.setText(responseString);
            }

            @Override
            public void onFailure(Call<UserModal> call, Throwable t) {
                responseTV.setText("Error found is : " + t.getMessage());

            }


        });
    }

    private void getblock() {
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.



        // Create an array to populate the spinner
        ArrayList<String> worldlist = new ArrayList<String>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "http://gis.duracity.ps/Mobile/api/Geo/block/502840" , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                if (response.length() == 0) {


               //     Toast.makeText(getActivity(), "راجع مركز خدمات الجمهور لتحديث بياناتك ... ", Toast.LENGTH_SHORT).show();

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


                            worldlist.add(responseObj.optString("name"));

                            LocationB.add(new Locaton(id, name));


                        //    Toast.makeText(getActivity(), name + id + "haya ", Toast.LENGTH_SHORT).show();


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


                            worldlist.add(responseObj.optString("name"));

                            LocationQ.add(new Locaton(id, name));


                      //      Toast.makeText(getActivity(), name + id + "haya ", Toast.LENGTH_SHORT).show();


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


        ArrayList<String> countryName = new ArrayList<String>();


        // Locate the WorldPopulation Class
        ArrayList<Locaton> world = new ArrayList<Locaton>();

        // Create an array to populate the spinner
        ArrayList<String> worldlist = new ArrayList<String>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "http://gis.duracity.ps/Mobile/api/Geo/Parcel/"+block_idii+","+quarter , null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                if (response.length() == 0) {


                //    Toast.makeText(getActivity(), "راجع مركز خدمات الجمهور لتحديث بياناتك ... ", Toast.LENGTH_SHORT).show();

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


                            worldlist.add(responseObj.optString("name"));

                            LocationPrsl.add(new LocatonPrsl(id, name));


                        //    Toast.makeText(getActivity(), name + id + "haya ", Toast.LENGTH_SHORT).show();


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

            //    Toast.makeText(getActivity(), res + "position ", Toast.LENGTH_SHORT).show();

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

              //  Toast.makeText(getActivity(), res + "position ", Toast.LENGTH_SHORT).show();

            }

        }
        //     int spinnerPosition = adapter.getPosition(res);

        // on below line we are setting selection for our spinner to spinner position.
        spinp.setSelection(res);





    }




}