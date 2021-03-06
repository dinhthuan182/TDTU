import React from 'react';
import { Platform } from 'react-native';
import { createStackNavigator, createBottomTabNavigator } from 'react-navigation';

import TabBarIcon from '../components/TabBarIcon';
import MessagesScreen from '../screens/MessagesScreen';
import ContactsScreen from '../screens/ContactsScreen';
import GroupsScreen from '../screens/GroupsScreen';
import TimelineScreen from '../screens/TimelineScreen';
import MoreScreen from '../screens/MoreScreen';

const config = Platform.select({
  web: { headerMode: 'screen' },
  default: {},
});

const MessagesStack = createStackNavigator(
  {
    Home: MessagesScreen,
  },
  config
);

MessagesStack.navigationOptions = {
  tabBarLabel: "Messages",
  tabBarIcon: ({ focused }) => (
    <TabBarIcon
      focused={focused}
      name={Platform.OS === "ios" ? "ios-chatbubbles" : "android-messages"}
    />
  )
};

MessagesStack.path = '';

const ContactsStack = createStackNavigator(
  {
    Contacts: ContactsScreen,
  },
  config
);

ContactsStack.navigationOptions = {
  tabBarLabel: 'Contacts',
  tabBarIcon: ({ focused }) => (
    <TabBarIcon focused={focused} name={Platform.OS === 'ios' ? 'ios-contacts' : 'android-close'} />
  ),
};

ContactsStack.path = '';

const GroupsStack = createStackNavigator(
  {
    Groups: GroupsScreen,
  },
  config
);

GroupsStack.navigationOptions = {
  tabBarLabel: 'Groups',
  tabBarIcon: ({ focused }) => (
    <TabBarIcon focused={focused} name={Platform.OS === 'ios' ? 'md-contacts' : 'md-contacts'} />
  ),
};

GroupsStack.path = '';


const TimelineStack = createStackNavigator(
  {
    Timeline: TimelineScreen,
  },
  config
);

TimelineStack.navigationOptions = {
  tabBarLabel: 'Timeline',
  tabBarIcon: ({ focused }) => (
    <TabBarIcon focused={focused} name={Platform.OS === 'ios' ? 'ios-home' : 'ios-home'} />
  ),
};

TimelineStack.path = '';

const MoreStack = createStackNavigator(
  {
    Settings: MoreScreen,
  },
  config
);

MoreStack.navigationOptions = {
  tabBarLabel: 'More',
  tabBarIcon: ({ focused }) => (
    <TabBarIcon focused={focused} name={Platform.OS === 'ios' ? 'ios-options' : 'md-options'} />
  ),
};

MoreStack.path = '';


const tabNavigator = createBottomTabNavigator({
  MessagesStack,
  ContactsStack,
  GroupsStack,
  TimelineStack,
  MoreStack,
});

tabNavigator.path = '';

export default tabNavigator;
