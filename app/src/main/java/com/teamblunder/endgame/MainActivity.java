package com.teamblunder.endgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    //ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

    }

        /*AnalyzeTextTask analyzeTextTask = new AnalyzeTextTask();
        try {
            String urlEncoder = URLEncoder.encode("The Indian independence movement was a series of historic events with the ultimate aim of ending the British rule in India. The movement spanned from 1857 to 1947.[1] The first nationalistic revolutionary movement for Indian independence emerged from Bengal.[2] It later took root in the newly formed Indian National Congress with prominent moderate leaders seeking only their fundamental right to appear for Indian Civil Service examinations in British India, as well as more rights", "UTF-8");
            analyzeTextTask.execute("https://c666bd4638fa.ngrok.io/" + urlEncoder);
            progress = new ProgressDialog(this);
            progress.setMessage("Retrieving data");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.setCancelable(false);
            progress.show();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/

        /*AnalyzeTextTask newanalyzeTextTask = new AnalyzeTextTask();
        try {
            String urlEncoder = URLEncoder.encode("The Indian independence movement was a series of historic events with the ultimate aim of ending the British rule in India. The movement spanned from 1857 to 1947.[1] The first nationalistic revolutionary movement for Indian independence emerged from Bengal.[2] It later took root in the newly formed Indian National Congress with prominent moderate leaders seeking only their fundamental right to appear for Indian Civil Service examinations in British India, as well as more rights", "UTF-8");
            newanalyzeTextTask.execute("https://405ce5e2358c.ngrok.io/" + urlEncoder);
            progress = new ProgressDialog(this);
            progress.setMessage("Retrieving data");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.setCancelable(false);
            progress.show();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public class AnalyzeTextTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
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
            progress.dismiss();
            if (s != null) {
                Log.i("dataxx", s);
            }
        }
    }*/
}