package com.teamblunder.endgame.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teamblunder.endgame.R;
import com.teamblunder.endgame.ResultActivity;
import com.teamblunder.endgame.api.ImageAPI;
import com.teamblunder.endgame.models.ImageJSONPlaceholder;
import com.teamblunder.endgame.models.KeywordAdapter;

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

public class KeywordFragment extends Fragment {

    String result;
    ArrayList<String> keywordsList = new ArrayList<>();
    ArrayList<Map<String, String>> imagesList = new ArrayList<>();
    ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keyword, container, false);
        RecyclerView keywordRecyclerView = view.findViewById(R.id.keywordRecyclerView);

        Bundle bundle = getArguments();
        String text = bundle.getString("inputText");

        progress = new ProgressDialog(getActivity());
        progress.setMessage("Retrieving data");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);

        getData(text);
        getImageData();

        KeywordAdapter keywordAdapter = new KeywordAdapter(imagesList, getActivity());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        keywordRecyclerView.setLayoutManager(layoutManager);
        keywordRecyclerView.setAdapter(keywordAdapter);

        return view;
    }

    public void getData(String inputText){
        keywordsList.clear();
        AnalyzeTextTask analyzeTextTask = new AnalyzeTextTask();
        try {
            String urlEncoder = URLEncoder.encode(inputText, "UTF-8");
            analyzeTextTask.execute("https://40a430dfd6bc.ngrok.io/" + urlEncoder);
            progress.show();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void getImageData(){
        imagesList.clear();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ImageAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        progress.show();
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
                    Toast.makeText(getActivity(), "Couldn't fetch data", Toast.LENGTH_LONG).show();
                }
            });
        }
        progress.dismiss();
    }

    public class AnalyzeTextTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            result = "";
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