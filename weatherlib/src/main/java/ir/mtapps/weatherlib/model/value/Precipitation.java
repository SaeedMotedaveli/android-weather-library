package ir.mtapps.weatherlib.model.value;

import android.os.Parcel;
import android.os.Parcelable;

public class Precipitation implements Parcelable {

    private final float amount;
    private final int probably;
    private final String unit;

    public Precipitation(float amount, String unit) {
        this.amount = amount;
        this.probably = -1;
        this.unit = unit;
    }

    public Precipitation(float amount, int probably, String unit) {
        this.amount = amount;
        this.probably = probably;
        this.unit = unit;
    }

    private Precipitation(Parcel in) {
        amount = in.readFloat();
        probably = in.readInt();
        unit = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(amount);
        dest.writeInt(probably);
        dest.writeString(unit);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Precipitation> CREATOR = new Parcelable.Creator<Precipitation>() {
        @Override
        public Precipitation createFromParcel(Parcel in) {
            return new Precipitation(in);
        }

        @Override
        public Precipitation[] newArray(int size) {
            return new Precipitation[size];
        }
    };

    // ---------------------------------------------------------------------------------------------

    public float getAmount() {
        return amount;
    }

    public int getProbably() {
        return probably;
    }

    public String getAmountUnit() {
        return unit;
    }

    public String getProbablyUnit() {
        return "%";
    }

}
