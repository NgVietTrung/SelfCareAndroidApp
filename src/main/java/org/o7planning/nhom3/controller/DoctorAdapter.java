package org.o7planning.nhom3.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.o7planning.nhom3.DoctorDetailActivity;
import org.o7planning.nhom3.R;
import org.o7planning.nhom3.model.Doctors;
import org.o7planning.nhom3.model.LoginResponse;


import java.util.ArrayList;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> implements Filterable {
    private List<Doctors.DoctorData.DoctorRecords> listDoctor;
    private List<Doctors.DoctorData.DoctorRecords> listDoctorFilter;
    private String patientId;
    private LoginResponse loginResponse = null;
    private Context context;

    public DoctorAdapter(Context context , List<Doctors.DoctorData.DoctorRecords> listDoctor, LoginResponse loginResponse) {
        this.context = context;
        this.listDoctor = listDoctor;
        this.listDoctorFilter = listDoctor;
        this.loginResponse = loginResponse;
    }
    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctors.DoctorData.DoctorRecords doctor = listDoctor.get(position);
        if(doctor == null){
            return;
        }

//        holder.imgDoctor.setImageResource(doctor.getAvatar());
        holder.textName.setText(doctor.getFullName());
        holder.textSpecialization.setText(doctor.getSpecialisation());

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetail(doctor);
            }
        });
    }

    private void onClickGoToDetail(Doctors.DoctorData.DoctorRecords doctor){
        Intent intent = new Intent(context, DoctorDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_doctor", doctor);
        intent.putExtra("loginAccount", loginResponse);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount(){
        if(listDoctor != null){
            return listDoctor.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    listDoctor = listDoctorFilter;
                }
                else {
                    List<Doctors.DoctorData.DoctorRecords> listTemp = new ArrayList<>();
                    for(Doctors.DoctorData.DoctorRecords doctor : listDoctorFilter){
                        if(doctor.getFullName().toLowerCase().contains(strSearch.toLowerCase())){
                            listTemp.add(doctor);
                        }
                    }

                    listDoctor = listTemp;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listDoctor;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listDoctor = (List<Doctors.DoctorData.DoctorRecords>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public class DoctorViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout layoutItem;
//        private ShapeableImageView imgDoctor;
        private TextView textName;
        private TextView textSpecialization;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_item);
//            imgDoctor = itemView.findViewById(R.id.doctorImage);
            textName = itemView.findViewById(R.id.doctorName);
            textSpecialization = itemView.findViewById(R.id.doctorSpecialization);
        }
    }
}
