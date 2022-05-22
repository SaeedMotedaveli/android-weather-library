package ir.mtapps.weatherlib.model.value;

import android.os.Parcel;
import android.os.Parcelable;

public class TemperatureMinMax implements Parcelable {

    private final float min;
    private final float max;
    private final String unit;

    public TemperatureMinMax(float min, float max, String unit) {
        this.min = min;
        this.max = max;
        this.unit = unit;
    }

    private TemperatureMinMax(Parcel in) {
        min = in.readFloat();
        max = in.readFloat();
        unit = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(min);
        dest.writeFloat(max);
        dest.writeString(unit);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TemperatureMinMax> CREATOR = new Parcelable.Creator<TemperatureMinMax>() {
        @Override
        public TemperatureMinMax createFromParcel(Parcel in) {
            return new TemperatureMinMax(in);
        }

        @Override
        public TemperatureMinMax[] newArray(int size) {
            return new TemperatureMinMax[size];
        }
    };

    // ---------------------------------------------------------------------------------------------

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public String getUnit() {
        return unit;
    }

}
