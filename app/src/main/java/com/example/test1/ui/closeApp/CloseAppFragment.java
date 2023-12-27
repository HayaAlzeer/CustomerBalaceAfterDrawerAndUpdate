package com.example.test1.ui.closeApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.test1.databinding.FragmentCloseappBinding;

;


public class CloseAppFragment extends Fragment {

    private CloseAppViewModel closeAppViewModel;
    private FragmentCloseappBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        closeAppViewModel =
                new ViewModelProvider(this).get(CloseAppViewModel.class);

        binding = FragmentCloseappBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCloseapp;
        closeAppViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });




        System.out.println("haya 4");


        getActivity().finish();
        System.exit(0);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void callParentMethod() {
        getActivity().onBackPressed();
    }



    /**
     * Method to show alert dialog
     */

}
