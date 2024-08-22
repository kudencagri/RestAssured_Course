package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Place {
    private String placename;
    private String longitude;
    private String state;
    private String stateabbreviation;
    private String latitude;

    @JsonProperty("place name")
    public void setPlacename(String placename) {
        this.placename = placename;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("state abbreviation")
    public void stateabbreviation(String stateabbreviation) {
        this.stateabbreviation = stateabbreviation;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPlacename() {
        return placename;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getState() {
        return state;
    }

    public String getStateabbrevation() {
        return stateabbreviation;
    }

    public String getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "Place{" +
                "placename='" + placename + '\'' +
                ", longitude='" + longitude + '\'' +
                ", state='" + state + '\'' +
                ", stateabbreviation='" + stateabbreviation + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
