package ir.mtapps.weatherlib.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;

import ir.mtapps.weatherlib.model.value.MoonPhase;

/**
 * <p>All parameters that supported by providers:</p>
 * <br>
 * <table  style="width: 100%; border-collapse: collapse; border: 1px solid gray">
 * <thead>
 * <tr>
 * <th>Params</th>
 * <th>AccuWeather</th>
 * <th>DarkSky</th>
 * <th>Open Weather</th>
 * <th>Weatherbit</th>
 * <th>World Weather Online</th>
 * </tr>
 * </thead>
 * <tbody>
 * <tr>
 * <td style="width: 16.6667%; text-align: center;">Sunrise</td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * </tr>
 * <tr>
 * <td style="width: 16.6667%; text-align: center;">Sunset</td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * </tr>
 * <tr>
 * <td style="width: 16.6667%; text-align: center;">Moonrise</td>
 * <td style="width: 16.6667%; text-align: center;">✔</td>
 * <td style="width: 16.6667%; text-align: center;">NO (return null)</td>
 * <td style="width: 16.6667%; text-align: center;">NO (return null)</td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * </tr>
 * <tr>
 * <td style="width: 16.6667%; text-align: center;">Moonset</td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * <td style="width: 16.6667%; text-align: center;">NO (return null)<br></td>
 * <td style="width: 16.6667%; text-align: center;">NO (return null)<br></td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * </tr>
 * <tr>
 * <td style="width: 16.6667%; text-align: center;">Moon phase</td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * <td style="width: 16.6667%; text-align: center;">NO (return null)</td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * <td style="width: 16.6667%; text-align: center;">✔<br></td>
 * </tr>
 * </tbody>
 * </table>
 */
public class Astronomy implements Parcelable {

    private ir.mtapps.weatherlib.model.value.Astronomy sun;
    private ir.mtapps.weatherlib.model.value.Astronomy moon;
    private MoonPhase moonPhase;

    private Astronomy() {}

    protected Astronomy(Parcel in) {
        sun = (ir.mtapps.weatherlib.model.value.Astronomy) in.readValue(Astronomy.class.getClassLoader());
        moon = (ir.mtapps.weatherlib.model.value.Astronomy) in.readValue(Astronomy.class.getClassLoader());
        moonPhase = (MoonPhase) in.readValue(MoonPhase.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(sun);
        dest.writeValue(moon);
        dest.writeValue(moonPhase);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Astronomy> CREATOR = new Parcelable.Creator<Astronomy>() {
        @Override
        public Astronomy createFromParcel(Parcel in) {
            return new Astronomy(in);
        }

        @Override
        public Astronomy[] newArray(int size) {
            return new Astronomy[size];
        }
    };

    public static class Builder {

        private final Astronomy astronomy;

        public Builder() {
            astronomy = new Astronomy();
        }

        public Builder setSunAstronomy(long rise, long set) {
            astronomy.sun = new ir.mtapps.weatherlib.model.value.Astronomy(rise, set);
            return this;
        }

        public Builder setMoonAstronomy(long rise, long set) {
            astronomy.moon = new ir.mtapps.weatherlib.model.value.Astronomy(rise, set);
            return this;
        }

        public Builder setMoonPhase(float moonPhase) {
            astronomy.moonPhase = new MoonPhase(moonPhase);
            return this;
        }

        public Astronomy build() {
            return astronomy;
        }
    }


    // *********************************************************************************************
    // *                                    Getter methods                                         *
    // *********************************************************************************************

    public ir.mtapps.weatherlib.model.value.Astronomy sun() {
        return sun;
    }

    public ir.mtapps.weatherlib.model.value.Astronomy moon() {
        return moon;
    }

    public MoonPhase moonPhase() {
        return moonPhase;
    }

    @NonNull
    @Override
    public String toString() {

        SimpleDateFormat format = new SimpleDateFormat("hh:mm a", Locale.getDefault());

        return "Astronomy: {"
                + "\n\t" + "sunrise: " + (sun == null ? "--" : format.format(new Date(sun.getRiseTime())))
                + "\n\t" + "sunset: " + (sun == null ? "--" : format.format(new Date(sun.getSetTime())))
                + "\n\t" + "moonrise: " + (moon == null ? "--" : format.format(new Date(moon.getRiseTime())))
                + "\n\t" + "moonset: " + (moon == null ? "--" : format.format(new Date(moon.getSetTime())))
                + "\n\t" + "moonPhase: " + (moonPhase == null ? "--" : moonPhase.getPhase())
                + "\n}";

    }
}
