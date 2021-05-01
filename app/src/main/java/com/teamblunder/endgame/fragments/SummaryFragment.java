package com.teamblunder.endgame.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamblunder.endgame.R;

import java.util.ArrayList;


public class SummaryFragment extends Fragment {

    ProgressDialog progress;
    ArrayList<String> summaryList = new ArrayList<>();

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
            //Do something with the text
        }
    }
}