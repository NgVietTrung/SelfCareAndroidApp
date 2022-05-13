package org.o7planning.nhom3.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.o7planning.nhom3.R;
import org.o7planning.nhom3.model.History;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<History.HistoryData.HistoryUser.HistoryAssignments> listAssignments;

    public HistoryAdapter(List<History.HistoryData.HistoryUser.HistoryAssignments> listDoctor) {
        this.listAssignments = listDoctor;
    }
    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History.HistoryData.HistoryUser.HistoryAssignments assignment = listAssignments.get(position);
        if(assignment == null){
            return;
        }

        holder.textDate.setText("Thời gian: " + assignment.getAssignmentTime().getTime() + "-" + assignment.getAssignmentTime().getDate());
        holder.textDoctorName.setText("ID bác sĩ: " + assignment.getDoctor());
        holder.textSpecialization.setText("Chuyên ngành: " + assignment.getPatient());
        holder.textDescription.setText("Mô tả: " + assignment.getNotes());
    }

    @Override
    public int getItemCount(){
        if(listAssignments != null){
            return listAssignments.size();
        }
        return 0;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout layoutItem;
        private TextView textDate;
        private TextView textDoctorName;
        private TextView textSpecialization;
        private TextView textDescription;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.history_item);
            textDate = itemView.findViewById(R.id.historyDate);
            textDoctorName = itemView.findViewById(R.id.historyDoctorName);
            textSpecialization = itemView.findViewById(R.id.historySpecialization);
            textDescription = itemView.findViewById(R.id.historyDescription);
        }
    }
}
