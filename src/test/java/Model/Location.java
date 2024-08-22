package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Location {
    private String postcode;
    private String country;
    private String countryabbreviation;
    private ArrayList<Place> places;

    @JsonProperty("post code") // sitedeki parametre bu  oyüzden böyle arıyor *** sadece Setlere atılır çüknü bilgi atarken yapılıyor
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }


    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("country abbreviation")
    public void setCountryabbreviation(String countryabbreviation) {
        this.countryabbreviation = countryabbreviation;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryabbreviation() {
        return countryabbreviation;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }
}
