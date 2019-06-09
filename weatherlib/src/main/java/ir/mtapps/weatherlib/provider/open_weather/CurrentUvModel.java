package ir.mtapps.weatherlib.provider.open_weather;

import com.google.gson.annotations.SerializedName;

class CurrentUvModel {

    @SerializedName("cod")
    int cod = 200;

    @SerializedName("message")
    String message;

//    @SerializedName("lat")
//    public float lat;

//    @SerializedName("lon")
//    public float lon;

//    @SerializedName("date_iso")
//    public String dateIso;

//    @SerializedName("date")
//    public int date;

    @SerializedName("value")
    float value;
}
