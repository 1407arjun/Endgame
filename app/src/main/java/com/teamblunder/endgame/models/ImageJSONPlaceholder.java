package com.teamblunder.endgame.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ImageJSONPlaceholder {

    @SerializedName("suggested_searches")
    @Expose
    private ArrayList<SuggestedSearch> suggestedSearches = null;
    private final static long serialVersionUID = -7770780447626989150L;

    public ArrayList<SuggestedSearch> getSuggestedSearches() {
        return suggestedSearches;
    }

    public void setSuggestedSearches(ArrayList<SuggestedSearch> suggestedSearches) {
        this.suggestedSearches = suggestedSearches;
    }

}