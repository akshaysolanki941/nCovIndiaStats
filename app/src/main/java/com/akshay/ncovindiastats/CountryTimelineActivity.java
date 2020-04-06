package com.akshay.ncovindiastats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.akshay.ncovindiastats.Adapters.RVAllCountriesAdapter;
import com.akshay.ncovindiastats.Adapters.RVCountryTimelineAdapter;
import com.akshay.ncovindiastats.Models.CountryData.Example1;
import com.akshay.ncovindiastats.Network.AllCountries.AllCountriesAPIClient;
import com.akshay.ncovindiastats.Network.AllCountries.AllCountriesAPIInterface;

import java.text.DecimalFormat;

public class CountryTimelineActivity extends AppCompatActivity {

    private TextView tv_timeline_countryname;
    private RecyclerView rv_timeline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_timeline);

        final String country_code = getIntent().getStringExtra("country_code");

        tv_timeline_countryname = findViewById(R.id.tv_timeline_countryname);
        rv_timeline = findViewById(R.id.rv_timeline);
        rv_timeline.setHasFixedSize(true);
        rv_timeline.setLayoutManager(new LinearLayoutManager(this));

        getCountryTimeline(country_code);
    }

    private void getCountryTimeline(String country_code) {
        final ProgressDialog dialog = ProgressDialog.show(CountryTimelineActivity.this, "", "Loading. Please wait....", true);

        final AllCountriesAPIInterface allCountriesAPIInterface = AllCountriesAPIClient.getClient().create(AllCountriesAPIInterface.class);

        Call<Example1> call = allCountriesAPIInterface.getCountryData(country_code);
        call.enqueue(new Callback<Example1>() {
            @Override
            public void onResponse(Call<Example1> call, Response<Example1> response) {
                Example1 mydata = response.body();
                tv_timeline_countryname.setText("Timeline (" + mydata.getData().getName() + ")");
                RVCountryTimelineAdapter adapter = new RVCountryTimelineAdapter(mydata, CountryTimelineActivity.this);
                adapter.notifyDataSetChanged();
                rv_timeline.setAdapter(adapter);
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<Example1> call, Throwable t) {
                Toast.makeText(CountryTimelineActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
    }
}
