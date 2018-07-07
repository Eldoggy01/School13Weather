package com.example.admin.school9databases;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String[]> dataSet = new ArrayList<>();


    public MyAdapter(List dataSet) {
        this.dataSet = dataSet;
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
        holder.id = String.valueOf(position+1);
        holder.mLabelView.setText(dataSet.get(position)[1]);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private String id;   //id записи в базе данных
        private TextView mLabelView;
        private Button mEditButton;

        public MyViewHolder(final View itemView) {
            super(itemView);
            mLabelView = itemView.findViewById(R.id.label);
            mEditButton = itemView.findViewById(R.id.editButton);
            mEditButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //переходим на другой экран для просмотра и редактирования заметки
                    itemView.getContext().startActivity(EditNoteActivity.newIntent(itemView.getContext(), id, mLabelView.getText().toString()));

                }
            });
        }

    }
}
