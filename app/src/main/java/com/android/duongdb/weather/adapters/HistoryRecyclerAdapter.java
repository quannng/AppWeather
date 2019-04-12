package com.android.duongdb.weather.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.duongdb.weather.R;
import com.android.duongdb.weather.helpers.ImageHelper;
import com.android.duongdb.weather.models.HistoryModel;
import com.android.duongdb.weather.utils.Constants;
import com.android.duongdb.weather.utils.ItemRemoveListener;

import java.util.ArrayList;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder>{

    private ArrayList<HistoryModel> historyModels;
    private Context context;
    private ItemRemoveListener itemRemoveListener;

    public HistoryRecyclerAdapter(Context context, ArrayList<HistoryModel> historyModels, ItemRemoveListener itemRemoveListener) {
        this.context = context;
        this.itemRemoveListener = itemRemoveListener;
        this.historyModels = historyModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_history, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    public void remove(int position) {
        itemRemoveListener.onRemove(position, historyModels.get(position));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(historyModels.get(position).getName());
        holder.cencius.setText(historyModels.get(position).getCencius());
        holder.city.setText(historyModels.get(position).getCity());
        holder.date.setText(historyModels.get(position).getDate());
        holder.icon.setImageResource(ImageHelper.getIconIdFromCode(historyModels.get(position).getImageId()));
        if(position%2 == 0)
            holder.linearLayout.setBackgroundColor(Color.parseColor("#7f818181"));
        else holder.linearLayout.setBackgroundColor(Color.parseColor("#7fcccccc"));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        holder.rippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(Constants.CITY, historyModels.get(holder.getAdapterPosition()).getCity());
                ((Activity) context).setResult(Activity.RESULT_OK, intent);
                ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, city, date, cencius;
        private ImageView icon;
        private LinearLayout linearLayout, rippleLayout;

        public ViewHolder(View convertView) {
            super(convertView);
            city = (TextView) convertView.findViewById(R.id.history_city);
            date = (TextView) convertView.findViewById(R.id.history_time);
            cencius = (TextView) convertView.findViewById(R.id.history_cencius);
            name = (TextView) convertView.findViewById(R.id.history_name);
            icon = (ImageView) convertView.findViewById(R.id.history_icon);
            linearLayout = (LinearLayout) convertView.findViewById(R.id.history_item);
            rippleLayout = (LinearLayout) convertView.findViewById(R.id.ripple_item);
        }
    }
}