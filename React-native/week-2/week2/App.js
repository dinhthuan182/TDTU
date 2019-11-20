import React from 'react';
import { StyleSheet, Text, View, Image, Button, TouchableOpacity, ScrollView } from 'react-native';
import { MaterialIcons } from '@expo/vector-icons';

const POLO_BLUE_COLOR = 'rgb(51,60,87)';
const FOLLOW_COLOR = '#1a8cff';
const SEND_MESSAGE_COLOR = 'rgb(120,213,250)';

const imgData = [
  { id: 1, imgSource: require('./assets/img1.jpeg') },
  { id: 2, imgSource: require('./assets/img2.jpeg') },
  { id: 3, imgSource: require('./assets/img3.jpg') },
  { id: 4, imgSource: require('./assets/img4.jpeg') },
  { id: 5, imgSource: require('./assets/img5.jpg') },
  { id: 6, imgSource: require('./assets/img6.jpg') },
  { id: 7, imgSource: require('./assets/img7.jpeg') },
  { id: 8, imgSource: require('./assets/img8.jpg') }
];
const centerImgData = Math.floor(imgData.length / 2);

export default function App() {
  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <TouchableOpacity style={styles.imgHeader}>
          <Image
          source={require('./assets/avatar.png')}
          style={styles.avatarShow}
          />
        </TouchableOpacity>
        <View style={styles.userInfo}>
          <Text style={styles.nameText}>Thuan Vu</Text>
          <Text style={styles.jobText}> IT-er </Text>
          <View style={styles.buttonGroup}>
            <TouchableOpacity style={styles.buttonFollow}>
              <Text style={styles.followText}>Follow</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.buttonSend}>
            <MaterialIcons name="send" size={27} color="white" />
            </TouchableOpacity>
          </View>
        </View>

      </View>

      <View style={styles.countLike}>
        <TouchableOpacity>
          <Text style={styles.numberGroup}>2010</Text>
          <Text style={styles.textGroup}>Photos</Text>
        </TouchableOpacity>
        <TouchableOpacity>
          <Text style={styles.numberGroup}>100K</Text>
          <Text style={styles.textGroup}>Followers</Text>
        </TouchableOpacity>
        <TouchableOpacity>
          <Text style={styles.numberGroup}>15K</Text>
          <Text style={styles.textGroup}>Followings</Text>
        </TouchableOpacity>
      </View>
      <ScrollView style={styles.scrollFlex}>
        <View style={styles.viewPost}>
          <View style={styles.viewOdd}>
            {imgData.slice(0, centerImgData).map( item =>{
              return <TouchableOpacity>
              <Image 
                style={styles.imgOdd}
                source={item.imgSource} />
              </TouchableOpacity>
              })  
            }
          </View>
          <View style={styles.viewEven}>
          {imgData.slice(centerImgData).map( item =>{
              return <TouchableOpacity>
              <Image 
                style={styles.imgOdd}
                source={item.imgSource} />
              </TouchableOpacity>
              })
            }
          </View>
        </View>
      </ScrollView>
      
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    marginTop: 30,
  },
  header: {
    flex: 0.28,
    backgroundColor: 'white',
    flexDirection: 'row',
    alignContent: 'space-around'
  },
  countLike: {
    flex: 0.15,
    flexDirection: 'row',
    justifyContent: 'space-around',
    alignItems: 'center',
    marginTop: 20,
  },
  viewPost: {
    flex: 0.57,
    flexDirection: 'row',
    alignContent: 'stretch',
  },
  avatarShow: {
    width: 130,
    height: 130,
    borderRadius: 75,
    shadowColor: "#000",
    shadowOffset: {
      width: 0,
      height: 4,
    },
    shadowOpacity: 0.30,
    shadowRadius: 4.65,

    elevation: 8,
  },
  buttonGroup: {
    flexDirection: 'row',
  },
  buttonFollow: {
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: FOLLOW_COLOR,
    width: 110,
    height: 35,
    borderRadius: 25,
    marginRight: 10,
    shadowColor: "#000",
    shadowOffset: {
      width: 0,
      height: 4,
    },
    shadowOpacity: 0.30,
    shadowRadius: 4.65,
    
    elevation: 8,
  },
  buttonSend: {
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: SEND_MESSAGE_COLOR,
    width: 63,
    height: 35,
    borderRadius: 25,
    marginLeft: 7,
  },
  userInfo: {
    justifyContent: 'center'
  },
  imgHeader: {
    justifyContent: 'center',
    marginTop: 30,
    marginLeft: 20,
    marginRight: 10,
  },
  nameText:{
    fontSize: 27,
    fontWeight: 'bold',
    marginBottom: 5,
    marginTop: 25,
  },
  jobText: {
    marginBottom: 20,
    fontSize: 18,
    color: 'grey',
  },
  followText: {
    fontWeight: 'bold',
    fontSize: 17,
    color: '#696969',
  },
  numberGroup: {
    fontSize: 21,
    fontWeight: 'bold'
  },
  textGroup:{
    fontSize: 13,
    color:'grey',
  },
  viewOdd: {
    flex:0.5,
    alignItems: 'center',
    marginLeft: 7,
  },
  viewEven: {
    flex: 0.5,
    alignItems: 'center',
    marginRight: 10,
  },
  imgOdd: {
    height: 170,
    width: 165,
    borderRadius: 10,
    marginBottom: 10,
  },
  imgEven: {
    height: 165,
    width: 165,
    borderRadius: 10,
    marginBottom: 7,
  },
  imgOddColumn: {
    height: 190,
    width: 165,
    borderRadius: 10,
    marginBottom: 7,
  },
  imgEvenColumn: {
    height: 190,
    width: 165,
    borderRadius: 10,
    marginBottom: 7,
  },
  scrollFlex: {
    flex: 0.57,
  },
});
