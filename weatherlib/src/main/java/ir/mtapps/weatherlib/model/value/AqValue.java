package ir.mtapps.weatherlib.model.value;

import android.os.Parcel;
import android.os.Parcelable;

public class AqValue implements Parcelable {

    private final float value;

    public AqValue(float value) {
        this.value = value;
    }

    private AqValue(Parcel in) {
        value = in.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(value);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AqValue> CREATOR = new Parcelable.Creator<AqValue>() {
        @Override
        public AqValue createFromParcel(Parcel in) {
            return new AqValue(in);
        }

        @Override
        public AqValue[] newArray(int size) {
            return new AqValue[size];
        }
    };

    // ---------------------------------------------------------------------------------------------

    public float value() {
        return value;
    }
}
