package ir.mtapps.weatherlib.model.value;

import android.os.Parcel;
import android.os.Parcelable;

public class AQ implements Parcelable {

    private float index;
    private String riskLevel;
    private String healthMessage;

    public AQ(float index,
            String riskLevel,
            String healthMessage) {

        this.index = index;
        this.riskLevel = riskLevel;
        this.healthMessage = healthMessage;

    }

    private AQ(Parcel in) {
        index = in.readFloat();
        riskLevel = in.readString();
        healthMessage = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(index);
        dest.writeString(riskLevel);
        dest.writeString(healthMessage);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AQ> CREATOR = new Parcelable.Creator<AQ>() {
        @Override
        public AQ createFromParcel(Parcel in) {
            return new AQ(in);
        }

        @Override
        public AQ[] newArray(int size) {
            return new AQ[size];
        }
    };

    // ---------------------------------------------------------------------------------------------

    public float index() {
        return index;
    }

    public String riskLevel() {
        return riskLevel;
    }

    public String healthMessage() {
        return healthMessage;
    }
}
