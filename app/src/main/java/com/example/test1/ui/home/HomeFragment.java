package com.example.test1.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.test1.R;
import com.example.test1.databinding.FragmentHomeBinding;
import com.example.test1.ui.gallery.GalleryFragment;
import com.example.test1.ui.namesearch.NamesearchFragment;
import com.example.test1.ui.slideshow.SlideshowFragment;

public class HomeFragment extends Fragment {


    Button mobileSearch;
    Button nameSearch;

    Button IdSearch;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        if (container != null) {
            container.removeAllViews();
        }

        mobileSearch = binding.mobilesearch ;

        mobileSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

              /*
                Intent intent = new Intent(getApplicationContext(), searchby.class);
                intent.putExtra("type", 1); //Optional parameters
                 MainActivity.this.startActivity(intent);
                */

                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);


                GalleryFragment GF = new GalleryFragment();
                GF.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, GF, GF.getTag())
                        .commit();


                HomeFragment HF = new HomeFragment();
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
        });

        IdSearch = binding.idsearch ;

        IdSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                /*
                Intent intent = new Intent(getActivity(), searchby.class);
                intent.putExtra("type", 2); //Optional parameters

                MainActivity.this.startActivity(intent);

                */






                Bundle bundle = new Bundle();
                bundle.putInt("type", 2);


                GalleryFragment GF = new GalleryFragment();
                GF.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, GF, GF.getTag())
                        .commit();


                HomeFragment HF = new HomeFragment();
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
        });


        nameSearch = binding.namesearch ;

        nameSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                /*
                Intent intent = new Intent(getApplicationContext(), SearchByName.class);
                intent.putExtra("type", 3); //Optional parameters

                MainActivity.this.startActivity(intent);

                */

                Bundle bundle = new Bundle();
                bundle.putString("type", String.valueOf(3));


                NamesearchFragment GF = new NamesearchFragment();
                GF.setArguments(bundle);
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, GF, GF.getTag())
                        .commit();


                HomeFragment HF = new HomeFragment();
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
        });



        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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