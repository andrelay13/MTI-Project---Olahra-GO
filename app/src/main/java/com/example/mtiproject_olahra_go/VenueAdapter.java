package com.example.mtiproject_olahra_go;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.ViewHolder> {

    Context ctx;
    Vector<Venue> vecVenues = new Vector<>();
    public static final String SEND_VENUE = "com.example.mtiproject.SEND_VENUE";
    public VenueAdapter(Context ctx){
        this.ctx = ctx;
    }

    public void setVecVenues(Vector<Venue> vecVenues) {
        this.vecVenues = vecVenues;
    }

    public VenueAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.venue_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VenueAdapter.ViewHolder holder, int position) {
        holder.tvVenueName.setText("" + vecVenues.get(position).getVenueName());
        holder.tvVenueAddress.setText("" + vecVenues.get(position).getVenueAddress());

        holder.cvVenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, VenueDetailActivity.class);
                intent.putExtra(SEND_VENUE, vecVenues.get(position).getVenueId());
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vecVenues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        CardView cvVenue;
        TextView tvVenueName, tvVenueAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvVenue = itemView.findViewById(R.id.cvVenue);
            tvVenueName = itemView.findViewById(R.id.tvVenueName);
            tvVenueAddress = itemView.findViewById(R.id.tvVenueAddress);
        }
    }
}
