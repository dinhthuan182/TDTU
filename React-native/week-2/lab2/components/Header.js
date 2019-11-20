import React, { Component } from 'react';
import { StyleSheet, Text, View, Image, TouchableOpacity } from 'react-native';
import { Feather } from '@expo/vector-icons';
class Header extends Component {
	render() {
    return (
			<View style={styles.navHeader}>
        <TouchableOpacity
          onPress={() => alert("Into header feed")}
          style={styles.imgHeader}>
          <Image
            source={{
              uri:
                'https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Instagram_logo.svg/1200px-Instagram_logo.svg.png'
            }}
            style={styles.imgHeader}
            resizeMode="contain"
          />
        </TouchableOpacity>
        <TouchableOpacity
          onPress={() => alert("InBox")}
          style={styles.btnInbox}>
        <Feather name="inbox" size={27} color="black"  />
        </TouchableOpacity>

      </View>
      
    );
  }
}


const styles = StyleSheet.create({
  navHeader: {
    flex: 0.3,
    paddingTop: 30,
    flexDirection: 'row',
    backgroundColor: '#ebf5f5',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  imgHeader: {
		flex: 1,
		width: null,
		height: 40
  },
  btnInbox: {
    marginRight: 15,
  },
});


export default Header;