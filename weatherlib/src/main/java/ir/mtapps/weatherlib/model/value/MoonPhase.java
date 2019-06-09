package ir.mtapps.weatherlib.model.value;

import android.os.Parcel;
import android.os.Parcelable;

public class MoonPhase implements Parcelable {

    private float phase;
    private int percent;
    private int age;

    public MoonPhase(float phase) {

        this.phase = phase;
        percent = calculatePercent(phase);
        age = calculateAge(phase);

    }

    protected MoonPhase(Parcel in) {
        phase = in.readFloat();
        percent = in.readInt();
        age = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(phase);
        dest.writeInt(percent);
        dest.writeInt(age);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MoonPhase> CREATOR = new Parcelable.Creator<MoonPhase>() {
        @Override
        public MoonPhase createFromParcel(Parcel in) {
            return new MoonPhase(in);
        }

        @Override
        public MoonPhase[] newArray(int size) {
            return new MoonPhase[size];
        }
    };

    private int calculatePercent(float phase) {

        if (phase <= 0.5f) {
            return Math.round(phase * 200);
        } else {
            return Math.round((1 - phase) * 200);
        }

    }

    private int calculateAge(float phase) {

        return Math.round(phase * 29);

    }

    public float getPhase() {
        return phase;
    }

    public int getPercent() {
        return percent;
    }

    public int getAge() {
        return age;
    }
}