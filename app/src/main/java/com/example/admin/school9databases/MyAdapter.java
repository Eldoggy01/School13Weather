package com.example.admin.school9databases;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
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
//        holder.id = String.valueOf(mDataSet.get(position)[0]);
        holder.labelView.setText(mDataSet.get(position)[2]);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }



    static class MyViewHolder extends RecyclerView.ViewHolder {
        private String id;   //id записи в базе данных
        private TextView labelView;
        private Button editButton;

        public MyViewHolder(final View itemView) {
            super(itemView);
            labelView = itemView.findViewById(R.id.label);
            editButton = itemView.findViewById(R.id.editButton);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //переходим на другой экран для просмотра и редактирования заметки
                    itemView.getContext().startActivity(EditNoteActivity.newIntent(itemView.getContext(), id, labelView.getText().toString()));

                }
            });
        }

    }

}
