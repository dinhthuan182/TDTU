import React,{ Component } from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { Table, Row, Rows } from 'react-native-table-component';

const GameTable = ({gameRate: {winMatch, defeatMatch, tieMatch, total, winRate, defeatRate, tieRate}}) =>{
  return (
    <View style = {styles.customTable}>
      <View style = {styles.columnTable}>
        <Text style = {styles.head}>Type</Text>
        <Text style = {styles.head}>Rate</Text>
        <Text style = {styles.head}>Quantity</Text>
      </View>
      <View style = {styles.columnTable}>
        <Text style = {styles.head}>Win</Text>
        <Text style = {styles.text}>{winRate}%</Text>
        <Text style = {styles.text}>{winMatch}/{total}</Text>
      </View>
      <View style = {styles.columnTable}>
        <Text style = {styles.head}>Tie</Text>
        <Text style = {styles.text}>{tieRate}%</Text>
        <Text style = {styles.text}>{tieMatch}/{total}</Text>
      </View>
      <View style = {styles.columnTable}>
        <Text style = {styles.head}>Defeat</Text>
        <Text style = {styles.text}>{defeatRate}%</Text>
        <Text style = {styles.text}>{defeatMatch}/{total}</Text>
      </View>
    </View>
  );
}

class GameState extends Component {
  render() {
    
    return (
      this.props.result === "Victory!" ?
      <View style= {styles.rateTable} updateGame={this.props.gameRate}>
        <GameTable gameRate = {this.props.gameRate}/>
        <View style = {styles.viewState}>
          <Text style={styles.textStateWin}>{this.props.result}</Text>
        </View>
      </View>
      :
      <View style= {styles.rateTable}>
        <GameTable gameRate = {this.props.gameRate}/>
        <View style = {styles.viewState}>
          <Text style={styles.textState}>{this.props.result}</Text>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  textStateWin: {
    fontSize: 33,
    fontWeight: 'bold',
    color: 'green',
    marginTop: 10,
  },
  textState: {
    fontSize: 33,
    fontWeight: 'bold',
    color: 'red',
    marginTop: 10,
  },
  rateTable: {
    marginTop: 38,
  },
  head: { 
    height: 30,
    width: 89,
    backgroundColor: '#f1f8ff',
    borderWidth: 1,
    justifyContent: 'center',
    alignItems: 'center',
    paddingTop: 6,
    paddingLeft: 15,
  },
  text: { 
    height: 30,
    width: 89,
    borderWidth: 1,
    paddingTop: 6,
    paddingLeft: 15,
  },
  viewState: {
    justifyContent: 'center',
    alignItems: 'center',
  },
  customTable: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginTop: 10,
  },
  columnTable: {
    justifyContent: 'center',
    alignItems: 'center',
  },
});
  
  export default GameState;


