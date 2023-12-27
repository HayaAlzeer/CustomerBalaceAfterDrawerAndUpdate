package com.example.test1.ui.gallery;

import android.content.Intent;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.test1.R;
import com.example.test1.databinding.FragmentGalleryBinding;
import com.example.test1.ui.home.HomeFragment;
import com.example.test1.ui.result.ResultFragment;
import com.example.test1.ui.resultid.ResultidFragment;

public class GalleryFragment extends Fragment {

    TextView tv ;
    EditText EtText;
    Button Search;
    Intent intentt;
    int value;
    int type;

    Button back;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);



        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();




        tv = binding.textView;
        EtText = binding.editText;
        Search = binding.search;
back = binding.back;

        value = getArguments().getInt("type");


        // Toast.makeText(getApplicationContext(), value+""  ,Toast.LENGTH_SHORT).show();


        tv = binding.textView;
        if (value == 1) {
            tv.setText(" ادخل رقم الموبايل ");

        }

        if (value == 2) {
            tv.setText(" ادخل رقم الهوية ");

        }

        if (value == 3) {
            tv.setText(" ادخل الاسم ");

        }

        Search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (EtText.getText().toString().equals("")) {

                    if (value == 1) {
                        Toast.makeText(getActivity(), "ادخل رقم الموبايل ", Toast.LENGTH_SHORT).show();

                    }

                    if (value == 2) {
                        Toast.makeText(getActivity(), "ادخل رقم الهوية  ", Toast.LENGTH_SHORT).show();

                    }


                } else {


                    //  String Link = "http://10.0.0.17:8563/Mobile/api/Customer/MobileCustomerName/";
                    String Link = "http://gis.duracity.ps/Mobile/api/Customer/MobileCustomerName/";

                    String Etvalue = EtText.getText().toString();

                    String MobValue = Link + "" + Etvalue;


                    if (value == 1) {


                      /*
                      intentt = new Intent(getApplicationContext(), result.class);
                       intentt.putExtra("URL", MobValue); //Optional parameters

                       */

                        Bundle bundle = new Bundle();
                        bundle.putString("URL", String.valueOf(MobValue));

                        ResultFragment GF = new ResultFragment();
                        GF.setArguments(bundle);
                        FragmentManager manager = getFragmentManager();
                        manager.beginTransaction()
                                .replace(R.id.nav_host_fragment_content_main, GF, GF.getTag())
                                .commit();

                        GalleryFragment HF = new GalleryFragment();
                        FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
                        fragTransaction.setCustomAnimations(android.R.animator.fade_in,
                                android.R.animator.fade_out);

                        if (HF.isHidden()) {
                            fragTransaction.show(HF);
                            Log.d("hidden", "Show");
                        } else {
                            fragTransaction.hide(HF);
                            Log.d("Shown", "Hide");
                        }

                        fragTransaction.commit();


                    }

                    if (value == 2) {
                        /*
                        intentt = new Intent(getApplicationContext(), resultID.class);
                        //  intentt.putExtra("URL", "http://10.0.0.17:8563/Mobile/api/Customer/DocumentNum/" + Etvalue); //Optional parameters
                        intentt.putExtra("URL", "http://gis.duracity.ps/Mobile/api/Customer/DocumentNum/" + Etvalue); //Optional parameters
                    */
                        Bundle bundle = new Bundle();
                        bundle.putString("URL", String.valueOf("http://gis.duracity.ps/Mobile/api/Customer/DocumentNum/" + Etvalue));


                        ResultFragment GF = new ResultFragment();
                        GF.setArguments(bundle);
                        FragmentManager manager = getFragmentManager();
                        manager.beginTransaction()
                                .replace(R.id.nav_host_fragment_content_main, GF, GF.getTag())
                                .commit();


                        /*
                        ResultidFragment GF = new ResultidFragment();
                        GF.setArguments(bundle);
                        FragmentManager manager = getFragmentManager();
                        manager.beginTransaction()
                                .replace(R.id.nav_host_fragment_content_main, GF, GF.getTag())
                                .commit();
*/
                        GalleryFragment HF = new GalleryFragment();
                        FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
                        fragTransaction.setCustomAnimations(android.R.animator.fade_in,
                                android.R.animator.fade_out);

                        if (HF.isHidden()) {
                            fragTransaction.show(HF);
                            Log.d("hidden", "Show");
                        } else {
                            fragTransaction.hide(HF);
                            Log.d("Shown", "Hide");
                        }

                        fragTransaction.commit();


                    }

                    if (value == 3) {
                        //    intent.putExtra("URL", ""+Etvalue); //Optional parameters

                        // nothing

                    }




                }

            }
        });


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


        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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

}