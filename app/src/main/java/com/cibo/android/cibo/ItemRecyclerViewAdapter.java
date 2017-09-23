package com.cibo.android.cibo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cibo.android.cibo.model.PastTravel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by gerogerke on 23.09.17.
 */

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

    Context context;
    List<PastTravel> items;

    public ItemRecyclerViewAdapter(Context context, List<PastTravel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PastTravel pt = items.get(position);

        holder.cost.setText((pt.cost / 100f) + "€");

        holder.time.setText(formatMillisToDate(pt.startTime, "dd.MM.", false) + " " + formatMillisToDate(pt.startTime, "HH:mm", false) + " ➞ " + formatMillisToDate(pt.endTime, "HH:mm", false));

        holder.line.setText(pt.line);
        setDrawableColor(holder.line.getBackground().mutate(), Color.parseColor(pt.lineColor));

        holder.info.setText((pt.travelLength / 60) + " minutes ∙ " + pt.stations + " stations");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView cost;
        TextView time;
        TextView line;
        TextView info;

        public ViewHolder(View itemView) {
            super(itemView);

            cost = ((ViewGroup)itemView).findViewById(R.id.cost);
            time = ((ViewGroup)itemView).findViewById(R.id.time);
            line = ((ViewGroup)itemView).findViewById(R.id.line);
            info = ((ViewGroup)itemView).findViewById(R.id.info);
        }

    }

    private void setDrawableColor(Drawable background, int color) {
        if (background instanceof ShapeDrawable) {
            // cast to 'ShapeDrawable'
            ShapeDrawable shapeDrawable = (ShapeDrawable) background;
            shapeDrawable.getPaint().setColor(color);
        } else if (background instanceof ColorDrawable) {
            // alpha value may need to be set again after this call
            ColorDrawable colorDrawable = (ColorDrawable) background;
            colorDrawable.setColor(color);
        } else if (background instanceof GradientDrawable) {
            // cast to 'GradientDrawable'
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            gradientDrawable.setColor(color);
        }

    }

    public static String formatMillisToDate(long milliSeconds, String dateFormat, boolean subtractADay) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.US);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        if(subtractADay) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        }
        return formatter.format(calendar.getTime());
    }

}
