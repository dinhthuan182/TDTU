import React, { Component } from 'react';
import { StyleSheet, Text, View, TouchableOpacity } from 'react-native';
import { AntDesign } from '@expo/vector-icons';
import { Feather } from '@expo/vector-icons';
class ButtonList extends Component {
	render() {
    return (
    <View style={styles.lists}>
      <View style={styles.btnList}>
        <TouchableOpacity 
        onPress={() => alert("Liked")}>
        <AntDesign name="hearto" size={27} color="black" style={styles.fixBtn} />
        </TouchableOpacity>
        <Text style={styles.countLike}>127</Text>
      </View>
      <View style={styles.btnList}>
        <TouchableOpacity 
        onPress={() => alert("Comment")}
        style={styles.fixBtn}>
          <AntDesign name="message1" size={27} color="black" />
        </TouchableOpacity>
        <TouchableOpacity 
        onPress={() => alert("Sharing")}
        style={styles.fixBtn}>
          <Feather name="share" size={27} color="black" />
        </TouchableOpacity>
      </View>
    </View>
    );
  }
}

const styles = StyleSheet.create({
  btnList: {
    flexDirection: 'row',
    alignItems: 'center'
  },
  lists: {
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  fixBtn: {
    margin: 10,
  },
  countLike: {
    fontWeight: 'bold',
    fontSize: 16,
  },
});

export default ButtonList;