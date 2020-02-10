# Firebasepush_react_native

FirebaseMessageService.java

this class paste in android/app/src/main/java/com/(your pachakge name/ here paste also here already file MainApplication.java

android folder androidManifest file add this code


    below there line add  <application> tage above 
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
    <uses-permission android:name="android.permission.VIBRATE" />

   below service code add  <application> inside

 <service
        android:name="com.firebasepush.FirebaseMessageService">
        <intent-filter>
            <action android:name="com.google.firebase.MESSAGING_EVENT" />
        </intent-filter>
      </service>


=>String time add in android/app/src/main/res/values/

 <string name="default_notification_channel_id">com.firebasepush</string>

=>Add audio file in android/app/src/main/res/raw/
 if your director inside not exists raw folder it create and paste audio in raw folder and rename audiofile ring

when you want push that time pass payload below formate same like
<br>
{<br>
  "data":{<br>
    "title": "hello",<br>
    "body": "this is body"<br>
  },<br>
  "to": "c0lGGsrCHGw:APA91bHacOodIzfcDq7TM8_uW6WYLVeneDgB7ReWQ4krvGEH_TcPhD0yvTz4yGcLtwp5TSJr4ZApMNS6GESU8u5vpHdxj_7XV-pGfT8n7Frt3vqyg1jz1hllgvVpBv8Te627qDFBkVLc"<br>
}<br>
