package ir.mtapps.weatherlib.model.value;

import android.os.Parcel;
import android.os.Parcelable;

public class CloudCover implements Parcelable {

    private final int value;

    public CloudCover(int value) {
        this.value = value;
    }

    private CloudCover(Parcel in) {
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
    public static final Parcelable.Creator<CloudCover> CREATOR = new Parcelable.Creator<CloudCover>() {
        @Override
        public CloudCover createFromParcel(Parcel in) {
            return new CloudCover(in);
        }

        @Override
        public CloudCover[] newArray(int size) {
            return new CloudCover[size];
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