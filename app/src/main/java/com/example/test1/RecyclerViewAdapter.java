package com.example.test1;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {
    private ArrayList<DataModal> item;
    private Context context ;

    public RecyclerViewAdapter(Context context, ArrayList<DataModal> item ) {
        Log.i("viewadapter", "RecyclerViewAdapter");
        this.item = item;
        this.context = context;

        getItemCount();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("create view holder ", "onCreateViewHolder");
        View view = LayoutInflater.from((parent.getContext())).inflate(R.layout.recycler_view_row, parent, false);

        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.i("bind view holder ", "onBindViewHolder");

        DataModal modal = item.get(position);
  /*      holder.courseNameTV.setText(modal.getCourseName());
        holder.courseTracksTV.setText(modal.getCourseTracks());
        holder.courseModeTV.setText(modal.getCourseMode());
*/
        //  holder.systeM_NO.setText(modal.getSysteM_NO());
        //  holder.arabiC_NAME.setText(modal.getArabiC_NAME());
        //  holder.mobile.setText(modal.getMobile());

        Log.i("name", modal.getF_name_a());
        Log.i("hobby", modal.getSysteM_NO());
        holder.name.setText(modal.getF_name_a());
        holder.hobby.setText(modal.getSysteM_NO());
    }

    @Override
    public int getItemCount() {
        Log.i("getItemCount", "getItemCount" +item.size() );

        return item.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, hobby;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.i("name", "ViewHolder");
            Log.i("hobby", "ViewHolder");
            name = itemView.findViewById(R.id.name);
            hobby = itemView.findViewById(R.id.hobby);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        }
    }
}
