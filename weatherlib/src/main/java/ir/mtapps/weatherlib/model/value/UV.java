package ir.mtapps.weatherlib.model.value;

import android.os.Parcel;
import android.os.Parcelable;

public class UV implements Parcelable {
    
    private final float value;

    public UV(float index) {
        this.value = index;
    }

    private UV(Parcel in) {
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
    public static final Parcelable.Creator<UV> CREATOR = new Parcelable.Creator<UV>() {
        @Override
        public UV createFromParcel(Parcel in) {
            return new UV(in);
        }

        @Override
        public UV[] newArray(int size) {
            return new UV[size];
        }
    };

    // ---------------------------------------------------------------------------------------------

    public float getIndex() {
        return value;
    }

}
