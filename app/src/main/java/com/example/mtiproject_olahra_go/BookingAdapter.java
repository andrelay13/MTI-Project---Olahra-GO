package com.example.mtiproject_olahra_go;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {

    Vector<Booking> bookings;
    VenueDB venueDB;
    Venue venue;

    public void setBookings(Vector<Booking> bookings) {
        this.bookings = bookings;
    }

    @NonNull
    @Override
    public BookingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item, parent, false);
        venueDB = new VenueDB(parent.getContext());
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.MyViewHolder holder, int position) {
        venue = venueDB.getVenueDetail(bookings.get(position).getVenueId());

        holder.tvNamaVenue.setText(venue.getVenueName());
        holder.tvAlamatVenue.setText(venue.getVenueAddress());
        holder.tvStartBooking.setText(BookingActivity.start);
        holder.tvStartBooking.setText(BookingActivity.stop);
        holder.tvStatusBooking.setText("Ongoing");

    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaVenue, tvAlamatVenue, tvStartBooking, tvEndBooking, tvStatusBooking;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaVenue = itemView.findViewById(R.id.tvNamaVenue);
            tvAlamatVenue = itemView.findViewById(R.id.tvAlamatVenue);
            tvStartBooking = itemView.findViewById(R.id.tvStartBooking);
            tvEndBooking = itemView.findViewById(R.id.tvEndBooking);
            tvStatusBooking = itemView.findViewById(R.id.tvStatusBooking);

        }
    }
}
