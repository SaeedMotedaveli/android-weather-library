package ir.mtapps.weatherlib.model.value;

import android.os.Parcel;
import android.os.Parcelable;

import ir.mtapps.weatherlib.enums.WIND_DIRECTION;

public class Wind implements Parcelable {

    private final float speed;
    private final float direction;
    private final String unit;

    public Wind(float speed, float direction, String unit) {
        this.speed = speed;
        this.direction = direction;
        this.unit = unit;
    }

    private Wind(Parcel in) {
        speed = in.readFloat();
        direction = in.readFloat();
        unit = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(speed);
        dest.writeFloat(direction);
        dest.writeString(unit);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Wind> CREATOR = new Parcelable.Creator<Wind>() {
        @Override
        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        @Override
        public Wind[] newArray(int size) {
            return new Wind[size];
        }
    };

    // ---------------------------------------------------------------------------------------------

    public float getSpeed() {
        return speed;
    }

    public float getDirection() {
        return direction;
    }

    public String getDirectionStr() {
        return WIND_DIRECTION.getDirection(Math.round(direction)).toString();
    }

    public String getUnit() {
        return unit;
    }

}