package com.example.discoverar.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discoverar.R;
import com.example.discoverar.models.Journey;

import java.util.ArrayList;

public class JourneyAdapter extends RecyclerView.Adapter<JourneyAdapter.JourneyViewHolder> {
    private ArrayList<Journey> journeys;

    public JourneyAdapter(ArrayList<Journey> journeys) {
        this.journeys = journeys;
    }

    @NonNull
    @Override
    public JourneyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.journey_item_list, parent, false);
        return new JourneyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JourneyViewHolder holder, int position) {
        holder.titleTextView.setText(journeys.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return journeys.size();
    }

    public class JourneyViewHolder extends RecyclerView.ViewHolder {
        protected TextView titleTextView;

        public JourneyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.journey_item_list_title);
        }
    }
}
