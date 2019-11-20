import React, { Component } from 'react';
import { StyleSheet, Text, View, Image, TouchableOpacity } from 'react-native';
import ButtonList from './ButtonList';

const imgData = [
	{
    id: 1,
    name: 'CoderSchool',
    image: require('../assets/img1.jpeg'),
    likeCount: 128,
    avatar: require('../assets/img1.jpeg')
	},
	{
    id: 2,
    name: 'CoderSchool',
    image: require('../assets/img2.jpeg'),
    likeCount: 30,
    avatar: require('../assets/img2.jpeg')
	},
	{
    id: 3,
    name: 'CoderSchool',
    image: require('../assets/img3.jpg'),
    likeCount: 157,
    avatar: require('../assets/img3.jpg')
	},
	{
    id: 4,
    name: 'CoderSchool',
    image: require('../assets/img4.jpeg'),
    likeCount: 324,
    avatar: require('../assets/img4.jpeg')
	},
	{
    id: 5,
    name: 'CoderSchool',
    image: require('../assets/img5.jpg'),
    likeCount: 95,
    avatar: require('../assets/img5.jpg')
	},
	{
    id: 6,
    name: 'CoderSchool',
    image: require('../assets/img6.jpg'),
    likeCount: 85,
    avatar: require('../assets/img6.jpg')
	},
	{
    id: 7,
    name: 'CoderSchool',
    image: require('../assets/img7.jpeg'),
    likeCount: 142,
    avatar: require('../assets/img7.jpeg')
	},
	{
    id: 8,
    name: 'CoderSchool',
    image: require('../assets/img8.jpg'),
    likeCount: 76,
    avatar: require('../assets/img8.jpg')
	},
	{
    id: 9,
    name: 'CoderSchool',
    image: require('../assets/img9.jpg'),
    likeCount: 78,
    avatar: require('../assets/img9.jpg')
	},
	{
    id: 10,
    name: 'CoderSchool',
    image: require('../assets/img10.jpg'),
    likeCount: 145,
    avatar: require('../assets/img10.jpg')
	},
	{
    id: 11,
    name: 'CoderSchool',
    image: require('../assets/img11.jpg'),
    likeCount: 234,
    avatar: require('../assets/img11.jpg')
  }
];

class BodyContent extends Component {
	render() {
		return(
			imgData.map(feed => {
				return (
					<View style={styles.bodyView} key={feed.id}>
						<TouchableOpacity 
							onPress={() => alert("View user profile")}
							style={styles.cardPerson}>
							<Image
								source={feed.avatar}
								style={styles.imgAvatar}
								resizeMode="cover"/>
							<Text style={styles.textName}>{feed.name}</Text>
						</TouchableOpacity>
						<TouchableOpacity 
							onPress={() => alert("View Image")}
							style={styles.imgPostCard}>
							<Image
								source={feed.image}
								style={styles.imgPost}
								resizeMode="cover"/>
						</TouchableOpacity>
						<ButtonList/>
					</View>
				);
			})
		);
  }
}


const styles = StyleSheet.create({
	bodyView: {
		flex: 0.7,
		flexDirection: 'column',
		backgroundColor: '#f0ffff',
		borderRadius: 13,
		marginBottom: 2,
		marginTop: 2,
	},
	cardPerson: {
		flexDirection: 'row',
		alignItems: 'center',
		margin: 10,
	},
	imgAvatar: {
		width: 50,
		height: 50,
		borderRadius: 25,
		marginRight: 10,
	},
	textName: {
		fontSize: 20,
		fontWeight: 'bold',
	},
	imgPostCard: {
		flex: 1,
		flexDirection: 'column',
		alignItems: 'center',
	},
	imgPost: {
		width: 365,
		height: 350,
		borderRadius: 10,
	},

});


export default BodyContent;