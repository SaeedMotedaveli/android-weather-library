package ir.mtapps.weatherlib.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * <p>All parameters that supported by providers:</p>
 * <br>
 * <table style="width: 100%; border: 1px solid gray;">
 *     <thead>
 *         <tr>
 *             <th>Params</th>
 *             <th>AccuWeather</th>
 *             <th>DarkSky</th>
 *             <th>Open Weather</th>
 *             <th>Weatherbit</th>
 *             <th>World Weather Online</th>
 *         </tr>
 *     </thead>
 *     <tbody>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">City name</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">NO (return null)<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Country name</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">NO (return null)<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Coordinate (latitude and longitude)</td>
 *             <td style="width: 16.6667%; text-align: center;">✔</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *     </tbody>
 * </table>
 */
public class City implements Parcelable {

    private String name;
    private String country;

    private double latitude;
    private double longitude;

    private City() {}

    protected City(Parcel in) {
        name = in.readString();
        country = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(country);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public static class Builder {

        private final City city;

        public Builder() {
            city = new City();
        }

        public Builder setName(String name) {
            city.name = name;
            return this;
        }

        public Builder setCountry(String country) {
            city.country = country;
            return this;
        }

        public Builder setCoordinate(double latitude, double longitude) {
            city.latitude = latitude;
            city.longitude = longitude;
            return this;
        }

        public City build() {
            return city;
        }

    }

    // *********************************************************************************************
    // *                                    Getter methods                                         *
    // *********************************************************************************************

    /**
     * Get location name.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get country name.
     * @return String
     */
    public String getCountry() {
        return country;
    }

    /**
     * Get latitude.
     * @return double
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Get longitude.
     * @return double
     */
    public double getLongitude() {
        return longitude;
    }

    @NonNull
    @Override
    public String toString() {
        return "City {"
                + "\n\t" + "name: " + (name == null ? "---" : name)
                + "\n\t" + "country: " + (country == null ? "---" : country)
                + "\n\t" + "coordinate: " + latitude + ", " + longitude
                + "\n}";
    }
}