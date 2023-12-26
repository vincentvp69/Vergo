package com.example.vergo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder> {
    private List<Seat> seatList;
    private LayoutInflater inflater;

    // Constructor
    public SeatAdapter(Context context, List<Seat> seatList) {
        inflater = LayoutInflater.from(context);
        this.seatList = seatList;
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.list_item_seat, parent, false);
        return new SeatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SeatViewHolder holder, int position) {
        Seat seat = seatList.get(position);
        holder.bind(seat);
    }

    @Override
    public int getItemCount() {
        return seatList.size();
    }

    class SeatViewHolder extends RecyclerView.ViewHolder {
        // Define your view holder, which includes the views for each list item

        public SeatViewHolder(View itemView) {
            super(itemView);
            // Initialize your views here
        }

        public void bind(Seat seat) {
            // Bind the seat data to your views
        }
    }
}
