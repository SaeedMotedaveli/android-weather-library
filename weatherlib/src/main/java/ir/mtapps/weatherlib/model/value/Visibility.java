package ir.mtapps.weatherlib.model.value;

import android.os.Parcel;
import android.os.Parcelable;

public class Visibility implements Parcelable {

    private final float value;
    private final String unit;

    public Visibility(float value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    private Visibility(Parcel in) {
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
    public static final Parcelable.Creator<Visibility> CREATOR = new Parcelable.Creator<Visibility>() {
        @Override
        public Visibility createFromParcel(Parcel in) {
            return new Visibility(in);
        }

        @Override
        public Visibility[] newArray(int size) {
            return new Visibility[size];
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
