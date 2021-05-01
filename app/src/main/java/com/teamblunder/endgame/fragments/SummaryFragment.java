package com.teamblunder.endgame.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamblunder.endgame.R;

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


public class SummaryFragment extends Fragment {

    ProgressDialog progress;
    ArrayList<String> summaryList = new ArrayList<>();
    String result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        Bundle args = getArguments();
        String text = args.getString("inputText");

        progress = new ProgressDialog(getActivity());
        progress.setMessage("Retrieving data");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);

        summarizeText(text);

        return view;
    }

    public void summarizeText(String inputText){
        if (!inputText.equals("")) {
            progress.show();
            summaryList.clear();
            AnalyzeTextTask analyzeTextTask = new AnalyzeTextTask();
            try {
                String urlEncoder = URLEncoder.encode(inputText, "UTF-8");
                analyzeTextTask.execute("https://40a430dfd6bc.ngrok.io/" + urlEncoder);
                progress.show();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
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
                        summaryList.add((String) jsonArray.get(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            progress.dismiss();
        }
    }

}