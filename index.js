/**
 * @format
 */
import bgMessaging from './bgMessaging'; 
import {AppRegistry} from 'react-native';
import firebase from 'react-native-firebase';
import App from './App';
import {name as appName} from './app.json';
// Current main application
// Current main application
AppRegistry.registerComponent('ReactNativeFirebaseDemo', () => bootstrap);
// New task registration
AppRegistry.registerHeadlessTask('RNFirebaseBackgroundMessage', () => bgMessaging); // <-- Add this line
AppRegistry.registerComponent(appName, () => App);
//AppRegistry.registerHeadlessTask('ReactNativeFirebaseMessagingHeadlessTask', () => backgroundHandler)
