package com.example.countriesapp.model;

import com.google.gson.annotations.SerializedName;

public class CountryModel {
    @SerializedName("name") // the names of the variables in the actual JSON file
    private String countryName;

    @SerializedName("capital") // the names of the variables in the actual JSON file
    private String capital;

    @SerializedName("flagPNG") // the names of the variables in the actual JSON file
    private String flagUrl;

    public CountryModel(String countryName, String capital, String flagUrl) {
        this.countryName = countryName;
        this.capital = capital;
        this.flagUrl = flagUrl;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCapital() {
        return capital;
    }

    public String getFlagUrl() {
        return flagUrl;
    }
}
