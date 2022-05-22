package ir.mtapps.weatherlib.provider.aeris_weather;

import com.google.gson.annotations.SerializedName;

public class JsonValidityModel {

    @SerializedName("success")
    public boolean success;

    @SerializedName("error")
    public String error;

}
