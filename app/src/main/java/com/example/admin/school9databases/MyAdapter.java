package com.example.admin.school9databases;



import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String[]> mDataSet = new ArrayList<>();


    public MyAdapter(List dataSet) {
        this.mDataSet = dataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.dayOfWeekView.setText(mDataSet.get(position)[1]);
        holder.temperatureView.setText(mDataSet.get(position)[3]);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }



    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView dayOfWeekView;
        private TextView temperatureView;

        public MyViewHolder(final View itemView) {
            super(itemView);
            temperatureView = itemView.findViewById(R.id.temp);
            dayOfWeekView = itemView.findViewById(R.id.dayOfWeek);

        }

    }

}
