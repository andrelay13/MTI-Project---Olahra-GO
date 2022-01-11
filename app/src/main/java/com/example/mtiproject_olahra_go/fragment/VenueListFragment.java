package com.example.mtiproject_olahra_go.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mtiproject_olahra_go.DBHelper;
import com.example.mtiproject_olahra_go.R;
import com.example.mtiproject_olahra_go.Venue;
import com.example.mtiproject_olahra_go.VenueAdapter;
import com.example.mtiproject_olahra_go.VenueDB;

import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VenueListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VenueListFragment extends Fragment {

    public static final String KEY_SPORT = "com.example.application.OlahraGO.KEY_SPORT";

    TextView tvTitle;
    RecyclerView rvVenues;
    Vector<Venue> vecVenues = new Vector<>();
    VenueDB venueDB;
    DBHelper dbHelper;
    String sport;
    public VenueListFragment() {
        // Required empty public constructor
    }


    public static VenueListFragment newInstance(String sport) {
        VenueListFragment fragment = new VenueListFragment();
        Bundle args = new Bundle();
        args.putString(KEY_SPORT, sport);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_venue_list, container, false);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText("" + getArguments().get(KEY_SPORT));
        getRvData(view);
        return view;
    }

    public void getRvData(View view){
        String sport = getArguments().getString(KEY_SPORT);
        rvVenues = view.findViewById(R.id.rvVenues);
        venueDB = new VenueDB(getContext());
        vecVenues = venueDB.getVenue(sport);
        VenueAdapter venueAdapter = new VenueAdapter(getContext());
        venueAdapter.setVecVenues(vecVenues);

        rvVenues.setAdapter(venueAdapter);
        rvVenues.setLayoutManager(new GridLayoutManager(getContext(), 1));
    }
}