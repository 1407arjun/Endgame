package com.teamblunder.endgame.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamblunder.endgame.R;
import com.teamblunder.endgame.ResultActivity;
import com.teamblunder.endgame.api.ImageAPI;
import com.teamblunder.endgame.models.ImageJSONPlaceholder;
import com.teamblunder.endgame.models.KeywordAdapter;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KeywordFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keyword, container, false);
        RecyclerView keywordRecyclerView = view.findViewById(R.id.keywordRecyclerView);

        /*KeywordAdapter keywordAdapter = new KeywordAdapter(imagesList,getActivity());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        keywordRecyclerView.setLayoutManager(layoutManager);
        keywordRecyclerView.setAdapter(keywordAdapter);*/

        return view;
    }
}