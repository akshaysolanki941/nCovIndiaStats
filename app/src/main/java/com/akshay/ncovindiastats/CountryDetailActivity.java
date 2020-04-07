package com.akshay.ncovindiastats;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.akshay.ncovindiastats.Models.CountryData.Example1;
import com.akshay.ncovindiastats.Network.AllCountries.AllCountriesAPIClient;
import com.akshay.ncovindiastats.Network.AllCountries.AllCountriesAPIInterface;

import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryDetailActivity extends AppCompatActivity {

    private TextView tv_countryName, tv_latitude, tv_longitude, tv_population, tv_confirmed, tv_dead, tv_latest_confirmed, tv_latest_recovered,
            tv_latest_critical, tv_latest_dead, tv_recoveryrate, tv_deathrate;
    private Button btn_viewtimeline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        final String country_code = getIntent().getStringExtra("countrycode");

        tv_countryName = findViewById(R.id.tv_countryName);
        tv_latitude = findViewById(R.id.tv_latitude);
        tv_longitude = findViewById(R.id.tv_longitude);
        tv_population = findViewById(R.id.tv_population);
        tv_confirmed = findViewById(R.id.tv_confirmed);
        tv_dead = findViewById(R.id.tv_dead);
        tv_latest_confirmed = findViewById(R.id.tv_latest_confirmed);
        tv_latest_recovered = findViewById(R.id.tv_latest_recovered);
        tv_latest_critical = findViewById(R.id.tv_latest_critical);
        tv_latest_dead = findViewById(R.id.tv_latest_dead);
        tv_recoveryrate = findViewById(R.id.tv_recoveryrate);
        tv_deathrate = findViewById(R.id.tv_deathrate);
        btn_viewtimeline = findViewById(R.id.btn_viewtimeline);

        btn_viewtimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CountryDetailActivity.this, CountryTimelineActivity.class);
                i.putExtra("country_code", country_code);
                startActivity(i);
            }
        });

        getCountryDetails(country_code);

    }

    private void getCountryDetails(String country_code) {
        final ProgressDialog dialog = ProgressDialog.show(CountryDetailActivity.this, "", "Loading. Please wait....", true);

        final AllCountriesAPIInterface allCountriesAPIInterface = AllCountriesAPIClient.getClient().create(AllCountriesAPIInterface.class);

        Call<Example1> call = allCountriesAPIInterface.getCountryData(country_code);
        call.enqueue(new Callback<Example1>() {
            @Override
            public void onResponse(Call<Example1> call, Response<Example1> response) {
                Example1 mydata = response.body();

                DecimalFormat formatter = new DecimalFormat("#,###");
                String popu = "";

                if (mydata.getData().getPopulation() == null)
                    popu = "NA";
                else {
                    int population = mydata.getData().getPopulation();
                    popu = formatter.format(population);
                }

                int c = mydata.getData().getLatestData().getConfirmed();
                int r = mydata.getData().getLatestData().getRecovered();
                int cr = mydata.getData().getLatestData().getCritical();
                int d = mydata.getData().getLatestData().getDeaths();


                String lc = formatter.format(c);
                String lr = formatter.format(r);
                String lcr = formatter.format(cr);
                String ld = formatter.format(d);

                String rr = String.format("%.2f", mydata.getData().getLatestData().getCalculated().getRecoveryRate());
                String dr = String.format("%.2f", mydata.getData().getLatestData().getCalculated().getDeathRate());

                tv_countryName.setText(mydata.getData().getName().toUpperCase());
                tv_latitude.setText(mydata.getData().getCoordinates().getLatitude() + "\nLatitude");
                tv_longitude.setText(mydata.getData().getCoordinates().getLongitude() + "\nLongitude");
                tv_population.setText("Population:-   " + popu);
                tv_confirmed.setText(mydata.getData().getToday().getConfirmed() + "\nConfirmed");
                tv_dead.setText(mydata.getData().getToday().getDeaths() + "\nDead");
                tv_latest_confirmed.setText(lc + "\nConfirmed");
                tv_latest_recovered.setText(lr + "\nRecovered");
                tv_latest_critical.setText(lcr + "\nCritical");
                tv_latest_dead.setText(ld + "\nDead");
                tv_recoveryrate.setText(rr + " %\nRecovery Rate");
                tv_deathrate.setText(dr + " %\nDeath Rate");
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<Example1> call, Throwable t) {
                Toast.makeText(CountryDetailActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
    }
}
