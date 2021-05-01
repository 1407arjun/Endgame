package com.teamblunder.endgame.models;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import com.teamblunder.endgame.R;
import com.teamblunder.endgame.api.API;
import com.teamblunder.endgame.api.ImageAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KeywordAdapter extends RecyclerView.Adapter<KeywordAdapter.RecyclerViewHolder> {

    private ArrayList<String> arrayList;
    private final Context context;

    public KeywordAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout_keywords, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.textView.setText(arrayList.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit2 = new Retrofit.Builder()
                        .baseUrl(ImageAPI.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ProgressDialog progress = new ProgressDialog(context);
                progress.setMessage("Retrieving data");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setIndeterminate(true);
                progress.setCancelable(false);
                progress.show();
                ImageAPI myApi = retrofit2.create(ImageAPI.class);
                String name = arrayList.get(position);
                Call<ImageJSONPlaceholder> call2 = myApi.getResult(name, "isch", "0", "eba15b7a6b9ecd04083e7acc5a05eedcf8f727afd4d6de755e4f0b78ee6185a1");
                call2.enqueue(new Callback<ImageJSONPlaceholder>() {
                    @Override
                    public void onResponse(Call<ImageJSONPlaceholder> call2, Response<ImageJSONPlaceholder> response2) {
                        ImageJSONPlaceholder searchResults = response2.body();
                        Log.i("serachxex", "xxxx");
                        if (searchResults.getSuggestedSearches().get(0).getThumbnail() != null) {
                            Picasso.with(context).load(searchResults.getSuggestedSearches().get(0).getThumbnail())
                                    .placeholder(R.drawable.ic_placeholder)
                                    .error(R.drawable.ic_placeholder)
                                    .into(holder.imageView);
                        }
                        progress.dismiss();
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<ImageJSONPlaceholder> call2, Throwable t2) {
                        progress.dismiss();
                        Toast.makeText(context, "Couldn't fetch data", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Retrofit retrofit2 = new Retrofit.Builder()
                        .baseUrl(API.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ProgressDialog progress = new ProgressDialog(context);
                progress.setMessage("Retrieving data");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setIndeterminate(true);
                progress.setCancelable(false);
                progress.show();
                API api = retrofit2.create(API.class);
                String name = arrayList.get(position);
                Call<ArrayList<JSONProcessActivity>> call = api.getResult("old-smoke-4544", name);
                call.enqueue(new Callback<ArrayList<JSONProcessActivity>>() {
                    @Override
                    public void onResponse(Call<ArrayList<JSONProcessActivity>> call, Response<ArrayList<JSONProcessActivity>> response) {
                        ArrayList<JSONProcessActivity> searchResults = response.body();
                        for (int i=0; i<searchResults.size(); i++){
                            if (searchResults.get(i).getSource().equals("Poly")) {
                                String url = searchResults.get(i).getGltfUrl();
                                Intent sceneViewerIntent = new Intent(Intent.ACTION_VIEW);
                                sceneViewerIntent.setData(Uri.parse("https://arvr.google.com/scene-viewer/1.0?file=" + url));
                                sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox");
                                context.startActivity(sceneViewerIntent);
                                break;
                            }
                        }
                        progress.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<JSONProcessActivity>> call, Throwable t) {
                        progress.dismiss();
                        Snackbar snackbar = Snackbar.make(v, "Couldn't fetch data", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                });
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;
        private CardView cardView;

        public RecyclerViewHolder(@NonNull View view) {
            super(view);
            textView = view.findViewById(R.id.textView);
            imageView = view.findViewById(R.id.imageView);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}
