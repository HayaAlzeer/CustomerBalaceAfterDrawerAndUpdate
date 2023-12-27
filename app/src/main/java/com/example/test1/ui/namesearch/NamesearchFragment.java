package com.example.test1.ui.namesearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.test1.R;
import com.example.test1.RecyclerViewAdapter;
import com.example.test1.databinding.FragmentNamesearchBinding;
import com.example.test1.databinding.FragmentSlideshowBinding;
import com.example.test1.ui.home.HomeFragment;
import com.example.test1.ui.result.ResultFragment;
import com.example.test1.ui.resultname.ResultnameFragment;
import com.example.test1.ui.resultname.ResultnameViewModel;

import java.util.ArrayList;
import java.util.List;

public class NamesearchFragment extends Fragment {


    private TextView responseTV;

    private ArrayList<DataModal> DataModelArrayList;


    Button Search;

    EditText fname, sname, thname, foname;

    String sfname, ssname, sthname, sfoname;;

    private ProgressBar loadingPB;

    private LinearLayoutManager layoutManager;

    private RecyclerViewAdapter adapter2;

    private RecyclerView courseRV;

    List<DataModal> userList = null;

    Button back;

    private FragmentNamesearchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       NamesearchViewModel namesearchViewModel =
                new ViewModelProvider(this).get(NamesearchViewModel.class);

        binding = FragmentNamesearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        Search = binding.search ;

        fname = binding.fname;
        sname = binding.sname ;
        thname = binding.thname ;
        foname = binding.foname ;
        responseTV = binding.idTVResponse;
back= (Button) binding.back;
        //  loadingPB = findViewById(R.id.idLoadingPB);





        Search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                // calling a method to post the data and passing our name and job.
                //  postData(fname.getText().toString(), sname.getText().toString(), thname.getText().toString(), foname.getText().toString());


                if (fname.getText().toString().equals("") && sname.getText().toString().equals("") && thname.getText().toString().equals("") && foname.getText().toString().equals("")){

                    Toast.makeText(getActivity(), "ادخل الاسم الذي تريد البحث عنه  "  ,Toast.LENGTH_SHORT).show();

                }
                else {


                    Bundle bundle = new Bundle();

                    bundle.putString("fname",  String.valueOf(fname.getText().toString().trim()));
                    bundle.putString("sname",  String.valueOf(sname.getText().toString().trim()));
                    bundle.putString("thname",  String.valueOf(thname.getText().toString().trim()));
                    bundle.putString("foname",  String.valueOf(foname.getText().toString().trim()));

                    ResultnameFragment GF = new ResultnameFragment();
                    GF.setArguments(bundle);
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction()
                            .replace(R.id.nav_host_fragment_content_main, GF, GF.getTag())
                            .commit();



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
        namesearchViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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