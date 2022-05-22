package ir.mtapps.weatherlib.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ResponseResult implements Parcelable {

    private boolean isSuccessful;
    private int code;
    private String message;

    private ResponseResult() {}

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    protected ResponseResult(Parcel in) {
        isSuccessful = in.readInt() > 0;
        code = in.readInt();
        message = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(isSuccessful ? 1 : 0);
        dest.writeInt(code);
        dest.writeString(message);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ResponseResult> CREATOR = new Parcelable.Creator<ResponseResult>() {
        @Override
        public ResponseResult createFromParcel(Parcel in) {
            return new ResponseResult(in);
        }

        @Override
        public ResponseResult[] newArray(int size) {
            return new ResponseResult[size];
        }
    };

    public static class Builder {

        private final ResponseResult mResult;

        public Builder() {
            mResult = new ResponseResult();
        }

        public Builder isSuccessful(boolean successful) {
            mResult.isSuccessful = successful;
            return this;
        }

        public Builder setCode(int code) {
            mResult.code = code;
            return this;
        }

        public Builder setMessage(String message) {
            mResult.message = message;
            return this;
        }

        public ResponseResult build() {

            if (mResult.isSuccessful) {
                mResult.code = 200;
                mResult.message = null;
            }

            return mResult;
        }

    }
}
