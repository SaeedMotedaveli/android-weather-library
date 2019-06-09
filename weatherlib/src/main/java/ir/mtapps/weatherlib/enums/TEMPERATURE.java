package ir.mtapps.weatherlib.enums;

public enum TEMPERATURE {

    C("°C"), F("°F"), K("°K");

    private String str;

    TEMPERATURE(String str) {
        this.str = str;
    }

    public String str() {
        return str;
    }

    public static float convert(TEMPERATURE from, TEMPERATURE to, float value) {

        // Celsius
        if (from == C) {

            if (to == F) {
                return (value * 1.8f) + 32f;
            }

            else if (to == K) {
                return value + 273.15f;
            }
        }

        // Fahrenheit
        else if (from == F) {

            if (to == C) {
                return (value - 32f) / 1.8f;
            }

            else if (to == K) {
                return (value + 459.67f) * 5f / 9f;
            }

        }

        // Kelvin
        else if (from == K) {

            if (to == C) {
                return value - 273.15f;
            }

            else if (to == F) {
                return (value * 9f / 5f) - 459.67f;
            }

        }

        return value;
    }
}
