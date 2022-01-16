package com.example.mtiproject_olahra_go;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {

    Context ctx;
    Vector<Jadwal> vecJadwal = new Vector<>();

    public JadwalAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public void setVecJadwal(Vector<Jadwal> vecJadwal) {
        this.vecJadwal = vecJadwal;
    }

    @NonNull
    @Override
    public JadwalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.jadwal_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JadwalAdapter.ViewHolder holder, int position) {
        holder.tvDay.setText("" + vecJadwal.get(position).getDay());
        String start, close;

        if(vecJadwal.get(position).getStartTime() > 9) start = "" + vecJadwal.get(position).getStartTime() + ":00";
        else start = "0" + vecJadwal.get(position).getStartTime() + ":00";

        if(vecJadwal.get(position).getCloseTime() > 9) close = "" + vecJadwal.get(position).getCloseTime() + ":00";
        else close = "0" + vecJadwal.get(position).getCloseTime() + ":00";

        String time = start + " - " + close;

        holder.tvTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return vecJadwal.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvDay, tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}
