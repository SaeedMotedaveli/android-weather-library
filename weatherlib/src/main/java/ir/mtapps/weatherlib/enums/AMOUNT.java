package ir.mtapps.weatherlib.enums;

public enum AMOUNT {
    mm("mm"), inch("inches"), Cm("Cm");

    private final String str;

    AMOUNT(String str) {
        this.str = str;
    }

    public String str() {
        return str;
    }

    public static float convert(AMOUNT from, AMOUNT to, float value) {

        // mm
        if (from == mm) {

            if (to == inch) {
                return value * 0.0393701f;
            } else if (to == Cm) {
                return value / 10f;
            }

        }

        // inch
        else if (from == inch) {

            if (to == mm) {
                return value * 25.4f;
            } else if (to == Cm) {
                return value * 2.54f;
            }

        }

        // Cm
        else if (from == Cm) {

            if (to == mm) {
                return value * 10f;
            } else if (to == inch) {
                return value * 0.393701f;
            }

        }

        return value;
    }
}
