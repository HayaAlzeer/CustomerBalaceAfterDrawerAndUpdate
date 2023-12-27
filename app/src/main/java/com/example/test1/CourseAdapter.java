package com.example.test1;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<CourseModal> courseModalArrayList;
    private Context context;
    String sertype = "";
    String curtype ="";
    // creating a constructor for our variables.
    public CourseAdapter(ArrayList<CourseModal> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        CourseModal modal = courseModalArrayList.get(position);
  /*      holder.courseNameTV.setText(modal.getCourseName());
        holder.courseTracksTV.setText(modal.getCourseTracks());
        holder.courseModeTV.setText(modal.getCourseMode());
*/
        //  holder.systeM_NO.setText(modal.getSysteM_NO());
        //  holder.arabiC_NAME.setText(modal.getArabiC_NAME());
        //  holder.mobile.setText(modal.getMobile());
        holder.balance.setText(modal.getBalance());


        switch (modal.getServS_TYPE() ) {
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
                throw new IllegalArgumentException("Invalid day of the week: " + modal.getServS_TYPE());

        }


        holder.servS_TYPE.setText(sertype);


        //holder.servS_TYPE.setText(modal.getServS_TYPE());
        holder.agreemenT_ID.setText(modal.getAgreemenT_ID());


        switch (modal.getCurrencY_ID()) {
            case "NIS":
                curtype = "شيكل";
                break;

            case "JOD":
                curtype = "دينار";
                break;
            case "USD":
                curtype = "دولار";
                break;
            case "EUR":
                curtype = "يورو";
                break;
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + modal.getServS_TYPE());

        }

        holder.currencY_ID.setText(curtype);



        holder.quarter_id.setText(modal.getQuarter_id());
        holder.block_id.setText(modal.getBlock_id());
        holder.parcel_id.setText(modal.getParcel_id());


        if (this.courseModalArrayList.get(position) != null) {

            if (position % 2 == 0) {
                int color = Color.rgb(197,255,176
                );


                holder.balance.setTextColor(Color.BLACK);
                holder.balance.setBackgroundColor(color);

                holder.block_id.setTextColor(Color.BLACK);
                holder.block_id.setBackgroundColor(color);

                holder.servS_TYPE.setTextColor(Color.BLACK);
                holder.servS_TYPE.setBackgroundColor(color);

                holder.agreemenT_ID.setTextColor(Color.BLACK);
                holder.agreemenT_ID.setBackgroundColor(color);

                holder.currencY_ID.setTextColor(Color.BLACK);
                holder.currencY_ID.setBackgroundColor(color);

                holder.quarter_id.setTextColor(Color.BLACK);
                holder.quarter_id.setBackgroundColor(color);

                holder.parcel_id.setTextColor(Color.BLACK);
                holder.parcel_id.setBackgroundColor(color);

            }else {
                int color = Color.rgb(225,241,215);


                holder.balance.setTextColor(Color.BLACK);
                holder.balance.setBackgroundColor(color);

                holder.block_id.setTextColor(Color.BLACK);
                holder.block_id.setBackgroundColor(color);

                holder.servS_TYPE.setTextColor(Color.BLACK);
                holder.servS_TYPE.setBackgroundColor(color);

                holder.agreemenT_ID.setTextColor(Color.BLACK);
                holder.agreemenT_ID.setBackgroundColor(color);

                holder.currencY_ID.setTextColor(Color.BLACK);
                holder.currencY_ID.setBackgroundColor(color);

                holder.quarter_id.setTextColor(Color.BLACK);
                holder.quarter_id.setBackgroundColor(color);

                holder.parcel_id.setTextColor(Color.BLACK);
                holder.parcel_id.setBackgroundColor(color);
            }


            if ( holder.balance.getText().toString().equalsIgnoreCase("0") )
            {

            }else
            {
                holder.balance.setTextColor(Color.RED);

            }

            // Picasso.get().load(modal.getCourseimg()).into(holder.courseIV);
        }





    }


        @Override
        public int getItemCount () {
            // returning the size of array list.
            return courseModalArrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            // creating variables for our views.
            private TextView courseNameTV, courseModeTV, courseTracksTV, systeM_NO, arabiC_NAME, mobile, balance, servS_TYPE, agreemenT_ID, currencY_ID, quarter_id, block_id, parcel_id;
            private ImageView courseIV;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                // initializing our views with their ids.

         /*
         courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseModeTV = itemView.findViewById(R.id.idTVBatch);
            courseTracksTV = itemView.findViewById(R.id.idTVTracks);

            */
                //  systeM_NO  = itemView.findViewById(R.id.systeM_NO);
                //   arabiC_NAME  = itemView.findViewById(R.id.arabiC_NAME);
                //  mobile  = itemView.findViewById(R.id.mobile);
                balance = itemView.findViewById(R.id.balance);
                servS_TYPE = itemView.findViewById(R.id.servS_TYPE);
                agreemenT_ID = itemView.findViewById(R.id.agreemenT_ID);
                currencY_ID = itemView.findViewById(R.id.currencY_ID);
                quarter_id = itemView.findViewById(R.id.quarter_id);
                block_id = itemView.findViewById(R.id.block_id);
                parcel_id = itemView.findViewById(R.id.parcel_id);


                //courseIV = itemView.findViewById(R.id.idIVCourse);
            }
        }
    }
