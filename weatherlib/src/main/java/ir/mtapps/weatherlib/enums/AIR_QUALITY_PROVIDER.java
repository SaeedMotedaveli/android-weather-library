package ir.mtapps.weatherlib.enums;

public enum AIR_QUALITY_PROVIDER {

    WORLD_AIR_POLLUTION("World's Air Pollution");

    private String name;

    AIR_QUALITY_PROVIDER(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
