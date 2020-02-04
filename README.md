# Firebasepush_react_native

 

<h2> Below intall package </h2><br>
<h5>npm install --save react-native-firebase </h5>

<h5>react-native link react-native-firebase </h5>
<br><br>
<h3>Setup React Native Firebase on Android</h3>
Copy the previously downloaded google-services.json to `android/app/` folder.
<h5>cp ~/Downloads/google-services.json android/app/</h5>
Next, open and edit `android/build.gradle` then add this classpath dependency of Google Services.
<h5>dependencies {
    classpath("com.android.tools.build:gradle:3.4.1")
    classpath 'com.google.gms:google-services:4.2.0'
}</h5>

Also, add the Google maven repository at repositories block and make orders like this.
<h5>repositories {
    google()
    maven {
        url 'https://maven.google.com'
    }
    jcenter()
}</h5>
Open and edit `android/app/build.gradle` then add this line to the bottom of the file.

<h5apply plugin: "com.google.gms.google-services"</5>

Add these lines of Firebase implementation to the dependencies.
<h5>
dependencies {
    ....
    implementation "com.google.android.gms:play-services-base:16.1.0"
    implementation "com.google.firebase:firebase-core:17.0.1"
    implementation "com.google.firebase:firebase-messaging:19.0.1"
    implementation 'me.leolin:ShortcutBadger:1.1.21@aar'
    ....
}</h5>

Next, open and edit `android/app/src/main/java/com/djamware/reactnative/MainApplication.java` then add these imports of RNFirebaseMessagingPackage and  RNFirebaseNotificationsPackage.
<h5>import io.invertase.firebase.messaging.RNFirebaseMessagingPackage;
import io.invertase.firebase.notifications.RNFirebaseNotificationsPackage;
</h5>
Add that packages to the list of packages.
<h5>@Override
protected List<ReactPackage> getPackages() {
  @SuppressWarnings("UnnecessaryLocalVariable")
  List<ReactPackage> packages = new PackageList(this).getPackages();
  // Packages that cannot be autolinked yet can be added manually here, for example:
  // packages.add(new MyReactNativePackage());
  packages.add(new RNFirebaseMessagingPackage());
  packages.add(new RNFirebaseNotificationsPackage());
  return packages;
}</h5>
Next, open and edit `android/app/src/main/AndroidManifest.xml` then add these Android permissions after INTERNET permission.
<h5><uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
<uses-permission android:name="android.permission.VIBRATE" /></h5>
Add the Firebase MESSAGING_EVENT before the closing of <application> tag.
<h5><application ...>
  ...
  <service android:name="io.invertase.firebase.messaging.RNFirebaseMessagingService">
    <intent-filter>
      <action android:name="com.google.firebase.MESSAGING_EVENT" />
    </intent-filter>
  </service>
</application></h5>
Run again this re-link commands from the terminal.
<h5>react-native unlink react-native-firebase
react-native link react-native-firebase</h5>


<img src="https://github.com/chaudharybharat/CircleProgressCutom/blob/master/screen_1.png">
<img src="https://github.com/chaudharybharat/CircleProgressCutom/blob/master/screen_3.png">
<img src="https://github.com/chaudharybharat/CircleProgressCutom/blob/master/screen_2.png">
