package ir.mtapps.weatherlib.provider.open_weather;

import com.google.gson.annotations.SerializedName;

class ErrorModel {

    @SerializedName("cod")
    int cod;

    @SerializedName("message")
    String message;

}
