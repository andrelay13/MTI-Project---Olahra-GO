package com.example.mtiproject_olahra_go.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mtiproject_olahra_go.BookingAdapter;
import com.example.mtiproject_olahra_go.BookingDB;
import com.example.mtiproject_olahra_go.R;

public class BookingFragment extends Fragment {

    RecyclerView rvBooking;
    BookingDB bookingDB;

    public BookingFragment() {

    }

    public static BookingFragment newInstance() {
        BookingFragment fragment = new BookingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bookingDB = new BookingDB(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        rvBooking = view.findViewById(R.id.rvBooking);

        BookingAdapter adapter = new BookingAdapter();
        adapter.setBookings(bookingDB.getBookings());

        rvBooking.setAdapter(adapter);

        rvBooking.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        return view;
    }
}