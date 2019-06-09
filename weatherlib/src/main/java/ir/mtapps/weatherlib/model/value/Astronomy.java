package ir.mtapps.weatherlib.model.value;

import android.os.Parcel;
import android.os.Parcelable;

public class Astronomy implements Parcelable {

    private long riseTime;
    private long setTime;

    public Astronomy(long rise, long set) {
        this.riseTime = rise;
        this.setTime = set;
    }

    private Astronomy(Parcel in) {
        riseTime = in.readLong();
        setTime = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(riseTime);
        dest.writeLong(setTime);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Astronomy> CREATOR = new Parcelable.Creator<Astronomy>() {
        @Override
        public Astronomy createFromParcel(Parcel in) {
            return new Astronomy(in);
        }

        @Override
        public Astronomy[] newArray(int size) {
            return new Astronomy[size];
        }
    };

    // ---------------------------------------------------------------------------------------------

    public long getRiseTime() {
        return riseTime;
    }

    public long getSetTime() {
        return setTime;
    }

}
