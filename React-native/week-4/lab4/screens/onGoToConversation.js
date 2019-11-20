import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import messages from '../messages.json';
const onGoToConversation = props => {
  return (
    <View style={styles.container}>
      <Text style={styles.headerText}>
        name
      </Text>
      <Text style={styles.bodyText}>body</Text>
    </View>
  );
};

onGoToConversation.navigationOptions = {
  title: 'onGoToConversation'
};

export default onGoToConversation;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center'
  },
  headerContainer: {
    flexDirection: 'row'
  },
  headerText: {
    fontSize: 30
  },
  bodyText: {
    fontSize: 50
  }
});