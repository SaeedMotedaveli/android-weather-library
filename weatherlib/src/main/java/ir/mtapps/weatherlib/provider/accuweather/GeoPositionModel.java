package ir.mtapps.weatherlib.provider.accuweather;

import com.google.gson.annotations.SerializedName;

import ir.mtapps.weatherlib.model.City;

class GeoPositionModel {

//    @SerializedName("Version")
//    int version;
    
    @SerializedName("Key")
    String key;
    
//    @SerializedName("Type")
//    String type;
    
//    @SerializedName("Rank")
//    int rank;
    
    @SerializedName("LocalizedName")
    String localizedName;
    
    @SerializedName("EnglishName")
    String englishName;
    
//    @SerializedName("PrimaryPostalCode")
//    String primaryPostalCode;
    
//    @SerializedName("Region")
//    Region region;
    
    @SerializedName("Country")
    Country country;
    
    @SerializedName("AdministrativeArea")
    AdministrativeArea administrativeArea;
    
//    @SerializedName("TimeZone")
//    TimeZone timeZone;
    
    @SerializedName("GeoPosition")
    GeoPosition geoPosition;
    
//    @SerializedName("IsAlias")
//    boolean isAlias;
    
//    @SerializedName("SupplementalAdminAreas")
//    List<Object> supplementalAdminAreas = null;
    
//    @SerializedName("DataSets")
//    List<Object> dataSets = null;

    static class Country {

        @SerializedName("ID")
        String iD;

        @SerializedName("LocalizedName")
        String localizedName;

        @SerializedName("EnglishName")
        String englishName;

    }

    static class AdministrativeArea {

        @SerializedName("ID")
        String iD;

        @SerializedName("LocalizedName")
        String localizedName;

        @SerializedName("EnglishName")
        String englishName;

        @SerializedName("Level")
        int level;

        @SerializedName("LocalizedType")
        String localizedType;

        @SerializedName("EnglishType")
        String englishType;

        @SerializedName("CountryID")
        String countryID;

    }

    static class GeoPosition {

        @SerializedName("Latitude")
        double latitude;

        @SerializedName("Longitude")
        double longitude;

//        @SerializedName("Elevation")
//        Elevation elevation;

    }

    City getCity() {

        return new City.Builder()
                .setName(localizedName)
                .setCountry(country.localizedName)
                .setCoordinate(geoPosition.latitude, geoPosition.longitude)
                .build();

    }
}
