import React,{ Component } from 'react';
import { StyleSheet, Text, View, TouchableOpacity } from 'react-native';
import {CHOICES} from '../caseGame';


class GroupButton extends Component {
  render() {
    return (
      CHOICES.map(choice => {
        return (
          <TouchableOpacity
            style={styles.buttonStyle}
            onPress={() => this.props.onPress(choice.name)}
            key={choice.name} 
          >
            <Text style={styles.buttonText}>
              {choice.name.charAt(0).toUpperCase() + choice.name.slice(1)}
            </Text>
          </TouchableOpacity>
        );
      })
    );
  }
}

const styles = StyleSheet.create({
  buttonStyle: {
    width: 180,
    height: 52,
    borderRadius: 12,
    alignItems: 'center',
    justifyContent: 'center',
    margin: 7,
    backgroundColor: '#6B050D',
  },
  buttonText: {
    fontSize: 25,
    fontWeight: 'bold',
    color: 'white',
  },
});


  export default GroupButton;


