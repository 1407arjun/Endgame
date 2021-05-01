package com.teamblunder.endgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.teamblunder.endgame.api.ImageAPI;
import com.teamblunder.endgame.fragments.KeywordFragment;
import com.teamblunder.endgame.fragments.SummaryFragment;
import com.teamblunder.endgame.models.ImageJSONPlaceholder;
import com.teamblunder.endgame.models.KeywordAdapter;
import com.teamblunder.endgame.models.TabAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultActivity extends AppCompatActivity {

    ArrayList<String> resultList = new ArrayList<>();
    static ArrayList<String> keywordsList = new ArrayList<>();
    static ArrayList<Map<String, String>> imagesList = new ArrayList<>();
    static String result, text;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        text = intent.getStringExtra("text");
        if (!text.equals("")) {
            resultList.clear();
            keywordsList.clear();
            //Do something with the text
            if (!result.equals("")) {
                SummaryFragment summaryFragment = new SummaryFragment();
                Bundle args = new Bundle();
                args.putString("summary", result);
                summaryFragment.setArguments(args);
            }
        }

        progress = new ProgressDialog(this);
        progress.setMessage("Retrieving data");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);

        TabLayout tabLayout= findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);

        final TabAdapter adapter = new TabAdapter(this , getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    public void getData(){

        AnalyzeTextTask analyzeTextTask = new AnalyzeTextTask();
        try {
            String urlEncoder = URLEncoder.encode(text, "UTF-8");
            analyzeTextTask.execute("https://de6c984099e4.ngrok.io/" + urlEncoder);
            progress.show();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        progress.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ImageAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ImageAPI myApi = retrofit.create(ImageAPI.class);
        for (int i = 0; i < keywordsList.size(); i++){
            String name = keywordsList.get(i);
            Call<ImageJSONPlaceholder> call = myApi.getResult(name, "isch", "0", "eba15b7a6b9ecd04083e7acc5a05eedcf8f727afd4d6de755e4f0b78ee6185a1");
            call.enqueue(new Callback<ImageJSONPlaceholder>() {
                @Override
                public void onResponse(Call<ImageJSONPlaceholder> call, Response<ImageJSONPlaceholder> response) {
                    ImageJSONPlaceholder searchResults = response.body();
                    if (searchResults.getSuggestedSearches().get(0).getThumbnail() != null){
                        Map<String, String> entry = new HashMap<>();
                        entry.put("name", name);
                        entry.put("thumbnail", searchResults.getSuggestedSearches().get(0).getThumbnail());
                        imagesList.add(entry);
                    }
                }

                @Override
                public void onFailure(Call<ImageJSONPlaceholder> call, Throwable t) {
                    Toast.makeText(ResultActivity.this, "Couldn't fetch data", Toast.LENGTH_LONG).show();
                }
            });
        }
        progress.dismiss();
    }

    class AnalyzeTextTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        keywordsList.add((String) jsonArray.get(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            progress.dismiss();
        }
    }
}