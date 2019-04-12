package com.android.duongdb.weather.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.duongdb.weather.R;
import com.android.duongdb.weather.models.WeatherModel;

import java.util.ArrayList;

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder>{

    private ArrayList<WeatherModel> weatherModels;
    private Context context;

    public WeatherRecyclerAdapter(Context context, ArrayList<WeatherModel> weatherModels) {
        this.context = context;
        this.weatherModels = weatherModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_weather, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(weatherModels.get(position).getName());
        holder.cencius.setText(weatherModels.get(position).getCencius());
        holder.icon.setImageResource(weatherModels.get(position).getImageId());
    }

    @Override
    public int getItemCount() {
        return weatherModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, cencius;
        private ImageView icon;

        public ViewHolder(View convertView) {
            super(convertView);
            name = (TextView) convertView.findViewById(R.id.next_name);
            icon = (ImageView) convertView.findViewById(R.id.next_icon);
            cencius = (TextView) convertView.findViewById(R.id.next_cencius);
        }
    }
}