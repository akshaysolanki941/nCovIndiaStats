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
import com.akshay.ncovindiastats.Models.Numbers.Example3;
import com.akshay.ncovindiastats.Models.Numbers.Regional;
import com.akshay.ncovindiastats.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVContactsAdapter extends RecyclerView.Adapter<RVContactsAdapter.ViewHolder> {

    private Example3 example3;
    private Context context;
    private List<Regional> mylist;
    private List<Regional> myoriginallist;

    public RVContactsAdapter(Example3 example3, Context context) {
        this.example3 = example3;
        this.context = context;
        mylist = example3.getData().getContacts().getRegional();
        myoriginallist = mylist;
    }

    @NonNull
    @Override
    public RVContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_contacts_item_view, viewGroup, false);
        return new RVContactsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RVContactsAdapter.ViewHolder viewHolder, final int i) {
        final Regional mydata = mylist.get(i);

        viewHolder.tv_stateName.setText(mydata.getLoc());
        viewHolder.tv_number.setText(mydata.getNumber());
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_stateName, tv_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_stateName = itemView.findViewById(R.id.tv_stateName);
            tv_number = itemView.findViewById(R.id.tv_number);
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
                ArrayList<Regional> fRecords = new ArrayList<>();

                for (Regional data : mylist) {
                    if (data.getLoc().toLowerCase().trim().contains(constraint.toString().toLowerCase().trim())) {
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
            mylist = (ArrayList<Regional>) results.values;
            notifyDataSetChanged();
        }
    }
}
