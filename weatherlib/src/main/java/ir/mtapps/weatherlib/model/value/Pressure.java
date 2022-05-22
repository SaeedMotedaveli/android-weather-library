package ir.mtapps.weatherlib.model.value;

import android.os.Parcel;
import android.os.Parcelable;

public class Pressure implements Parcelable {

    private final float value;
    private final String unit;

    public Pressure(float value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    private Pressure(Parcel in) {
        value = in.readFloat();
        unit = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(value);
        dest.writeString(unit);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Pressure> CREATOR = new Parcelable.Creator<Pressure>() {
        @Override
        public Pressure createFromParcel(Parcel in) {
            return new Pressure(in);
        }

        @Override
        public Pressure[] newArray(int size) {
            return new Pressure[size];
        }
    };

    // ---------------------------------------------------------------------------------------------

    public float getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

}