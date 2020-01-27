package com.encrypts.goofyturtle.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories {
    public String menuName;
    @SerializedName("CatagoryName")
    @Expose
    private String catagoryName;
    @SerializedName("SiteUrl")
    @Expose
    private String siteUrl;
    @SerializedName("child")
    @Expose
    private List<SubCategory> child = null;

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public List<SubCategory> getChild() {
        return child;
    }

    public void setChild(List<SubCategory> child) {
        this.child = child;
    }

}


