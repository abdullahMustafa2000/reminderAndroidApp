package com.ryada.calnderproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ryada.calnderproject.R;
import com.ryada.calnderproject.modules.ReminderModule;

import java.util.List;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.ReminderItemViewHolder> {

    List<ReminderModule> reminderModuleList;
    Context context;

    public RemindersAdapter(List<ReminderModule> reminderModuleList, Context context) {
        this.reminderModuleList = reminderModuleList;
        this.context = context;
    }

    @NonNull
    @Override
    public ReminderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReminderItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reminder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderItemViewHolder holder, int position) {
        ReminderModule module = reminderModuleList.get(position);
        holder.nameTv.setText(module.getName());
        int hour = module.getHour();
        int minute = module.getMinute();
        String pmAM = module.getPm_am() == 0? "PM":"AM";
        String time = hour + ":" + minute + pmAM;
        holder.timeTv.setText(time);
    }

    @Override
    public int getItemCount() {
        return reminderModuleList.size();
    }

    public static class ReminderItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv, timeTv;
        public ReminderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.cldr_title);
            timeTv = itemView.findViewById(R.id.cldr_time);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position, ReminderModule model);
    }
}
