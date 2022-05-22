package ir.mtapps.weatherlib.model.value;

import android.os.Parcel;
import android.os.Parcelable;

public class Humidity implements Parcelable {

    private final int value;

    public Humidity(int value) {
        this.value = value;
    }

    private Humidity(Parcel in) {
        value = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(value);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Humidity> CREATOR = new Parcelable.Creator<Humidity>() {
        @Override
        public Humidity createFromParcel(Parcel in) {
            return new Humidity(in);
        }

        @Override
        public Humidity[] newArray(int size) {
            return new Humidity[size];
        }
    };

    // ---------------------------------------------------------------------------------------------

    public int getValue() {
        return value;
    }

    public String getUnit() {
        return "%";
    }
}
