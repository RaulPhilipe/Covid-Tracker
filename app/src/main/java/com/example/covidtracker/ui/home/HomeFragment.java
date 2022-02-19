package com.example.covidtracker.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covidtracker.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class HomeFragment extends Fragment {

    private TextView TotalConfirmed, TotalDeath, TotalRecovered, TextvLastUpdate;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        TotalConfirmed = root.findViewById(R.id.TotalConfirmed);
        TotalDeath = root.findViewById(R.id.TotalDeath);
        TotalRecovered = root.findViewById(R.id.TotalRecovered);
        progressBar = root.findViewById(R.id.progress_home);
        TextvLastUpdate = root.findViewById(R.id.TextvLastUpdate);

        getData();

        return root;
    }

    private String getDate(long milliSecond){
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
        SimpleDateFormat timeFormatter = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss aaa", new Locale("pt", "BR"));

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSecond);

        return timeFormatter.format(calendar.getTime());
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        String url = "https://disease.sh/v3/covid-19/all";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());

                    TotalConfirmed.setText(jsonObject.getString("cases"));
                    TotalDeath.setText(jsonObject.getString("deaths"));
                    TotalRecovered.setText(jsonObject.getString("recovered"));
                    TextvLastUpdate.setText("Ultima Atualização"+"\n"+getDate(jsonObject.getLong("updated")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Log.d("Error Response", error.toString());
            }
        });

        queue.add(stringRequest);
    }

}