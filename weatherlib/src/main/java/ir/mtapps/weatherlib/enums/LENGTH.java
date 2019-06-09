package ir.mtapps.weatherlib.enums;

public enum LENGTH {
    m("m"), Km("Km"), miles("miles");

    private String str;

    LENGTH(String str) {
        this.str = str;
    }

    public String str() {
        return str;
    }

    public static float convert(LENGTH from, LENGTH to, float value) {

        if (from == m) {

            if (to == Km) {
                return value / 1000;
            } else if (to == miles) {
                return value * 0.621371f / 1000;
            }

        } else if (from == Km) {

            if (to == m) {
                return value * 1000;
            } else if (to == miles) {
                return value * 0.621371f;
            }

        } else if (from == miles) {

            if (to == m) {
                return value * 1.60934f * 1000;
            } else if (to == Km) {
                return value * 1.60934f;
            }

        }

        return value;
    }

}
