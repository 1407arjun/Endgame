package com.teamblunder.endgame.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ImageJSONPlaceholder implements Serializable
{

    @SerializedName("search_metadata")
    @Expose
    private SearchMetadata searchMetadata;
    @SerializedName("search_parameters")
    @Expose
    private SearchParameters searchParameters;
    @SerializedName("search_information")
    @Expose
    private SearchInformation searchInformation;
    @SerializedName("suggested_searches")
    @Expose
    private ArrayList<SuggestedSearch> suggestedSearches = null;
    private final static long serialVersionUID = -7770780447626989150L;

    public SearchMetadata getSearchMetadata() {
        return searchMetadata;
    }

    public void setSearchMetadata(SearchMetadata searchMetadata) {
        this.searchMetadata = searchMetadata;
    }

    public SearchParameters getSearchParameters() {
        return searchParameters;
    }

    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters = searchParameters;
    }

    public SearchInformation getSearchInformation() {
        return searchInformation;
    }

    public void setSearchInformation(SearchInformation searchInformation) {
        this.searchInformation = searchInformation;
    }

    public ArrayList<SuggestedSearch> getSuggestedSearches() {
        return suggestedSearches;
    }

    public void setSuggestedSearches(ArrayList<SuggestedSearch> suggestedSearches) {
        this.suggestedSearches = suggestedSearches;
    }

}


class SearchInformation implements Serializable {

    @SerializedName("image_results_state")
    @Expose
    private String imageResultsState;
    @SerializedName("query_displayed")
    @Expose
    private String queryDisplayed;
    private final static long serialVersionUID = 6744676022214649272L;

    public String getImageResultsState() {
        return imageResultsState;
    }

    public void setImageResultsState(String imageResultsState) {
        this.imageResultsState = imageResultsState;
    }

    public String getQueryDisplayed() {
        return queryDisplayed;
    }

    public void setQueryDisplayed(String queryDisplayed) {
        this.queryDisplayed = queryDisplayed;
    }

}

class SearchMetadata implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("json_endpoint")
    @Expose
    private String jsonEndpoint;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("processed_at")
    @Expose
    private String processedAt;
    @SerializedName("google_url")
    @Expose
    private String googleUrl;
    @SerializedName("raw_html_file")
    @Expose
    private String rawHtmlFile;
    @SerializedName("total_time_taken")
    @Expose
    private Double totalTimeTaken;
    private final static long serialVersionUID = -2160850566966204698L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJsonEndpoint() {
        return jsonEndpoint;
    }

    public void setJsonEndpoint(String jsonEndpoint) {
        this.jsonEndpoint = jsonEndpoint;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(String processedAt) {
        this.processedAt = processedAt;
    }

    public String getGoogleUrl() {
        return googleUrl;
    }

    public void setGoogleUrl(String googleUrl) {
        this.googleUrl = googleUrl;
    }

    public String getRawHtmlFile() {
        return rawHtmlFile;
    }

    public void setRawHtmlFile(String rawHtmlFile) {
        this.rawHtmlFile = rawHtmlFile;
    }

    public Double getTotalTimeTaken() {
        return totalTimeTaken;
    }

    public void setTotalTimeTaken(Double totalTimeTaken) {
        this.totalTimeTaken = totalTimeTaken;
    }

}

class SearchParameters implements Serializable {

    @SerializedName("engine")
    @Expose
    private String engine;
    @SerializedName("q")
    @Expose
    private String q;
    @SerializedName("google_domain")
    @Expose
    private String googleDomain;
    @SerializedName("ijn")
    @Expose
    private String ijn;
    @SerializedName("device")
    @Expose
    private String device;
    @SerializedName("tbm")
    @Expose
    private String tbm;
    private final static long serialVersionUID = 2870374509724671456L;

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getGoogleDomain() {
        return googleDomain;
    }

    public void setGoogleDomain(String googleDomain) {
        this.googleDomain = googleDomain;
    }

    public String getIjn() {
        return ijn;
    }

    public void setIjn(String ijn) {
        this.ijn = ijn;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getTbm() {
        return tbm;
    }

    public void setTbm(String tbm) {
        this.tbm = tbm;
    }

}

class SuggestedSearch implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("chips")
    @Expose
    private String chips;
    @SerializedName("serpapi_link")
    @Expose
    private String serpapiLink;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    private final static long serialVersionUID = 1683326428284473710L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getChips() {
        return chips;
    }

    public void setChips(String chips) {
        this.chips = chips;
    }

    public String getSerpapiLink() {
        return serpapiLink;
    }

    public void setSerpapiLink(String serpapiLink) {
        this.serpapiLink = serpapiLink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
