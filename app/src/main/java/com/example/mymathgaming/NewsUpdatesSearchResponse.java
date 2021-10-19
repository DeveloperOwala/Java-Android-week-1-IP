
package com.example.mymathgaming;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsUpdatesSearchResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("articles")
    @Expose
    private List<com.example.mymathgaming.Article> articles = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public NewsUpdatesSearchResponse() {
    }

    /**
     * 
     * @param totalResults
     * @param articles
     * @param status
     */
    public NewsUpdatesSearchResponse(String status, Integer totalResults, List<com.example.mymathgaming.Article> articles) {
        super();
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<com.example.mymathgaming.Article> getArticles() {
        return articles;
    }

    public void setArticles(List<com.example.mymathgaming.Article> articles) {
        this.articles = articles;
    }

}
