import * as WebBrowser from 'expo-web-browser';
import React from 'react';
import {
  Image,
  Platform,
  ScrollView,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
} from 'react-native';
import messages from '../messages.json';

const MessageCard = () => {
  return messages.map(message => {
    return (
      <TouchableOpacity 
        style={styles.cardStyle}
        key = {message.id}
        onPress={() => props.onGoToConversation(props.message.id)}
        >
        <Image
            style={styles.avatar}
            source={require('../assets/images/robot-dev.png')}
          />
        <View style = {styles.cardContent}>
          <View style = {styles.conentItem}>
            <Text style={styles.senderName}>{message.from}</Text>
            <Text>{message.time}</Text>
          </View>
          <Text style={styles.content}>{message.content}</Text>
        </View>
      </TouchableOpacity>
    );
  });
}

export default function MessagesScreen() {

  return (
    <ScrollView style={styles.container}>
      <MessageCard/>
    </ScrollView>
  );
}

MessagesScreen.navigationOptions = {
  title: "Messages"
};


const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
  cardStyle: {
    flexDirection: 'row',
    borderRadius: 15,
    borderWidth: 1,
    margin: 4,
    padding: 5,
  },
  avatar: {
    height: 80,
    width: 80,
    borderRadius: 40,
    borderWidth: 1,

  },
  conentItem: {
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  senderName: {
    fontWeight: 'bold',
    fontSize: 20
  },
  content: {

  },
  cardContent: {
    marginLeft: 10,
  }
});
