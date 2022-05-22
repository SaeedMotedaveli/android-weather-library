# MT Weather Library
#### An android library for request weather from different providers
*For testing library, please have a look at the Demo Project (demo) or download apk file from [here](https://github.com/SaeedMotedaveli/android-weather-library/releases).*

<img src="/assets/demo-preview.png" />

# Supported providers

1. AccuWeather
2. Dark Sky
3. Open Weather
4. Weatherbit
5. World Weather Online
6. World's Air Pollution

# Usage

1. Include the library as local library project.

   ```
   dependencies {
      implementation 'ir.mtapps:WeatherLib:0.9.0'
   }
   ```

2. Use default Configuration:

   ```java
   WeatherConfig config = WeatherConfig.create();
   ```

   Or create your configuration:

   ```java
   String language = "en";   // Language of weather data
   UNIT_SYSTEM unit_system = UNIT_SYSTEM.METRIC; // or UNIT_SYSTEM.IMPERIAL. You can also change unit for every parameter.

   WeatherConfig config = WeatherConfig.edit()
      .language(language)
      .unitSystem(unit_system)
      .disableCache() // NOTE: caching data enable by default
      .create();
   ```

3. Create client. For weather client:

   ```java
   WeatherClient client = new WeatherClient.Builder()
      .attach(context)
      .config(config)
      .provider(provider) // PROVIDER.ACCUWEATHER, PROVIDER.DARK_SKY, ...
      .apiKey(key)  // provider api key
      .coordinate(latitude, longitude)  // remove it if you want to use GPS
      .build();
   ```

   For air quality:

   ```java
   AirQualityClient aqClient = new AirQualityClient.Builder()
      .attach(context)
      .config(config)
      .provider(provider) // AIR_QUALITY_PROVIDER.WORLD_AIR_POLLUTION
      .apiKey(key)  // provider api key
      .coordinate(latitude, longitude)  // remove it if you want to use GPS
      .build();
   ```

4. Get current weather:

   ```java
   client.currentCondition(new CurrentWeatherListener() {
      @Override
      public void onSuccessful(City city, CurrentWeather weather) {
         // do something with city or weather
         // ...
      }

      @Override
      public void onError(int code, String message) {
         // do something with error
         // ...
      }
   });
   ```

   or other options like ``` client.todayAstronomy() ```, ``` client.hourlyWeather() ``` and ``` client.dailyWeather() ```.
   If you want get all this data in one request, use ``` client.allWeather() ```.

   ```java
   client.allWeather(new AllWeatherListener() {
      @Override
      public void onSuccessful(City city,
                               CurrentWeather weather,
                               Astronomy astronomy,
                               List<HourlyWeather> hourlyWeatherList,
                               List<DailyWeather> dailyWeatherList) {
         // do something with this data
         // ...
      }

      @Override
      public void onError(int code, String message) {
         // do something with error
         // ...
      }
   });
   ```

   For get air quality use this method:

   ```java
   aqClient.airQuality(new AirQualityListener() {
      @Override
      public void onSuccessful(AirQuality airQuality) {
          // do something with airQuality
          // ...
      }

      @Override
      public void onError(int code, String message) {
         // do something with error
         // ...
      }
   });
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
