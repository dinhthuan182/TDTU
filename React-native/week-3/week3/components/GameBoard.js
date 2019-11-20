import React,{ Component, useState } from 'react';
import { StyleSheet, Text, View, Image } from 'react-native';
import {CHOICES} from '../caseGame';

const ChoiceCard = ({ player, choice: { uri, name } }) => {
  const title = name && name.charAt(0).toUpperCase() + name.slice(1);

  return (
    <View style={styles.cardPlayer}>
      <Text style={styles.choiceDescription}>{player}</Text>
      <Image source= {{uri: uri}} resizeMode="contain" style={styles.choiceImage} />
      <Text style={styles.choiceCardTitle}>{title}</Text>
    </View>
  );
};

class GameBoard extends React.Component {  
  render() {
    return (
      <View style={styles.choiceContainer}>
        <ChoiceCard player="Player" choice={this.props.player} />
          <Text style={styles.vsLetter}>vs</Text>
        <ChoiceCard player="Computer" choice={this.props.computer} />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  cardPlayer: {
    alignItems: 'center',
  },
  choiceContainer: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  choiceDescription: {
    fontSize: 30,
    fontWeight: 'bold',
    color: '#6B050D',
  },
  choiceImage: {
    width: 180,
    height: 180,
  },
  choiceCardTitle: {
    fontSize: 25,
    color: '#6B050D',
  },
  vsLetter: {
    fontSize: 22,
    fontWeight: 'bold',
  },
});
  
export default GameBoard;


