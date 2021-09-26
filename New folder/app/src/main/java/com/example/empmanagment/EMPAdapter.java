package com.example.empmanagment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EMPAdapter extends RecyclerView.Adapter<EMPAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<EmpModel> courseModalArrayList;
    private Context context;

    // constructor
    public EMPAdapter(ArrayList<EmpModel> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleempcard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        EmpModel modal = courseModalArrayList.get(position);

        holder.empNameTV.setText(modal.getName());
        holder.empEmailTV.setText(modal.getEmail());
//        holder.empDepartmentTV.setText(modal.getDepartment());
//        holder.empBranchTV.setText(modal.getBranch());
//        holder.empContactTV.setText(modal.getContact());
//        holder.empNicTV.setText(modal.getNIC());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView empNameTV, empEmailTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            empEmailTV = itemView.findViewById(R.id.empEmail);
            empNameTV = itemView.findViewById(R.id.empNames);
//            courseDurationTV = itemView.findViewById(R.id.idTVCourseDuration);
//            courseTracksTV = itemView.findViewById(R.id.idTVCourseTracks);
        }
    }
}
