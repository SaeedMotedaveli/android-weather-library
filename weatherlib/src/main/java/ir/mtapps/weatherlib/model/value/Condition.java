package ir.mtapps.weatherlib.model.value;

import android.os.Parcel;
import android.os.Parcelable;

import ir.mtapps.weatherlib.enums.WEATHER_ICON;

public class Condition implements Parcelable {
    private final WEATHER_ICON icon;
    private final String description;

    public Condition(WEATHER_ICON icon, String description) {
        this.icon = icon;
        this.description = description;
    }

    private Condition(Parcel in) {
        icon = (WEATHER_ICON) in.readValue(WEATHER_ICON.class.getClassLoader());
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(icon);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Condition> CREATOR = new Parcelable.Creator<Condition>() {
        @Override
        public Condition createFromParcel(Parcel in) {
            return new Condition(in);
        }

        @Override
        public Condition[] newArray(int size) {
            return new Condition[size];
        }
    };


    // --------------------------------------------------------------------------------------------

    public WEATHER_ICON getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

}
