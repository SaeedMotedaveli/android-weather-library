# MT Weather Library
[<img src="https://jitpack.io/v/SaeedMotedaveli/android-weather-library.svg)](https://jitpack.io/#SaeedMotedaveli/android-weather-library)

An android library for request weather from different providers. Supported weather providers:
1. [AccuWeather](https://developer.accuweather.com/apis)
2. [Aeris Weather](https://www.aerisweather.com/support/docs/api/)
3. [Dark Sky](https://darksky.net/dev)
4. [Open Weather](https://openweathermap.org/api)
5. [Tomorrow.io](https://www.tomorrow.io/weather-api/)
6. [VisualCrossing](https://www.visualcrossing.com/weather-api)
7. [Weatherbit](https://www.weatherbit.io/api)
8. [World Weather Online](https://www.worldweatheronline.com/developer/api/)

And air quality provider:
1. [World's Air Pollution](https://aqicn.org/api/)

*For testing library, please have a look at the Demo Project (demo) or download apk file from [here](https://mtapps.ir/wp-content/uploads/2022/06/mt_weather_library_v2.0.apk).*

<img src="/assets/mt-weather-lib-demo-preview.png" width="200" />  

# Usage

1. Add it in your root `build.gradle` at the end of repositories:
```java
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
or add it at `settings.gradle`:
```java
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
2. Add the dependency:
```java
dependencies {
  implementation 'com.github.SaeedMotedaveli:android-weather-library:1.0.5'
}
```
4. Use default Configuration:

 ```java
 WeatherConfig weatherConfig = WeatherConfig.create();
 ```

Or create your configuration:

  ```java
  int numberOfDays = 7;
int numberOfHours = 24;
String language = "en";
UNIT_SYSTEM unitSystem = UNIT_SYSTEM.METRIC;
        
WeatherConfig weatherConfig = WeatherConfig.edit()
	.limitDailyWeather(numberOfDays)
	.limitHourlyWeather(numberOfHours)
	.language(language)
	.unitSystem(unitSystem)
	.create();
  ```

| method | description | defualt |
|--|--| :--: |
| `limitDailyWeather(int)` | Number of days that daily weather return (include today). | no limit |
| `limitHourlyWeather(int)` | Number of hours that hourly weather return (include current hour) every 60 minutes | no limit |
| `language(String)` | Language of weather data | en |
| `unitSystem(UNIT_SYSTEM)` | **`UNIT_SYSTEM.METRIC`** (*Temperature: °C, Speed: Km/h, Amount: mm, Pressure: mmHg and Length: Km*)  or **`UNIT_SYSTEM.IMPERIAL`** (*Temperature: °F, Speed: mph, Amount: in, Pressure: mmHg and Length: miles*) | `UNIT_SYSTEM.METRIC` |
| `unitOfTemperature(TEMPERATURE)` | `TEMPERATURE.C`, `TEMPERATURE.F`  or `TEMPERATURE.K` | `TEMPERATURE.C` |
| `unitOfSpeed(SPEED)` | `SPEED.Km_h`, `SPEED.m_s`, `SPEED.mph` or `SPEED.Knot` | `SPEED.Km_h` |
| `unitOfPressure(PRESSURE)` | `PRESSURE.mb`, `PRESSURE.mmHg`, `PRESSURE.inHg`, `PRESSURE.hPa`, `PRESSURE.kPa` or `PRESSURE.atm` | `SPEED.Km_h` |
| `unitOfVisibility(LENGTH)` | `LENGTH.m`, `LENGTH.Km` or `LENGTH.miles` | `LENGTH.Km` |
| `unitOfPrecipitationAmount(AMOUNT)` | `AMOUNT.mm`, `AMOUNT.inch` or `AMOUNT.Cm` | `AMOUNT.mm` |
| `validRadiusOfAirQuality(int)` | valid radius of air quality (unit: Kilometers) | 5 |


5. Create client. For weather client:

```java
PROVIDER provider = PROVIDER.OPEN_WEATHER;	// or any you want...
String apiKey = "<your api key>";
double latitude = 0;
double longitude = 0;
        
WeatherClient weatherClient = new WeatherClient.Builder()
	.config(weatherConfig)
	.provider(provider)
	.apiKey(apiKey)
	.coordinate(latitude, longitude)	// remove it if you want use GPS
	.build();  
  ```

| method | description |
|--|--|
| `config(WeatherConfig)` | Use `WeatherConfig` instance |
| `provider(PROVIDER)` | `PROVIDER.DARK_SKY`, `PROVIDER.OPEN_WEATHER`, `PROVIDER.WORLD_WEATHER_ONLINE`, `PROVIDER.ACCUWEATHER`, `PROVIDER.WEATHERBIT`, `PROVIDER.VISUAL_CROSSING`, `PROVIDER.TOMORROW` or `PROVIDER.AERIS_WEATHER` |
| `apiKey(String)` or `apiKey(String, String)` | For Aeris Weather use `apiKey(clientId, clientSecret)` and for others use `apiKey(key)` |
| `coordinate(double, double)` | Latitude and longitude of your place. If you want to use GPS, just remove this method. |

for air quality:
```java
AIR_QUALITY_PROVIDER airQualityProvider = AIR_QUALITY_PROVIDER.WORLD_AIR_POLLUTION;
String apiKey = "<your api key>";
double latitude = 0;
double longitude = 0;
        
AirQualityClient airQualityClient = new AirQualityClient.Builder()
	.config(weatherConfig)
	.provider(airQualityProvider)
	.apiKey(apiKey)
	.coordinate(latitude, longitude)
	.build();
```

| method | description |
|--|--|
| `config(WeatherConfig)` | Use `WeatherConfig` instance |
| `provider(PROVIDER)` | `AIR_QUALITY_PROVIDER.WORLD_AIR_POLLUTION` |
| `apiKey(String)` | api key |
| `coordinate(double, double)` | Latitude and longitude of your place. If you want to use GPS, just remove this method. |

6. Get weather data. Current weather:
  ```java
  weatherClient.currentCondition(context, new CurrentWeatherListener() {
	@Override
	public void onSuccessful(City city, CurrentWeather weather) {
		...
	}

	@Override
	public void onFailure(int code, String message) {
		...
	}
});
  ```

Hourly weather:
```java
weatherClient.hourlyWeather(context, new HourlyWeatherListener() {
	@Override
	public void onSuccessful(City city, List<HourlyWeather> hourlyWeatherList) {
		...
	}

	@Override
	public void onFailure(int code, String message) {
		...
	}
});
```

Daily weather:
```java
weatherClient.dailyWeather(context, new DailyWeatherListener() {
	@Override
	public void onSuccessful(City city, List<DailyWeather> dailyWeatherList) {
		...
	}

	@Override
	public void onFailure(int code, String message) {
		...
	}
});
```

Today astronomy:
```java
weatherClient.todayAstronomy(context, new AstronomyListener() {
	@Override
	public void onSuccessful(City city, Astronomy astronomy) {
		...
	}

	@Override
	public void onFailure(int code, String message) {
		...
	}
});
```

All above data together:
```java
weatherClient.allWeather(context, new AllWeatherListener() {
	@Override
	public void onSuccessful(City city,
                 CurrentWeather weather,
                 Astronomy astronomy,
                 List<HourlyWeather> hourlyWeatherList,
                 List<DailyWeather> dailyWeatherList) {
		...
	}

	@Override
	public void onFailure(int code, String message) {
		...
	}
});
```

Air quality:
```java
airQualityClient.airQuality(new AirQualityListener() {
	@Override
	public void onSuccessful(AirQuality airQuality) {
		...
	}

	@Override
	public void onFailure(int code, String message) {
		...
	}
});
```
Data has its own value and unit. example:
```java
float temp = weather.temperature().getValue();	// example: 19.5
String tempUnit = weather.temperature().getUnit();	// example: °C
```  

weather condition:
```java
String desc = weather.condition().getDescription();		// example: Clear
String iconCode = weather.condition().getIcon().getCode();	// example: 1000d
String iconName = weather.condition().getIcon().getName();	// example: clear_day
int iconRes = weather.condition().getIcon().getIconRes();	// default library icon
```
Icon package: [Weather is Nice Today](https://www.iconfinder.com/iconsets/the-weather-is-nice-today).

| enum | Code | Name | Icon |
|--|:--:|:--:|:--:|
| CLEAR_DAY | 1000d | clear_day | <img src="/assets/clear_day.png" width="48" />  |
| CLEAR_NIGHT | 1000n | clear_night | <img src="/assets/clear_night.png" width="48" />  |
| PARTLY_CLOUDY_DAY | 1100d | partly_cloudy_day | <img src="/assets/partly_cloudy_day.png" width="48" />  |
| PARTLY_CLOUDY_NIGHT | 1100n | partly_cloudy_night | <img src="/assets/partly_cloudy_night.png" width="48" />  |
| MOSTLY_CLOUDY_DAY | 1200d | mostly_cloudy_day | <img src="/assets/mostly_cloudy_day.png" width="48" />  |
| MOSTLY_CLOUDY_NIGHT | 1200n | mostly_cloudy_night | <img src="/assets/mostly_cloudy_night.png" width="48" />  |
| CLOUDY | 1300 | cloudy | <img src="/assets/cloudy.png" width="48" />  |
| CLOUDY_WINDY | 1400 | cloudy_windy | <img src="/assets/cloudy_windy.png" width="48" />  |
| CLOUDY_WINDY_DAY | 1400d | cloudy_windy_day | <img src="/assets/cloudy_windy_day.png" width="48" />  |
| CLOUDY_WINDY_NIGHT | 1400n | cloudy_windy_night | <img src="/assets/cloudy_windy_night.png" width="48" />  |
| FOG | 1500 | fog | <img src="/assets/fog.png" width="48" />  |
| FOG_DAY | 1500d | fog_day | <img src="/assets/fog_day.png" width="48" />  |
| FOG_NIGHT | 1500n | fog_night | <img src="/assets/fog_night.png" width="48" />  |
| CLOUDY_FOG | 1600 | cloudy_fog | <img src="/assets/cloudy_fog.png" width="48" />  |
| CLOUDY_FOG_DAY | 1600d | cloudy_fog_day | <img src="/assets/cloudy_fog_day.png" width="48" />  |
| CLOUDY_FOG_NIGHT | 1600n | cloudy_fog_night | <img src="/assets/cloudy_fog_night.png" width="48" />  |
| DRIZZLE | 1700 | drizzle | <img src="/assets/drizzle.png" width="48" />  |
| LIGHT_RAIN | 1800 | light_rain | <img src="/assets/light_rain.png" width="48" />  |
| LIGHT_RAIN_DAY | 1800d | light_rain_day | <img src="/assets/light_rain_day.png" width="48" />  |
| LIGHT_RAIN_NIGHT | 1800n | light_rain_night | <img src="/assets/light_rain_night.png" width="48" />  |
| RAIN | 1900 | rain | <img src="/assets/rain.png" width="48" />  |
| RAIN_DAY | 1900d | rain_day | <img src="/assets/rain_day.png" width="48" />  |
| RAIN_NIGHT | 1900n | rain_night | <img src="/assets/rain_night.png" width="48" />  |
| HEAVY_RAIN | 2000 | heavy_rain | <img src="/assets/heavy_rain.png" width="48" />  |
| HEAVY_RAIN_DAY | 2000d | heavy_rain_day | <img src="/assets/heavy_rain_day.png" width="48" />  |
| HEAVY_RAIN_NIGHT | 2000n | heavy_rain_night | <img src="/assets/heavy_rain_night.png" width="48" />  |
|SHOWER_RAIN | 2100 | shower_rain | <img src="/assets/shower_rain.png" width="48" />  |
| SHOWER_RAIN_DAY | 2100d | shower_rain_day | <img src="/assets/shower_rain_day.png" width="48" />  |
| SHOWER_RAIN_NIGHT | 2100n | shower_rain_night | <img src="/assets/shower_rain_night.png" width="48" />  |
| LIGHT_SNOW | 2200 | light_snow | <img src="/assets/light_snow.png" width="48" />  |
| SNOW | 2300 | snow | <img src="/assets/snow.png" width="48" />  |
| SNOW_DAY | 2300d | snow_day | <img src="/assets/snow_day.png" width="48" />  |
| SNOW_NIGHT | 2300n | snow_night | <img src="/assets/snow_night.png" width="48" />  |
| HEAVY_SNOW | 2400 | heavy_snow | <img src="/assets/heavy_snow.png" width="48" />  |
| FREEZING_RAIN | 2500 | freezing_rain | <img src="/assets/freezing_rain.png" width="48" />  |
| FREEZING_RAIN_DAY | 2500d | freezing_rain_day | <img src="/assets/freezing_rain_day.png" width="48" />  |
| FREEZING_RAIN_NIGHT | 2500n | freezing_rain_night | <img src="/assets/freezing_rain_night.png" width="48" />  |
| THUNDERSTORM | 2600 | thunderstorm | <img src="/assets/thunderstorm.png" width="48" />  |
| THUNDERSTORM_WITH_RAIN | 2700 | thunderstorm_with_rain | <img src="/assets/thunderstorm_with_rain.png" width="48" />  |
| THUNDERSTORM_WITH_RAIN_DAY | 2700d | thunderstorm_with_rain_day | <img src="/assets/thunderstorm_with_rain_day.png" width="48" />  |
| THUNDERSTORM_WITH_RAIN_NIGHT | 2700n | thunderstorm_with_rain_night | <img src="/assets/thunderstorm_with_rain_night.png" width="48" />  |
| HAIL | 2800 | hail | <img src="/assets/hail.png" width="48" />  |
| HAIL_DAY | 2800d | hail_day | <img src="/assets/hail_day.png" width="48" />  |
| HAIL_NIGHT | 2800n | hail_night | <img src="/assets/hail_night.png" width="48" />  |
| HEAVY_HAIL | 2900 | heavy_hail | <img src="/assets/heavy_hail.png" width="48" />  |
| RAIN_AND_SNOW | 3000 | rain_and_snow | <img src="/assets/rain_and_snow-1.png" width="48" />  |
| SNOW_AND_HAIL | 3100 | snow_and_hail | <img src="/assets/snow_and_hail.png" width="48" />  |
| COLD | 3200 | cold | <img src="/assets/cold.png" width="48" />  |
| HOT | 3300 | hot | <img src="/assets/hot.png" width="48" />  |
| DUST | 3400 | dust | <img src="/assets/dust.png" width="48" />  |
| SMOKE | 3500 | smoke | <img src="/assets/smoke.png" width="48" />  |
| WINDY | 3600 | windy | <img src="/assets/windy.png" width="48" />  |
| SNOW_AND_WINDY | 3700 | snow_and_windy | <img src="/assets/snow_and_windy.png" width="48" />  |
| TORNADO | 3800 | tornado | <img src="/assets/tornado.png" width="48" />  |
| VOLCANO_ASH | 3900 | volcano_ash | <img src="/assets/volcano_ash.png" width="48" />  |
| NA | 0 | na | <img src="/assets/na.png" width="48" />  |

## proguard
If you want to use proguard, just this to end of `proguard-rules.pro` file:
```
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }

# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}
```

## License

      Copyright 2019, Saeed Motedaveli

      Licensed under the Apache License, Version 2.0 (the "License");
      you may not use this file except in compliance with the License.
      You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

      Unless required by applicable law or agreed to in writing, software
      distributed under the License is distributed on an "AS IS" BASIS,
      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
      See the License for the specific language governing permissions and
      limitations under the License.
