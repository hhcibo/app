package com.cibo.android.cibo;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;

import com.cibo.android.cibo.model.PastTravel;

import org.json.JSONArray;
import org.json.JSONObject;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.util.ArrayListSupplier;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.item_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.history_swipe_refresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new RetrieveFeedTask().execute();
            }
        });

        client = new OkHttpClient();

        new RetrieveFeedTask().execute();
    }

    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, List<PastTravel>> {

        private Exception exception;

        @Override
        protected void onPreExecute() {
            swipeRefresh.setRefreshing(true);
        }

        protected List<PastTravel> doInBackground(String... urls) {
            try {
                String resp = run("http://207.154.234.13/mobile");
                Log.d("CIBO", resp);

                JSONObject myJson = new JSONObject(run("http://207.154.234.13/mobile"));
                JSONArray historicData = myJson.getJSONArray("pastTravels");

                List<PastTravel> travelList = new ArrayList<PastTravel>();
                for (int i = 0; i < historicData.length(); i++) {
                    JSONObject pastTravel = historicData.getJSONObject(i);

                    String fromStation = pastTravel.getString("fromStation");
                    String startTime = pastTravel.getString("startTime");
                    String endStation = pastTravel.getString("endStation");
                    String endTime = pastTravel.getString("endTime");
                    String cost = pastTravel.getString("cost");

                    travelList.add(new PastTravel(
                            fromStation,
                            startTime,
                            endStation,
                            endTime,
                            cost
                    ));
                }
                return travelList;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }

        protected void onPostExecute(List<PastTravel> feed) {
            recyclerView.setAdapter(new ItemRecyclerViewAdapter(feed));
            swipeRefresh.setRefreshing(false);
        }
    }

}
