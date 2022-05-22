package ir.mtapps.weatherlib.enums;

public enum PRESSURE {
    mb("mb"), mmHg("mmHg"), inHg("inHg"), hPa("hPa"), kPa("kPa"), atm("atm");

    private final String str;

    PRESSURE(String str) {
        this.str = str;
    }

    public String str() {
        return str;
    }

    public static float convert(PRESSURE from, PRESSURE to, float value) {

        // mb
        if (from == mb) {

            if (to == mmHg) {
                return value *  0.750062f;
            } else if (to == inHg) {
                return value * 0.0295300f;
            } else if (to == hPa) {
                return value;
            } else if (to == kPa) {
                return value / 10f;
            } else if (to == atm) {
                return value * 0.0009869233f;
            }

        }

        // mmHg
        else if (from == mmHg) {

            if (to == mb) {
                return value * 1.333224f;
            } else if (to == inHg) {
                return value * 0.03937008f;
            } else if (to == hPa) {
                return value * 1.333224f;
            } else if (to == kPa) {
                return value * 0.1333224f;
            } else if (to == atm) {
                return value / 760f;
            }

        }

        // inHg
        else if (from == inHg) {

            if (to == mb) {
                return value * 33.8639f;
            } else if (to == mmHg) {
                return value * 25.4f;
            } else if (to == hPa) {
                return value * 33.8639f;
            } else if (to == kPa) {
                return value * 3.38639f;
            } else if (to == atm) {
                return value * 0.033421f;
            }

        }

        else if (from == hPa) {

            if (to == mb) {
                return value;
            } else if (to == mmHg) {
                return value *  0.750062f;
            } else if (to == inHg) {
                return value * 0.0295300f;
            } else if (to == kPa) {
                return value / 10f;
            } else if (to == atm) {
                return value * 0.0009869233f;
            }

        }

        else if (from == kPa) {

            if (to == mb) {
                return value * 10f;
            } else if (to == mmHg) {
                return value * 7.50062f;
            } else if (to == inHg) {
                return value * 0.295300f;
            } else if (to == hPa) {
                return value * 10f;
            } else if (to == atm) {
                return value *  0.009869233f;
            }

        }

        else if (from == atm) {

            if (to == mb) {
                return value * 1013.250F;
            } else if (to == mmHg) {
                return value * 760f;
            } else if (to == inHg) {
                return value * 29.9213f;
            } else if (to == hPa) {
                return value * 1013.250f;
            } else if (to == kPa) {
                return value * 101.3250f;
            }

        }

        return value;
    }
}
