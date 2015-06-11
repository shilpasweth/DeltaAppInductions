package com.shilpasweth.simpleweather;


import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherFragment extends Fragment {
        Typeface weatherFont;

        TextView cityField;
        TextView updatedField;
        TextView detailsField;
        TextView currentTemperatureField;
        TextView weatherIcon;

        Handler handler;

        public WeatherFragment(){
                handler = new Handler();
        }

        private void setWeatherIcon(int actualId, long sunrise, long sunset){
                int id = actualId / 100;
                String icon = "";
                long currentTime = new Date().getTime();
                if(currentTime>=sunrise && currentTime<sunset) {
                        switch(id) {
                                case 2 : icon = getActivity().getString(R.string.day_thunderstorm);
                                        break;
                                case 3 : icon = getActivity().getString(R.string.day_drizzle);
                                        break;
                                case 5 : icon = getActivity().getString(R.string.day_rain);
                                        break;
                                case 6 : icon = getActivity().getString(R.string.day_snow);
                                        break;
                        }
                        switch(actualId){
                                case 701 :
                                case 741 : icon = getActivity().getString(R.string.day_fog);
                                        break;
                                case 711 :
                                case 721 :
                                case 731 :
                                case 751 :
                                case 761 :
                                case 762 :
                                case 771 : icon = getActivity().getString(R.string.dust);
                                        break;
                                case 781 :
                                case 900 :
                                case 902 :
                                case 962 : icon = getActivity().getString(R.string.tornado);
                                        break;
                                case 800 : icon = getActivity().getString(R.string.day_clear);
                                        break;
                                case 801 :
                                case 802 :
                                case 803 : icon = getActivity().getString(R.string.day_cloudy);
                                        break;
                                case 901 : icon = getActivity().getString(R.string.storm);
                                        break;
                                case 903 :
                                case 905 :
                                case 951 :
                                case 953 :
                                case 954 :
                                case 955 :
                                case 956 :
                                case 957 :
                                case 958 :
                                case 959 :
                                case 960 :
                                case 961 : icon = getActivity().getString(R.string.wind);
                                        break;
                                case 904 : icon = getActivity().getString(R.string.hot);
                                        break;
                                case 906 : icon = getActivity().getString(R.string.hail);
                                        break;

                        }
                } else {
                        switch(id) {

                                case 2 : icon = getActivity().getString(R.string.night_thunderstorm);
                                        break;
                                case 3 : icon = getActivity().getString(R.string.night_drizzle);
                                        break;
                                case 5 : icon = getActivity().getString(R.string.night_rain);
                                        break;
                                case 6 : icon = getActivity().getString(R.string.night_snow);
                                        break;
                        }
                        switch(actualId){
                                case 701 :
                                case 741 : icon = getActivity().getString(R.string.night_fog);
                                        break;
                                case 711 :
                                case 721 :
                                case 731 :
                                case 751 :
                                case 761 :
                                case 762 :
                                case 771 : icon = getActivity().getString(R.string.dust);
                                        break;
                                case 781 :
                                case 900 :
                                case 902 :
                                case 962 : icon = getActivity().getString(R.string.tornado);
                                        break;
                                case 800 : icon = getActivity().getString(R.string.night_clear);
                                        break;
                                case 801 :
                                case 802 :
                                case 803 : icon = getActivity().getString(R.string.night_cloudy);
                                        break;
                                case 901 : icon = getActivity().getString(R.string.storm);
                                        break;
                                case 903 :
                                case 905 :
                                case 951 :
                                case 953 :
                                case 954 :
                                case 955 :
                                case 956 :
                                case 957 :
                                case 958 :
                                case 959 :
                                case 960 :
                                case 961 : icon = getActivity().getString(R.string.wind);
                                        break;
                                case 904 : icon = getActivity().getString(R.string.hot);
                                        break;
                                case 906 : icon = getActivity().getString(R.string.hail);
                                        break;
                        }
                }
                weatherIcon.setText(icon);
        }



        private void renderWeather(JSONObject json){
                try {
                        cityField.setText(json.getString("name").toUpperCase(Locale.US) +
                                ", " +
                                json.getJSONObject("sys").getString("country"));

                        JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                        JSONObject main = json.getJSONObject("main");
                        detailsField.setText("\n\n"+
                                details.getString("description").toUpperCase(Locale.US) +
                                        "\n" + "Humidity: " + main.getString("humidity") + "%" +
                                        "\n" + "Pressure: " + main.getString("pressure") + " hPa");

                        currentTemperatureField.setText(
                                String.format("%.2f", main.getDouble("temp"))+ " ℃");

                        DateFormat df = DateFormat.getDateTimeInstance();
                        String updatedOn = df.format(new Date(json.getLong("dt")*1000));
                        updatedField.setText("Last update: " + updatedOn);

                        setWeatherIcon(details.getInt("id"),
                                json.getJSONObject("sys").getLong("sunrise") * 1000,
                                json.getJSONObject("sys").getLong("sunset") * 1000);

                }catch(Exception e){
                        Log.e("SimpleWeather", "One or more fields not found in the JSON data");
                }
        }

        private void updateWeatherData(final String city){
                new Thread(){
                        public void run(){
                                final JSONObject json = RemoteFetch.getJSON(getActivity(), city);
                                if(json == null){
                                        handler.post(new Runnable(){
                                                public void run(){
                                                        Toast.makeText(getActivity(),
                                                                getActivity().getString(R.string.place_not_found),
                                                                Toast.LENGTH_LONG).show();
                                                }
                                        });
                                } else {
                                        handler.post(new Runnable(){
                                                public void run(){
                                                        renderWeather(json);
                                                }
                                        });
                                }
                        }
                }.start();
        }

        public void changeCity(String city){
                updateWeatherData(city);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
                cityField = (TextView) rootView.findViewById(R.id.city_field);
                updatedField = (TextView)rootView.findViewById(R.id.updated_field);
                detailsField = (TextView)rootView.findViewById(R.id.details_field);
                currentTemperatureField = (TextView)rootView.findViewById(R.id.current_temperature_field);
                weatherIcon = (TextView)rootView.findViewById(R.id.weather_icon);

                weatherIcon.setTypeface(weatherFont);
                return rootView;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weather.ttf");
                updateWeatherData(new CityPreference(getActivity()).getCity());
        }
}