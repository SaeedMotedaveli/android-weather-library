package ir.mtapps.weatherlib.enums;

public enum SPEED {
    Km_h("Km/h"), m_s("m/s"), mph("mph"), Knot("Knot");

    private String str;

    SPEED(String str) {
        this.str = str;
    }

    public String str() {
        return str;
    }

    public static float convert(SPEED from, SPEED to, float value) {

        // Km/h
        if (from == Km_h) {

            if (to == m_s) {
                return value * 5f / 18f;
            } else if (to == mph) {
                return value * 0.621371f;
            } else if (to == Knot) {
                return value * 0.539957f;
            }

        }
        else if (from == m_s) {

            if (to == Km_h) {
                return value * 3.6f;
            } else if (to == mph) {
                return value * 2.23694f;
            } else if (to == Knot) {
                return value * 1.94384f;
            }

        }
        else if (from == mph) {

            if (to == Km_h) {
                return value * 1.60934f;
            } else if (to == m_s) {
                return value * 0.44704f;
            } else if (to == Knot) {
                return value * 0.868976f;
            }

        }
        else if (from == Knot) {

            if (to == Km_h) {
                return value * 1.852f;
            } else if (to == m_s) {
                return value * 0.514444f;
            } else if (to == mph) {
                return value * 1.15078f;
            }

        }

        return value;
    }
}
