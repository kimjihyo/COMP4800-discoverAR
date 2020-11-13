package com.example.discoverar.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discoverar.MainActivity;
import com.example.discoverar.R;
import com.example.discoverar.ScanActivity;
import com.example.discoverar.models.Journey;

import java.util.ArrayList;

public class JourneyAdapter extends RecyclerView.Adapter<JourneyAdapter.JourneyViewHolder> {
    private ArrayList<Journey> journeys;
    private Context context;

    public JourneyAdapter(ArrayList<Journey> journeys, Context context) {
        this.context = context;
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
        holder.titleDescView.setText(journeys.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof ScanActivity) {
                    ((ScanActivity)context).setCurrentImageArr(journeys.get(position).getImages());
                }
                Toast.makeText(context,"Journey " + journeys.get(position).getTitle() + " set",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return journeys.size();
    }

    public class JourneyViewHolder extends RecyclerView.ViewHolder {
        protected TextView titleTextView;
        protected TextView titleDescView;

        public JourneyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.journey_item_list_title);
            titleDescView = itemView.findViewById(R.id.journey_item_list_desc);
        }
    }
}
