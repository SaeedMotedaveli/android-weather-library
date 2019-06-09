package ir.mtapps.weatherlib.provider.waqi;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ir.mtapps.weatherlib.Util;
import ir.mtapps.weatherlib.model.AirQuality;

class Model {

    @SerializedName("status")
    String status;
    
    @SerializedName("data")
    Data data;

    class Data {

        @SerializedName("aqi")
        float aqi;
        
        @SerializedName("idx")
        float idx;
        
//        @SerializedName("attributions")
//        List<Attribution> attributions = null;
        
        @SerializedName("city")
        City city;
        
        @SerializedName("dominentpol")
        String dominentpol;
        
        @SerializedName("iaqi")
        Iaqi iaqi;
        
        @SerializedName("time")
        Time time;

        class Attribution {

            @SerializedName("url")
            String url;
            
            @SerializedName("name")
            String name;

        }

        class City {

            @SerializedName("geo")
            List<Double> geo = null;
            
            @SerializedName("name")
            String name;
            
            @SerializedName("url")
            String url;

        }

        class Iaqi {

            @SerializedName("co")
            Co co;
            
            @SerializedName("h")
            H h;
            
            @SerializedName("no2")
            No2 no2;
            
            @SerializedName("o3")
            O3 o3;
            
            @SerializedName("p")
            P p;
            
            @SerializedName("pm10")
            Pm10 pm10;
            
            @SerializedName("pm25")
            Pm25 pm25;
            
            @SerializedName("so2")
            So2 so2;
            
            @SerializedName("t")
            T t;

            class Co {

                @SerializedName("v")
                float v;

            }

            class H {

                @SerializedName("v")
                float v;

            }

            class No2 {

                @SerializedName("v")
                float v;

            }

            class O3 {

                @SerializedName("v")
                float v;

            }

            class P {

                @SerializedName("v")
                float v;

            }

            class Pm10 {

                @SerializedName("v")
                float v;

            }

            class Pm25 {

                @SerializedName("v")
                float v;

            }

            class So2 {

                @SerializedName("v")
                float v;

            }

            class T {

                @SerializedName("v")
                float v;

            }

        }

        class Time {

//            @SerializedName("s")
//            String s;
            
//            @SerializedName("tz")
//            String tz;
            
            @SerializedName("v")
            long v;

        }

    }

    boolean isDataValid() {
        return status.contentEquals("ok");
    }

    AirQuality createAQModel(Context context, String language) {

        String riskLevel = Util.getAirQualityRiskLevel(context, language, data.aqi);
        String healthMessage = Util.getAirQualityHealthMessage(context, language, data.aqi);

        AirQuality.Builder builder = new AirQuality.Builder()
                .setIndex(data.aqi, riskLevel, healthMessage);

        if (data.iaqi.pm25 != null) {
            builder.setPm25(data.iaqi.pm25.v);
        }

        if (data.iaqi.pm10 != null) {
            builder.setPm10(data.iaqi.pm10.v);
        }

        if (data.iaqi.o3 != null) {
            builder.setO3(data.iaqi.o3.v);
        }

        if (data.iaqi.no2 != null) {
            builder.setNo2(data.iaqi.no2.v);
        }

        if (data.iaqi.so2 != null) {
            builder.setSo2(data.iaqi.so2.v);
        }

        if (data.iaqi.co != null) {
            builder.setCo(data.iaqi.co.v);
        }

        return builder.build();
    }
}