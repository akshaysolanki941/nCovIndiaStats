package com.akshay.ncovindiastats.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.akshay.ncovindiastats.CountryDetailActivity;
import com.akshay.ncovindiastats.Models.AllCountries.Datum;
import com.akshay.ncovindiastats.Models.AllCountries.Example;
import com.akshay.ncovindiastats.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVAllCountriesAdapter extends RecyclerView.Adapter<RVAllCountriesAdapter.ViewHolder> {

    private Example example;
    private Context context;
    private List<Datum> mylist;
    private List<Datum> myoriginallist;

    public RVAllCountriesAdapter(Example example, Context context) {
        this.example = example;
        this.context = context;
        mylist = example.getData();
        myoriginallist = mylist;
    }

    @NonNull
    @Override
    public RVAllCountriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_countries_item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RVAllCountriesAdapter.ViewHolder viewHolder, final int i) {
        final Datum mydata = mylist.get(i);

        int c = mydata.getLatestData().getConfirmed();
        int r = mydata.getLatestData().getRecovered();
        int d = mydata.getLatestData().getDeaths();
        DecimalFormat formatter = new DecimalFormat("#,###");
        String confirmed = formatter.format(c);
        String recoverd = formatter.format(r);
        String dead = formatter.format(d);

        viewHolder.countryname.setText(mydata.getName());
        viewHolder.confirmed.setText(confirmed + "\nConfirmed");
        viewHolder.recovered.setText(recoverd + "\nRecovered");
        viewHolder.dead.setText(dead + "\nDead");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CountryDetailActivity.class);
                i.putExtra("countrycode", mydata.getCode());
                context.startActivity(i);
            }
        });

        viewHolder.ib_countrydetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CountryDetailActivity.class);
                i.putExtra("countrycode", mydata.getCode());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView countryname, confirmed, recovered, dead;
        ImageButton ib_countrydetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            countryname = itemView.findViewById(R.id.tv_countryname);
            confirmed = itemView.findViewById(R.id.tv_confirmed_countries);
            recovered = itemView.findViewById(R.id.tv_recovered_countries);
            dead = itemView.findViewById(R.id.tv_dead_countries);
            ib_countrydetail = itemView.findViewById(R.id.ib_countrydetaildata);
        }
    }

    private Filter fRecords;

    //return the filter class object
    public Filter getFilter() {
        if (fRecords == null) {
            fRecords = new RecordFilter();
        }
        return fRecords;
    }

    //filter class
    private class RecordFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            //Implement filter logic
            // if edittext is null return the actual list
            if (constraint == null || constraint.length() == 0) {
                //No need for filter
                results.values = myoriginallist;
                results.count = myoriginallist.size();

            } else {
                //Need Filter
                // it matches the text  entered in the edittext and set the data in adapter list
                ArrayList<Datum> fRecords = new ArrayList<>();

                for (Datum data : mylist) {
                    if (data.getName().toLowerCase().trim().contains(constraint.toString().toLowerCase().trim())) {
                        fRecords.add(data);
                    }
                }
                results.values = fRecords;
                results.count = fRecords.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            //it set the data from filter to adapter list and refresh the recyclerview adapter
            mylist = (ArrayList<Datum>) results.values;
            notifyDataSetChanged();
        }
    }
}

