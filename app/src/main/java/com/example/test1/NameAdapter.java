package com.example.test1;


import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<DataModal> courseModalArrayList;
    private Context context;
    String sertype = "";
    String curtype ="";

    String ss = "";
    public OnItemClicked onClick;
    String SystemNo;

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    // creating a constructor for our variables.



    public NameAdapter(ArrayList<DataModal> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.name_rv_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pposition) {
        // setting data to our views of recycler view.
        DataModal modal = courseModalArrayList.get(pposition);
        final int pos = pposition;




        ss = modal.getSysteM_NO();

        holder.servS_TYPE.setText(modal.getF_name_a());
        //holder.servS_TYPE.setText(modal.getServS_TYPE());
        holder.agreemenT_ID.setText(modal.getS_name_a());

        holder.balance.setText(modal.getM_name_a());


        holder.currencY_ID.setText(modal.getL_name_a());


        SpannableString spanString = new SpannableString(ss);
        spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);


        holder.quarter_id.setText(spanString);

        if (this.courseModalArrayList.get(pposition) != null) {

            if (pposition % 2 == 0) {
                int color = Color.rgb(197,255,176
                );

                int colorlink = Color.rgb(93, 148, 52
                );

                holder.balance.setTextColor(Color.BLACK);
                holder.balance.setBackgroundColor(color);



                holder.servS_TYPE.setTextColor(Color.BLACK);
                holder.servS_TYPE.setBackgroundColor(color);

                holder.agreemenT_ID.setTextColor(Color.BLACK);
                holder.agreemenT_ID.setBackgroundColor(color);

                holder.currencY_ID.setTextColor(Color.BLACK);
                holder.currencY_ID.setBackgroundColor(color);

                holder.quarter_id.setTextColor(colorlink);
                holder.quarter_id.setBackgroundColor(color);



            }else {
                int color = Color.rgb(225,241,215);
                int colorlink = Color.rgb(93, 148, 52
                );

                holder.balance.setTextColor(Color.BLACK);
                holder.balance.setBackgroundColor(color);

                holder.servS_TYPE.setTextColor(Color.BLACK);
                holder.servS_TYPE.setBackgroundColor(color);

                holder.agreemenT_ID.setTextColor(Color.BLACK);
                holder.agreemenT_ID.setBackgroundColor(color);

                holder.currencY_ID.setTextColor(Color.BLACK);
                holder.currencY_ID.setBackgroundColor(color);

                holder.quarter_id.setTextColor(colorlink);
                holder.quarter_id.setBackgroundColor(color);


            }


            // Picasso.get().load(modal.getCourseimg()).into(holder.courseIV);
        }


        holder.quarter_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(pos);
            }
        });

    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }




        @Override
        public int getItemCount () {
            // returning the size of array list.
            return courseModalArrayList.size();
        }



     class ViewHolder extends RecyclerView.ViewHolder {
            // creating variables for our views.
            private TextView courseNameTV, courseModeTV, courseTracksTV, systeM_NO, arabiC_NAME, mobile, balance, servS_TYPE, agreemenT_ID, currencY_ID, quarter_id, block_id, parcel_id;
            private ImageView courseIV;

             ViewHolder(@NonNull View itemView) {
                super(itemView);


                // initializing our views with their ids.

         /*
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseModeTV = itemView.findViewById(R.id.idTVBatch);
            courseTracksTV = itemView.findViewById(R.id.idTVTracks);

            */
                //  systeM_NO  = itemView.findViewById(R.id.systeM_NO);
                //  arabiC_NAME  = itemView.findViewById(R.id.arabiC_NAME);
                //  mobile  = itemView.findViewById(R.id.mobile);
                balance = itemView.findViewById(R.id.balance);
                servS_TYPE = itemView.findViewById(R.id.servS_TYPE);
                agreemenT_ID = itemView.findViewById(R.id.agreemenT_ID);
                currencY_ID = itemView.findViewById(R.id.currencY_ID);
                quarter_id = itemView.findViewById(R.id.quarter_id);




          /*      itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent myIntent = new Intent(context, getsystemNo.class);
                        myIntent.putExtra("systemNo", ss);
                        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                        context.startActivity(myIntent);

                    }});

                */
//


                // courseIV = itemView.findViewById(R.id.idIVCourse);

        }


    }



}



