package ir.mtapps.weatherlib.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import ir.mtapps.weatherlib.model.value.AQ;
import ir.mtapps.weatherlib.model.value.AqValue;

public class AirQuality implements Parcelable {

    private AQ aq;
    private AqValue pm25;
    private AqValue pm10;
    private AqValue o3;
    private AqValue no2;
    private AqValue so2;
    private AqValue co;

    private AirQuality() {}

    private AirQuality(Parcel in) {
        aq = (AQ) in.readValue(AQ.class.getClassLoader());
        pm25 = (AqValue) in.readValue(AqValue.class.getClassLoader());
        pm10 = (AqValue) in.readValue(AqValue.class.getClassLoader());
        o3 = (AqValue) in.readValue(AqValue.class.getClassLoader());
        no2 = (AqValue) in.readValue(AqValue.class.getClassLoader());
        so2 = (AqValue) in.readValue(AqValue.class.getClassLoader());
        co = (AqValue) in.readValue(AqValue.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(aq);
        dest.writeValue(pm25);
        dest.writeValue(pm10);
        dest.writeValue(o3);
        dest.writeValue(no2);
        dest.writeValue(so2);
        dest.writeValue(co);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AirQuality> CREATOR = new Parcelable.Creator<AirQuality>() {
        @Override
        public AirQuality createFromParcel(Parcel in) {
            return new AirQuality(in);
        }

        @Override
        public AirQuality[] newArray(int size) {
            return new AirQuality[size];
        }
    };

    // ---------------------------------------------------------------------------------------------

    public static class Builder {

        private AirQuality airQuality = new AirQuality();

        public Builder setIndex(float index, String riskLevel, String healthMessage) {

            airQuality.aq = new AQ(index, riskLevel, healthMessage);

            return this;
        }

        public Builder setPm25(float value) {

            airQuality.pm25 = new AqValue(value);

            return this;
        }

        public Builder setPm10(float value) {

            airQuality.pm10 = new AqValue(value);

            return this;
        }

        public Builder setO3(float value) {

            airQuality.o3 = new AqValue(value);

            return this;
        }

        public Builder setNo2(float value) {

            airQuality.no2 = new AqValue(value);

            return this;
        }

        public Builder setSo2(float value) {

            airQuality.so2 = new AqValue(value);

            return this;
        }

        public Builder setCo(float value) {

            airQuality.co = new AqValue(value);

            return this;
        }

        public AirQuality build() {
            return airQuality;
        }
    }

    // ---------------------------------------------------------------------------------------------

    public AQ aq() {
        return aq;
    }

    public AqValue pm25() {
        return pm25;
    }

    public AqValue pm10() {
        return pm10;
    }

    public AqValue o3() {
        return o3;
    }

    public AqValue no2() {
        return no2;
    }

    public AqValue so2() {
        return so2;
    }

    public AqValue co() {
        return co;
    }

    // ---------------------------------------------------------------------------------------------


    @NonNull
    @Override
    public String toString() {
        return "Air Quality {"
                + "\n\t" + "aq: " + aq.index()
                + "\n\t" + "PM25: " + (pm25() == null ? "N/A" : pm25().value())
                + "\n\t" + "PM10: " + (pm10() == null ? "N/A" : pm10().value())
                + "\n\t" + "O3: " + (o3() == null ? "N/A" : o3().value())
                + "\n\t" + "NO2: " + (no2() == null ? "N/A" : no2().value())
                + "\n\t" + "SO2: " + (so2() == null ? "N/A" : so2().value())
                + "\n\t" + "CO: " + (co() == null ? "N/A" : co().value())
                + "\n}";
    }
}
