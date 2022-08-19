package com.example.whetherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class weatherRVAdapter extends RecyclerView.Adapter<weatherRVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<weatherRVModel>weatherRVModelArrayList;

    public weatherRVAdapter(Context context, ArrayList<weatherRVModel> weatherRVModelArrayList) {
        this.context = context;
        this.weatherRVModelArrayList = weatherRVModelArrayList;
    }

    @NonNull
    @Override
    public weatherRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_rv_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull weatherRVAdapter.ViewHolder holder, int position) {
      weatherRVModel model = weatherRVModelArrayList.get(position);
      holder.idTVTemperature.setText(model.getTemperature() + "°C");
      Picasso.get().load("http:".concat(model.getIcon())).into(holder.idIVCondition);
      holder.idTVWindSpeed.setText(model.getWindSpeed() + "km/hr");
      SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
      SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");
      try {
          Date t = input.parse(model.getTime());
          holder.idTVTime.setText(output.format(t));
      }catch (ParseException e){
          e.printStackTrace();
      }
    }

    @Override
    public int getItemCount() {
        return weatherRVModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView idTVWindSpeed, idTVTemperature, idTVTime;
        private ImageView idIVCondition;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTVWindSpeed = itemView.findViewById(R.id.idTVWindSpeed);
            idTVTemperature = itemView.findViewById(R.id.idTVTemperature);
            idTVTime = itemView.findViewById(R.id.idTVTime);
            idIVCondition = itemView.findViewById(R.id.idIVCondition);
        }

    }
}
