package com.example.postrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterShow extends RecyclerView.Adapter<AdapterShow.ViewHolder> {
    ArrayList<ModelData> modelDataArrayList;

    public AdapterShow(Context applicationContext, ArrayList<ModelData> modelDataArrayList) {
       this.modelDataArrayList=modelDataArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_activity,parent,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelData data=modelDataArrayList.get(position);
        holder.id.setText(data.getId());
        holder.name.setText(data.getName());
        holder.email.setText(data.getEmail());
        holder.token.setText(data.getToken());
    }

    @Override
    public int getItemCount() {
        return modelDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id,name,email,token;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id);
            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            token=itemView.findViewById(R.id.token);
        }
    }
}
