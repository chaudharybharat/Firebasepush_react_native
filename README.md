# Firebasepush_react_native

 

<h2> Below intall package </h2><br>
<h5>npm install --save react-native-firebase </h5>
<br>
<h5>react-native link react-native-firebase </h5>
<br><br>
<h3>Setup React Native Firebase on Android</h3>
<br>
Copy the previously downloaded google-services.json to `android/app/` folder.
<h5>cp ~/Downloads/google-services.json android/app/</h5>
<br>
Next, open and edit `android/build.gradle` then add this classpath dependency of Google Services.
<h5>dependencies {
 <br>
    classpath("com.android.tools.build:gradle:3.4.1")
 <br>
    classpath 'com.google.gms:google-services:4.2.0'
 <br>
}</h5>
<br>
Also, add the Google maven repository at repositories block and make orders like this.
<h5>repositories {<br>
    google()<br>
    maven {<br>
        url 'https://maven.google.com'<br>
    }<br>
    jcenter()<br>
}</h5>
<br>
Open and edit `android/app/build.gradle` then add this line to the bottom of the file.
<br>
<h5apply plugin: "com.google.gms.google-services"</5>

Add these lines of Firebase implementation to the dependencies.
<h5>
dependencies {<br>
    ....
    implementation "com.google.android.gms:play-services-base:16.1.0"<br>
    implementation "com.google.firebase:firebase-core:17.0.1"<br>
    implementation "com.google.firebase:firebase-messaging:19.0.1"<br>
    implementation 'me.leolin:ShortcutBadger:1.1.21@aar'<br>
    ....
}</h5>

Next, open and edit `android/app/src/main/java/com/djamware/reactnative/MainApplication.java` then add these imports of RNFirebaseMessagingPackage and  RNFirebaseNotificationsPackage.
<h5>import io.invertase.firebase.messaging.RNFirebaseMessagingPackage;<br>
import io.invertase.firebase.notifications.RNFirebaseNotificationsPackage;<br>
</h5>
Add that packages to the list of packages.<br>
<h5>@Override<br>
protected List<ReactPackage> getPackages() {<br>
  @SuppressWarnings("UnnecessaryLocalVariable")<br>
  List<ReactPackage> packages = new PackageList(this).getPackages();<br>
  // Packages that cannot be autolinked yet can be added manually here, for example:<br>
  // packages.add(new MyReactNativePackage());<br>
  packages.add(new RNFirebaseMessagingPackage());<br>
  packages.add(new RNFirebaseNotificationsPackage());<br>
  return packages;<br>
}</h5>
Next, open and edit `android/app/src/main/AndroidManifest.xml` then add these Android permissions after INTERNET permission.
<h5><uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" /><br>
<uses-permission android:name="android.permission.VIBRATE" /></h5><br>
Add the Firebase MESSAGING_EVENT before the closing of <application> tag.<br>
<h5><application ...>
  ...<br>
  <service android:name="io.invertase.firebase.messaging.RNFirebaseMessagingService"><br>
    <intent-filter><br>
      <action android:name="com.google.firebase.MESSAGING_EVENT" /><br>
    </intent-filter><br>
  </service><br>
</application></h5><br>
 
Run again this re-link commands from the terminal.
<h5>react-native unlink react-native-firebase
react-native link react-native-firebase</h5>

<h2>Payload type </h2>
<br><br>
{"to":"c0lGGsrCHGw:APA91bHacOodIzfcDq7TM8_uW6WYLVeneDgB7ReWQ4krvGEH_TcPhD0yvTz4yGcLtwp5TSJr4ZApMNS6GESU8u5vpHdxj_7XV-pGfT8n7Frt3vqyg1jz1hllgvVpBv8Te627qDFBkVLc","notification":{"title":"Working Good","body":"ellll"},"priority":"high"}

<br><br>
{
 "data": {
  "body": "here is body",
  "title": "Title"
 },
"notification": {
  "body": "here is body",
  "title": "Title",
  "click_action": "YOUR_ACTION"
 },
 "to": "c0lGGsrCHGw:APA91bHacOodIzfcDq7TM8_uW6WYLVeneDgB7ReWQ4krvGEH_TcPhD0yvTz4yGcLtwp5TSJr4ZApMNS6GESU8u5vpHdxj_7XV-pGfT8n7Frt3vqyg1jz1hllgvVpBv8Te627qDFBkVLc"
"priority": "high"
}
<br><br>
{
  "data":{
    "title": "hello",
    "body": "this is body"
  },
  "to": "c0lGGsrCHGw:APA91bHacOodIzfcDq7TM8_uW6WYLVeneDgB7ReWQ4krvGEH_TcPhD0yvTz4yGcLtwp5TSJr4ZApMNS6GESU8u5vpHdxj_7XV-pGfT8n7Frt3vqyg1jz1hllgvVpBv8Te627qDFBkVLc"
}
<br><br>



<img src="https://github.com/chaudharybharat/Firebasepush_react_native/blob/master/screen_1.jpg">
<img src="https://github.com/chaudharybharat/Firebasepush_react_native/blob/master/screen_3.jpg">
<img src="https://github.com/chaudharybharat/Firebasepush_react_native/blob/master/screen_2.jpg">
