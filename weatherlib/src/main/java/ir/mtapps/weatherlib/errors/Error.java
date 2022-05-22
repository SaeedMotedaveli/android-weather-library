package ir.mtapps.weatherlib.errors;

public class Error {

    /**
     * The error occurred when api key not initialize when create {@link ir.mtapps.weatherlib.WeatherClient.Builder}.
     */
    public static int API_KEY_REQUIRE = 1000;

    public static int PREMIUM_API_KEY_REQUIRE = 1001;

    public static int RESPONSE_IS_NULL = 2000;

    public static int PROVIDER_RETURN_ERROR = 2001;

    public static int RESPONSE_NOT_OK = 2002;

    public static int RESPONSE_FAILURE = 2003;

    public static int LOCATION_PERMISSION_DENIED = 3000;

    public static int LOCATION_ERROR = 3001;

    public static int NETWORK_ERROR = 4000;

    public static int AIR_QUALITY_NOT_FOUND = 5000;
}
