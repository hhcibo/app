package com.cibo.android.cibo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cibo.android.cibo.model.PastTravel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by gerogerke on 23.09.17.
 */

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

    List<PastTravel> items;

    public ItemRecyclerViewAdapter(List<PastTravel> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cost.setText(items.get(position).cost);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView cost;
        TextView time;

        public ViewHolder(View itemView) {
            super(itemView);

            cost = ((ViewGroup)itemView).findViewById(R.id.cost);
            time = ((ViewGroup)itemView).findViewById(R.id.time);
        }

    }

}
