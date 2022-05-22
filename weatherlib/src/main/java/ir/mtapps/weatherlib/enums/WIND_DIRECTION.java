package ir.mtapps.weatherlib.enums;

public enum WIND_DIRECTION {

    N, NNE, NE, ENE, E, ESE, SE, SSE, S, SSW, SW, WSW, W, WNW, NW, NNW;

    /**
     * Creates the direction from the azimuth degrees.
     */
    public static WIND_DIRECTION getDirection(int deg) {
        int degPositive = deg;
        if (deg < 0) {
            degPositive += (-deg / 360 + 1) * 360;
        }
        int degNormalized = degPositive % 360;
        int degRotated = degNormalized + (360 / 16 / 2);
        int sector = degRotated / (360 / 16);
        switch (sector) {
            case 0:
            case 16:
                return WIND_DIRECTION.N;
            case 1:
                return WIND_DIRECTION.NNE;
            case 2:
                return WIND_DIRECTION.NE;
            case 3:
                return WIND_DIRECTION.ENE;
            case 4:
                return WIND_DIRECTION.E;
            case 5:
                return WIND_DIRECTION.ESE;
            case 6:
                return WIND_DIRECTION.SE;
            case 7:
                return WIND_DIRECTION.SSE;
            case 8:
                return WIND_DIRECTION.S;
            case 9:
                return WIND_DIRECTION.SSW;
            case 10:
                return WIND_DIRECTION.SW;
            case 11:
                return WIND_DIRECTION.WSW;
            case 12:
                return WIND_DIRECTION.W;
            case 13:
                return WIND_DIRECTION.WNW;
            case 14:
                return WIND_DIRECTION.NW;
            case 15:
                return WIND_DIRECTION.NNW;
        }
        return WIND_DIRECTION.N;
    }

}
