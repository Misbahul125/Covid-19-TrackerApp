package com.example.covid_19tracker.api;

import com.google.gson.annotations.SerializedName;

public class CountryModel {

    @SerializedName("country")
    private String country;

    @SerializedName("updated")
    private String updated;

    @SerializedName("cases")
    private String cases;

    @SerializedName("todayCases")
    private String todayCases;

    @SerializedName("active")
    private String active;

    @SerializedName("recovered")
    private String recovered;

    @SerializedName("todayRecovered")
    private String todayRecovered;

    @SerializedName("deaths")
    private String deaths;

    @SerializedName("todayDeaths")
    private String todayDeaths;

    @SerializedName("tests")
    private String tests;

    public CountryModel(String country, String updated, String cases, String todayCases, String active, String recovered, String todayRecovered, String deaths, String todayDeaths, String tests) {
        this.country = country;
        this.updated = updated;
        this.cases = cases;
        this.todayCases = todayCases;
        this.active = active;
        this.recovered = recovered;
        this.todayRecovered = todayRecovered;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.tests = tests;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }
}
