package ir.mtapps.weatherlib.model.value;

import android.os.Parcel;
import android.os.Parcelable;

public class Temperature implements Parcelable {

    private final float value;
    private final String unit;

    public Temperature(float value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    private Temperature(Parcel in) {
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
    public static final Parcelable.Creator<Temperature> CREATOR = new Parcelable.Creator<Temperature>() {
        @Override
        public Temperature createFromParcel(Parcel in) {
            return new Temperature(in);
        }

        @Override
        public Temperature[] newArray(int size) {
            return new Temperature[size];
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
