package ir.mtapps.weatherlib.provider.world_weather_online;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class ErrorModel {

    @SerializedName("data")
    Data data;

    static class Data {

        @SerializedName("error")
        List<Error> error = null;

        static class Error {
            @SerializedName("msg")
            String message;
        }
    }

}
