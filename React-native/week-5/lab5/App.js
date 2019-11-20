import React, { useEffect, useState } from 'react';
import { StyleSheet, Text, View, ActivityIndicator,  ImageBackground, TouchableOpacity } from 'react-native';
import { MaterialIcons, MaterialCommunityIcons } from "@expo/vector-icons";
import * as Location from "expo-location";
import * as Permissions from "expo-permissions";

const WeatherCard = ({ location, error, loading }) => { 
  const temperatureC = (location.main.temp - 273.15).toFixed(2); 
  const temperatureF = (((location.main.temp - 273.15) * 9) / 5 + 32).toFixed( 2 ); 
  const description = location.weather[0].description; 
  const windSpeed = location.wind.speed; 
  const icon = location.weather[0].main; 
  const capitalizedDescription = description && description.charAt(0).toUpperCase() + description.slice(1); 
  if (error) { 
    return (
      <View style={styles.container}>
        <Text> Error fetching weather! </Text>
      </View>
    ); 
  } 
  // return ( {loading && } {location.name} {windSpeed} {capitalizedDescription} {temperatureF} {temperatureC} ); 
  return (
    <View style={styles.container}>
      <View style={styles.weatherContainer}>
      {loading && <Loading/>}
        <View style={styles.row}>
          <MaterialIcons name="location-city" size={32} color={"white"} />
          <Text style={styles.text}>{location.name}</Text>
        </View>
        <View style={styles.row}>
          <MaterialCommunityIcons name="speedometer" size={29} color={"white"} />
          <Text style={styles.text}>{windSpeed}</Text>
        </View>
        <View style={styles.row}>
          <MaterialCommunityIcons name="weather-cloudy" size={29} color={"white"}/>
          <Text style={styles.text}> {capitalizedDescription} </Text>
        </View>
        <View style={styles.row}>
          <MaterialCommunityIcons name="oil-temperature" size={29} color={"white"}/>
          <MaterialCommunityIcons name="temperature-fahrenheit" size={25} color={"white"}/>
          <Text style={styles.text}>{temperatureF} - </Text>
          <MaterialCommunityIcons name="temperature-celsius" size={25} color={"white"}/>
          <Text style={styles.text}>{temperatureC}</Text>
        </View>
      </View>
    </View>
  ); 
};
const CitySelectionButtons = props => (
  <View style={styles.cityContainer}>
    <TouchableOpacity
      key="currentLocation"
      style={styles.currentLocation}
      onPress={() => props.onChooseCity("")}
    >
      <Text style={styles.cityName}>Current Location</Text>
    </TouchableOpacity>
    {CITIES.map(city => {
      return (
        <TouchableOpacity
          key={city.name}
          style={styles.cityButton}
          onPress={() => props.onChooseCity(city.name)}
        >
          <Text style={styles.cityName}>{city.name}</Text>
        </TouchableOpacity>
      );
    })}
  </View>
);
const Loading = () => (
  <View style={styles.loading}>
    <ActivityIndicator />
  </View>
);

export default function App() {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [location, setLocation] = useState({
    name: "",
    main: { temp: "" },
    wind: { speed: "" },
    weather: [{ main: "", description: "" }]
  });
  useEffect(() => {
    getLocationAsync();
  }, []);

  getLocationAsync = async () => {
    const { status } = await Permissions.askAsync(Permissions.LOCATION);
    if (status !== "granted") {
      return;
    }
  
    const location = await Location.getCurrentPositionAsync();
    getWeather(location.coords.latitude, location.coords.longitude);
  };

  getWeather = async (latitude, longitude, imgUrl = "") => {
    setLoading(true);
    const API_KEY = "1c1b57d184226d91543a03b163a65709";
    const api = `http://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=${API_KEY}`;
  
    try {
      const response = await fetch(api);
      const jsonData = await response.json();
      setLocation({ ...jsonData, imgUrl });
    } catch (error) {
      setError(true);
    }
    setLoading(false);
  };
  onChooseCity = name => {
    let randImg = "";
    if (name !== "") {
      const city = CITIES.find(city => city.name === name);
      randImg = city.imgUrl[Math.floor(Math.random() * city.imgUrl.length)];
      getWeather(city.latitude, city.longitude, randImg);
    } else {
      getLocationAsync();
    }
  };
  return (
    <ImageBackground source={bgImage} style={styles.bg}>
      <WeatherCard location={location} error={error} loading={loading} />
      <CitySelectionButtons onChooseCity={onChooseCity} />
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  bg: {
    flex: 1,
    width: "100%",
    height: "100%",
    backgroundColor: "black"
  },
  container: {
    alignItems: "center",
    justifyContent: "center"
  },
  weatherContainer: {
    padding: 20,
    width: "90%",
    borderWidth: 1,
    maxWidth: "90%",
    minHeight: "20%",
    marginTop: "70%",
    borderRadius: 25,
    marginBottom: "2%",
    borderColor: "white",
    backgroundColor: "rgba(0,0,0,0.4)"
  },
  text: {
    fontSize: 20,
    color: "white",
    marginLeft: 10,
    fontWeight: "bold"
  },
  row: {
    alignItems: "center",
    flexDirection: "row",
    justifyContent: "center"
  },
  cityContainer: {
    flex: 1,
    flexWrap: "wrap",
    flexDirection: "row",
    justifyContent: "space-around"
  },
  cityName: {
    fontSize: 12,
    color: "white",
    fontWeight: "bold",
    textAlign: "center"
  },
  cityButton: {
    margin: 3,
    height: 40,
    padding: 3,
    width: "25%",
    borderWidth: 1,
    borderRadius: 10,
    borderColor: "white",
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "rgba(0,0,0,0.5)"
  },
  tempRow: {
    alignSelf: "center",
    flexDirection: "row"
  },
  locationText: {
    fontSize: 25,
    color: "white",
    marginLeft: 10,
    fontWeight: "bold",
    textDecorationLine: "underline"
  },
  loading: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center"
  },
  currentLocation: {
    margin: 3,
    height: 40,
    padding: 3,
    width: "72.5%",
    borderWidth: 1,
    borderRadius: 10,
    borderColor: "white",
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: "rgba(20,33,61,0.6)"
  }
});

