package ir.mtapps.weatherlib.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import ir.mtapps.weatherlib.enums.WEATHER_ICON;
import ir.mtapps.weatherlib.model.value.Astronomy;
import ir.mtapps.weatherlib.model.value.CloudCover;
import ir.mtapps.weatherlib.model.value.Condition;
import ir.mtapps.weatherlib.model.value.Humidity;
import ir.mtapps.weatherlib.model.value.MoonPhase;
import ir.mtapps.weatherlib.model.value.Precipitation;
import ir.mtapps.weatherlib.model.value.Pressure;
import ir.mtapps.weatherlib.model.value.Temperature;
import ir.mtapps.weatherlib.model.value.TemperatureMinMax;
import ir.mtapps.weatherlib.model.value.UV;
import ir.mtapps.weatherlib.model.value.Visibility;
import ir.mtapps.weatherlib.model.value.Wind;

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
 *             <td style="width: 16.6667%; text-align: center;">Icon</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Description</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Temperature (min and max)</td>
 *             <td style="width: 16.6667%; text-align: center;">✔</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Apparent temperature (min and max)</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">NO (return null)</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">NO (return null)</td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Dewpoint</td>
 *             <td style="width: 16.6667%; text-align: center;">NO (return null)</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">NO (return null)</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Pressure</td>
 *             <td style="width: 16.6667%; text-align: center;">NO (return null)</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Humidity</td>
 *             <td style="width: 16.6667%; text-align: center;">NO (return null)<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Visibility</td>
 *             <td style="width: 16.6667%; text-align: center;">NO (return null)<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">NO (return null)</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Wind speed</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Wind direction</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Cloud cover</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Precipitation amount</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td rowspan="2" style="width: 16.6548%; text-align: center;">NO (return null)<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Precipitation probably</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">UV index</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">NO (return null)<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Sunrise</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td rowspan="2" style="width: 16.6548%; text-align: center;">NO (return null)<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Sunset</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Moonrise</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">NO (return null)<br></td>
 *             <td rowspan="2" style="width: 16.6548%; text-align: center;">NO (return null)<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔</td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Moonset</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">NO (return null)<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *         <tr>
 *             <td style="width: 16.6667%; text-align: center;">Moon phase</td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">NO (return null)<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *             <td style="width: 16.6667%; text-align: center;">✔<br></td>
 *         </tr>
 *     </tbody>
 * </table>
 */
public class DailyWeather implements Parcelable  {

    private long date;
    private Condition condition;
    private TemperatureMinMax temperature;
    private TemperatureMinMax apparentTemp;
    private Temperature dewPoint;
    private Pressure pressure;
    private Humidity humidity;
    private Visibility visibility;
    private Wind wind;
    private CloudCover cloudCover;
    private Precipitation precipitation;
    private UV uv;
    private Astronomy sun;
    private Astronomy moon;
    private MoonPhase moonPhase;

    private DailyWeather() { }

    protected DailyWeather(Parcel in) {
        date = in.readLong();
        condition = (Condition) in.readValue(Condition.class.getClassLoader());
        temperature = (TemperatureMinMax) in.readValue(TemperatureMinMax.class.getClassLoader());
        apparentTemp = (TemperatureMinMax) in.readValue(TemperatureMinMax.class.getClassLoader());
        dewPoint = (Temperature) in.readValue(Temperature.class.getClassLoader());
        pressure = (Pressure) in.readValue(Pressure.class.getClassLoader());
        humidity = (Humidity) in.readValue(Humidity.class.getClassLoader());
        visibility = (Visibility) in.readValue(Visibility.class.getClassLoader());
        wind = (Wind) in.readValue(Wind.class.getClassLoader());
        cloudCover = (CloudCover) in.readValue(CloudCover.class.getClassLoader());
        precipitation = (Precipitation) in.readValue(Precipitation.class.getClassLoader());
        uv = (UV) in.readValue(UV.class.getClassLoader());
        sun = (Astronomy)  in.readValue(Astronomy.class.getClassLoader());
        moon = (Astronomy)  in.readValue(Astronomy.class.getClassLoader());
        moonPhase = (MoonPhase) in.readValue(MoonPhase.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(date);
        dest.writeValue(condition);
        dest.writeValue(temperature);
        dest.writeValue(apparentTemp);
        dest.writeValue(dewPoint);
        dest.writeValue(pressure);
        dest.writeValue(humidity);
        dest.writeValue(visibility);
        dest.writeValue(wind);
        dest.writeValue(cloudCover);
        dest.writeValue(precipitation);
        dest.writeValue(uv);
        dest.writeValue(sun);
        dest.writeValue(moon);
        dest.writeValue(moonPhase);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DailyWeather> CREATOR = new Parcelable.Creator<DailyWeather>() {
        @Override
        public DailyWeather createFromParcel(Parcel in) {
            return new DailyWeather(in);
        }

        @Override
        public DailyWeather[] newArray(int size) {
            return new DailyWeather[size];
        }
    };

    public static class Builder {

        private DailyWeather weather;

        public Builder() {
            weather = new DailyWeather();
        }

        public Builder setDate(long date) {
            weather.date = date;
            return this;
        }

        public Builder setCondition(WEATHER_ICON icon, String description) {
            weather.condition = new Condition(icon, description);
            return this;
        }

        public Builder setTemperature(float min, float max, String unit) {
            weather.temperature = new TemperatureMinMax(min, max, unit);
            return this;
        }

        public Builder setApparentTemperature(float min, float max, String unit) {
            weather.apparentTemp = new TemperatureMinMax(min, max, unit);
            return this;
        }

        public Builder setDewPoint(float temp, String unit) {
            weather.dewPoint = new Temperature(temp, unit);
            return this;
        }

        public Builder setPressure(float pressure, String unit) {
            weather.pressure = new Pressure(pressure, unit);
            return this;
        }

        public Builder setHumidity(int humidity) {
            weather.humidity = new Humidity(humidity);
            return this;
        }

        public Builder setVisibility(float visibility, String unit) {
            weather.visibility = new Visibility(visibility, unit);
            return this;
        }

        public Builder setWind(float speed, float direction, String unit) {
            weather.wind = new Wind(speed, direction, unit);
            return this;
        }

        public Builder setCloudCover(int cloudCover) {
            weather.cloudCover = new CloudCover(cloudCover);
            return this;
        }

        public Builder setPrecipitation(float amount, int probably, String unit) {
            weather.precipitation = new Precipitation(amount, probably, unit);
            return this;
        }

        public Builder setPrecipitation(float amount, String unit) {
            weather.precipitation = new Precipitation(amount, unit);
            return this;
        }

        public Builder setUvIndex(float uvIndex) {
            weather.uv = new UV(uvIndex);
            return this;
        }

        public Builder setSunAstronomy(long rise, long set) {
            weather.sun = new Astronomy(rise, set);
            return this;
        }

        public Builder setMoonAstronomy(long rise, long set) {
            weather.moon = new Astronomy(rise, set);
            return this;
        }

        public Builder setMoonPhase(float phase) {
            weather.moonPhase = new MoonPhase(phase);
            return this;
        }

        public DailyWeather build() {
            return weather;
        }
    }

    // *********************************************************************************************
    // *                                    Getter methods                                         *
    // *********************************************************************************************

    public long getTime() {
        return date;
    }

    public Condition condition() {
        return condition;
    }

    public TemperatureMinMax temperature() {
        return temperature;
    }

    public TemperatureMinMax apparentTemp() {
        return apparentTemp;
    }

    public Temperature dewpoint() {
        return dewPoint;
    }

    public Pressure pressure() {
        return pressure;
    }

    public Humidity humidity() {
        return humidity;
    }

    public Visibility visibility() {
        return visibility;
    }

    public Wind wind() {
        return wind;
    }

    public CloudCover cloudCover() {
        return cloudCover;
    }

    public Precipitation precipitation() {
        return precipitation;
    }

    public UV uv() {
        return uv;
    }

    public Astronomy sun() {
        return sun;
    }

    public Astronomy moon() {
        return moon;
    }

    public MoonPhase moonPhase() {
        return moonPhase;
    }

    @NonNull
    @Override
    public String toString() {

        Date date = new Date(getTime());
        SimpleDateFormat format = new SimpleDateFormat("EEE, MMM d", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());

        return "Daily Weather: {"
                + "\n\t" + "date: " + format.format(date)
                + "\n\t" + "icon: " + (condition() == null ? "---" : condition().getIcon())
                + "\n\t" + "description: " + (condition() == null ? "---" : condition().getDescription())
                + "\n\t" + "temperature: " + (temperature() == null ? "---" : temperature().getMin() + "/" + temperature().getMax() + " " + temperature().getUnit())
                + "\n\t" + "apparentTemperature: " + (apparentTemp() == null ? "---" : apparentTemp().getMin() + "/" + apparentTemp().getMax() + " " + apparentTemp().getUnit())
                + "\n\t" + "dewPoint: " + (dewpoint() == null ? "---" : (dewpoint().getValue() + " " + dewpoint().getUnit()))
                + "\n\t" + "pressure: " + (pressure() == null ? "---" : pressure().getValue() + " " + pressure().getUnit())
                + "\n\t" + "humidity: " + (humidity() == null ? "---" : humidity().getValue() + "%")
                + "\n\t" + "visibility: " + (visibility() == null ? "---" : visibility().getValue() + " " + visibility().getUnit())
                + "\n\t" + "windSpeed: " + (wind() == null ? "---" : wind().getSpeed() + " " + wind().getUnit())
                + "\n\t" + "windDegree: " + (wind() == null ? "---" : wind().getDirection())
                + "\n\t" + "cloudCover: " + (cloudCover() == null ? "---" : cloudCover().getValue() + "%")
                + "\n\t" + "precipitation: " + (precipitation() == null ? "---" : precipitation().getAmount() + " " + precipitation().getAmountUnit()
                + (precipitation().getProbably() > 0 ? " (" + precipitation().getProbably() + "%)" : "" ))
                + "\n\t" + "uvIndex: " + (uv() == null ? "---" : uv().getIndex())

                + "\n\t" + "sunrise: " + (sun() == null ? "---" : timeFormat.format(new Date(sun().getRiseTime())))
                + "\n\t" + "sunset: " + (sun() == null ? "---" : timeFormat.format(new Date(sun().getSetTime())))
                + "\n\t" + "moonrise : " + (moon() == null ? "---" : timeFormat.format(new Date(moon().getRiseTime())))
                + "\n\t" + "moonset: " + (moon() == null ? "---" : timeFormat.format(new Date(moon().getSetTime())))
                + "\n\t" + "moonPhase: " + (moonPhase() == null ? "---" : moonPhase().getPhase())
                + "\n}";

    }
}
