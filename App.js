import React, {Fragment,useEffect} from 'react';
import {
  SafeAreaView,
  StyleSheet,
  ScrollView,
  View,
  Text,
  Image,
  Alert
} from 'react-native';

import {
  Header,
  LearnMoreLinks,
  Colors,
  DebugInstructions,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';
import firebase from 'react-native-firebase';

const App = () => {
  useEffect(() => {
    this.checkPermission();
    this.messageListener();
  }, []);

  checkPermission = async () => {
    const enabled = await firebase.messaging().hasPermission();
    if (enabled) {
        this.getFcmToken();
    } else {
        this.requestPermission();
    }
  }

  getFcmToken = async () => {
    const fcmToken = await firebase.messaging().getToken();
    if (fcmToken) {
      console.log("Your Firebase Token is  "+fcmToken);
      this.showAlert('Your Firebase Token is:', fcmToken);
    } else {
      this.showAlert('Failed', 'No token received');
    }
  }

  requestPermission = async () => {
    try {
      await firebase.messaging().requestPermission();
      // User has authorised
    } catch (error) {
        // User has rejected permissions
    }
  }

  messageListener = async () => {

    this.notificationListener = firebase.notifications().onNotification((notification) => {
        const { title, body } = notification;
        this.showAlert(title, body);
    });

    this.notificationOpenedListener = firebase.notifications().onNotificationOpened((notificationOpen) => {
        const { title, body } = notificationOpen.notification;
        this.showAlert(title, body);
    });
  
    const notificationOpen = await firebase.notifications().getInitialNotification();
    if (notificationOpen) {
        const { title, body } = notificationOpen.notification;
        this.showAlert(title, body);
    }

    this.messageListener = firebase.messaging().onMessage((message) => {
      console.log("print this"+JSON.stringify(message));
    });
  }

  showAlert = (title, message) => {
    Alert.alert(
      title,
      message,
      [
        {text: 'OK', onPress: () => console.log('OK Pressed')},
      ],
      {cancelable: false},
    );
  }

  return (
    <View style={{alignContent:"center",alignItems:"center" }}>
                     
 <Image  style={{ height: 200,width:200 }} source={require('./firebase.png')} />
 <Text style={{ marginTop: 20,color:'red',fontSize:25 }}>Firebase push demo</Text>       
   
    </View>
   
  );
};



export default App;