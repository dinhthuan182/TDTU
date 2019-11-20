import React, { Component } from 'react';
import { StyleSheet, Text, View, Platform } from 'react-native';
import MapView, { PROVIDER_GOOGLE, Marker } from 'react-native-maps';
import Constants from 'expo-constants';
import * as Location from 'expo-location';
import * as Permissions from 'expo-permissions';


export default class App extends Component {
  state = {
    location: null,
    errorMessage: null,
  };

  componentWillMount() {
    if (Platform.OS === 'android' && !Constants.isDevice) {
      this.setState({
        errorMessage: 'Oops, this will not work on Sketch in an Android emulator. Try it on your device!',
      });
    } else {
      this._getLocationAsync();
    }
  }

  _getLocationAsync = async () => {
    let { status } = await Permissions.askAsync(Permissions.LOCATION);
    if (status !== 'granted') {
      this.setState({
        errorMessage: 'Permission to access location was denied',
      });
    }

    let location = await Location.getCurrentPositionAsync({});
    latitude = parseFloat(JSON.stringify(location.coords.latitude));
    longitude = parseFloat(JSON.stringify(location.coords.longitude));
    this.mapView.animateToRegion({
      latitude, longitude,
      latitudeDelta: 0.0622,
      longitudeDelta: 0.0221,
    }, 3000);

  };

  render() {
    return (
      <MapView
        ref={c => this.mapView = c}
        style={{ flex: 1 }}
        provider={PROVIDER_GOOGLE}
      >
        {this.state.markers.map((marker:any)  => (  
              <MapView.Marker
                key={marker.id}
                coordinate={marker.coordinate}
                title={marker.title}
                description={marker.description}
              />
         }
      </MapView>

    );
  }
}


const styles = StyleSheet.create({
  container: {
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  fullMap: {
    width: '100%',
    height: '100%',
  },
});
