package com.akshay.ncovindiastats.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import com.akshay.ncovindiastats.Models.AllCountries.Datum;
import com.akshay.ncovindiastats.Models.IndiaStates.Example2;
import com.akshay.ncovindiastats.Models.IndiaStates.Statewise;
import com.akshay.ncovindiastats.R;
import com.akshay.ncovindiastats.StateWiseActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVStatesAdpater extends RecyclerView.Adapter<RVStatesAdpater.ViewHolder> {

    private Example2 example2;
    private Context context;
    private List<Statewise> mylist;
    private List<Statewise> myoriginallist;

    public RVStatesAdpater(Example2 example2, Context context) {
        this.example2 = example2;
        this.context = context;
        mylist = example2.getData().getStatewise();
        myoriginallist = mylist;
    }

    @NonNull
    @Override
    public RVStatesAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_states_item_view, viewGroup, false);
        return new RVStatesAdpater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RVStatesAdpater.ViewHolder viewHolder, final int i) {
        Statewise mydata = mylist.get(i);

        int c = mydata.getConfirmed();
        int r = mydata.getRecovered();
        int a = mydata.getActive();
        int d = mydata.getDeaths();
        DecimalFormat formatter = new DecimalFormat("#,###");
        String confirmed = formatter.format(c);
        String recoverd = formatter.format(r);
        String active = formatter.format(a);
        String dead = formatter.format(d);

        viewHolder.tv_statename.setText(mydata.getState());
        viewHolder.tv_confirmed_state.setText(confirmed + "\nConfirmed");
        viewHolder.tv_recovered_state.setText(recoverd + "\nRecovered");
        viewHolder.tv_active_state.setText(active + "\nActive");
        viewHolder.tv_dead_state.setText(dead + "\nDead");

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_statename, tv_confirmed_state, tv_recovered_state, tv_active_state, tv_dead_state;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_statename = itemView.findViewById(R.id.tv_statename);
            tv_confirmed_state = itemView.findViewById(R.id.tv_confirmed_state);
            tv_recovered_state = itemView.findViewById(R.id.tv_recovered_state);
            tv_active_state = itemView.findViewById(R.id.tv_active_state);
            tv_dead_state = itemView.findViewById(R.id.tv_dead_state);
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
                ArrayList<Statewise> fRecords = new ArrayList<>();

                for (Statewise state : mylist) {
                    if (state.getState().toLowerCase().trim().contains(constraint.toString().toLowerCase().trim())) {
                        fRecords.add(state);
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
            mylist = (ArrayList<Statewise>) results.values;
            notifyDataSetChanged();
        }
    }
}
