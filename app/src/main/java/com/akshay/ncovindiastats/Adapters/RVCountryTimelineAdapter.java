package com.akshay.ncovindiastats.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.akshay.ncovindiastats.CountryDetailActivity;
import com.akshay.ncovindiastats.Models.AllCountries.Datum;
import com.akshay.ncovindiastats.Models.AllCountries.Example;
import com.akshay.ncovindiastats.Models.CountryData.Example1;
import com.akshay.ncovindiastats.Models.CountryData.Timeline;
import com.akshay.ncovindiastats.R;
import com.google.gson.internal.$Gson$Preconditions;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVCountryTimelineAdapter extends RecyclerView.Adapter<RVCountryTimelineAdapter.ViewHolder> {

    private Example1 example1;
    private Context context;

    public RVCountryTimelineAdapter(Example1 example1, Context context) {
        this.example1 = example1;
        this.context = context;
    }

    @NonNull
    @Override
    public RVCountryTimelineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_country_timeline_item_view, viewGroup, false);
        return new RVCountryTimelineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RVCountryTimelineAdapter.ViewHolder viewHolder, final int i) {
        Timeline mytimeline = example1.getData().getTimeline().get(i);

        String date = formateDateFromstring("yyyy-MM-dd", "MMM dd, yyyy", mytimeline.getDate());

        int c = mytimeline.getConfirmed();
        int r = mytimeline.getRecovered();
        int a = mytimeline.getActive();
        int d = mytimeline.getDeaths();
        int nc = mytimeline.getNewConfirmed();
        int nr = mytimeline.getNewRecovered();
        int nd = mytimeline.getNewDeaths();
        DecimalFormat formatter = new DecimalFormat("#,###");
        String confirmed = formatter.format(c);
        String recoverd = formatter.format(r);
        String active = formatter.format(a);
        String dead = formatter.format(d);
        String new_confirmed = formatter.format(nc);
        String new_recovered = formatter.format(nr);
        String new_dead = formatter.format(nd);

        viewHolder.tv_date.setText(date);
        viewHolder.tv_timeline_confirmed.setText(confirmed + "\nConfirmed");
        viewHolder.tv_timeline_recovered.setText(recoverd + "\nRecovered");
        viewHolder.tv_timeline_active.setText(active + "\nActive");
        viewHolder.tv_timeline_dead.setText(dead + "\nDead");
        viewHolder.tv_new_confirmed.setText(new_confirmed + "\nNew Confirmed");
        viewHolder.tv_new_recovered.setText(new_recovered + "\nNew Recovered");
        viewHolder.tv_new_dead.setText(new_dead + "\nNew Dead");
    }

    @Override
    public int getItemCount() {
        return example1.getData().getTimeline().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_date, tv_timeline_confirmed, tv_timeline_recovered, tv_timeline_active, tv_timeline_dead, tv_new_confirmed, tv_new_recovered, tv_new_dead;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_date = itemView.findViewById(R.id.tv_date);
            tv_timeline_confirmed = itemView.findViewById(R.id.tv_timeline_confirmed);
            tv_timeline_recovered = itemView.findViewById(R.id.tv_timeline_recovered);
            tv_timeline_active = itemView.findViewById(R.id.tv_timeline_active);
            tv_timeline_dead = itemView.findViewById(R.id.tv_timeline_dead);
            tv_new_confirmed = itemView.findViewById(R.id.tv_new_confirmed);
            tv_new_recovered = itemView.findViewById(R.id.tv_new_recovered);
            tv_new_dead = itemView.findViewById(R.id.tv_new_dead);
        }
    }

    private String formateDateFromstring(String inputFormat, String outputFormat, String inputDate) {

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, java.util.Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return outputDate;
    }
}
