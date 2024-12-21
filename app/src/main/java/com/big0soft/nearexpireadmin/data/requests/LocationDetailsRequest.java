package com.big0soft.nearexpireadmin.data.requests;

import com.big0soft.resource.data.FromJson;
import com.big0soft.resource.data.ToJson;
import com.big0soft.resource.gson.FromJsonImpl;
import com.big0soft.resource.gson.ToJsonImpl;

public class LocationDetailsRequest implements ToJson, FromJson {
   private int id;
   private String CountryName;
   private String CityName;
   private String StreetName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getStreetName() {
        return StreetName;
    }

    public void setStreetName(String streetName) {
        StreetName = streetName;
    }

    @Override
    public FromJson fromJson(String data) {
        return new FromJsonImpl<>(getClass()).fromJson(data);
    }

    @Override
    public String toJson() {
        return new ToJsonImpl<>(this).toJson();
    }
}
