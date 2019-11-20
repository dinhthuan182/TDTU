import React, { Component } from 'react';
import { StyleSheet, Text, View, Image, ScrollView } from 'react-native';
import Header from './components/Header';
import BodyContent from './components/BodyContent';
export default function App() {
  return (
    <View style={styles.container}>
        <Header/>
        <ScrollView>
          <BodyContent/>
        </ScrollView>


    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'stretch',
    justifyContent: 'center',
  },
});
