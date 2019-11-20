import React, { Component, useState } from 'react';
import { StyleSheet, Text, View } from 'react-native';

import GameBoard from './components/GameBoard';
import GameState from './components/GameState';
import GroupButton from'./components/GroupButton';

import {CHOICES} from './caseGame';

const getRoundOutcome = userChoice => {
  const computerChoice = randomComputerChoice().name;
  let result;

  if (userChoice === 'rock') {
    result = computerChoice === 'scissors' ? 'Victory!' : 'Defeat!';
  }
  if (userChoice === 'paper') {
    result = computerChoice === 'rock' ? 'Victory!' : 'Defeat!';
  }
  if (userChoice === 'scissors') {
    result = computerChoice === 'paper' ? 'Victory!' : 'Defeat!';
  }

  if (userChoice === computerChoice) result = 'Tie game!';
  return [result, computerChoice];
};

const randomComputerChoice = () =>
  CHOICES[Math.floor(Math.random() * CHOICES.length)];

export default function App(){

  const [gamePrompt, setGamePrompt] = useState('Choose your weapon!');
  const [userChoice, setUserChoice] = useState({});
  const [computerChoice, setComputerChoice] = useState({});
  const [gameHistory, setGameHistory] = useState([]);
  const [gameRate, setGameRate] = useState({});

  const matchRate = () => {
    const winMatch = gameHistory.filter(x => x === "Victory!");
    const defeatMatch = gameHistory.filter(x => x === "Defeat!");
    const tieMatch = gameHistory.length - winMatch.length - defeatMatch.length;
    const total = gameHistory.length;
    const winRate = ((winMatch.length / total) *100).toFixed(0);
    const defeatRate = (defeatMatch.length / total *100).toFixed(0);
    const tieRate = (tieMatch / total *100).toFixed(0);
    setGameRate({
      winMatch: winMatch.length,
      defeatMatch: defeatMatch.length,
      tieMatch: tieMatch,
      total: total,
      winRate: winRate,
      defeatRate: defeatRate,
      tieRate: tieRate
    })
  };

  onPress = playerChoice => {
    const [result, compChoice] = getRoundOutcome(playerChoice);
    const newUserChoice = CHOICES.find(choice => choice.name === playerChoice);
    const newComputerChoice = CHOICES.find(choice => choice.name === compChoice);
    const newArray = gameHistory.push(result)
    
    
    setGameHistory(gameHistory);
    matchRate()
    setGamePrompt(result);
    setUserChoice(newUserChoice);
    setComputerChoice(newComputerChoice);
  };
    return (
      <View style={styles.container}>
        <View style={styles.styleGameState}>
          <GameState result = {gamePrompt} gameRate= {gameRate} />
        </View>
        <View style={styles.styleGameBoard}>
          <GameBoard player = {userChoice} computer = {computerChoice}/>
        </View>
        <View style={styles.btnGroup}>
          <GroupButton onPress = {this.onPress}/>
        </View>
      </View>
    );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  styleGameState: {
    flex:0.22,
    alignItems: 'center',
    justifyContent: 'center',
  },
  styleGameBoard: {
    flex: 0.45,
    justifyContent: 'center',
  },
  btnGroup: {
    flex: 0.33,
  }
});
