# Google Places API for Android
Android Google Places Client Api for location suggestion. Myanmar font issue handled also like Zawgyi and unicode.
  
 # Developer's Guide
It is important to note that Google Places API for Android is supported by all Android versions. The only prerequisite is that Google Play services component must be installed via the SDK Manager. Please follow the link given in Reference 3 for detailed information. The installed libraries must be added to the project.

The second prerequisite to use Google Places API for Android is that Google API key must be got by registering the app via Google API Console. Getting a key for your app requires several steps. These steps are outlined as follows.

## 1. Get information about your app's certificate.

The API key is based on a short form of your app's digital certificate, known as its SHA-1 fingerprint. To display the SHA-1 fingerprint for your certificate, first ensure that you are using the right certificate. There are two types of certificate; namely, debug certificate and release certificate. If the project is built in debug mode, debug certificate created automatically. On the other hand, release certificate is generated when the project is built in release mode by Android SDK tools. Also, by using keytool program, this certificate can be generated. It is important to note that when the app is released, release certificate must be used. In order to know how to display the certificate and for more information, please check Reference 4.

## 2. Register a project in the Google API Console, add the Google Places API for Android as a service for the project and request a key

Follow these steps to get an API key:

Go to the Google API Console.

Create or select a project.

Click Continue to enable the Google Places API for Android.

On the Credentials page, get an API key. **Note: **If you have an existing API key with Android restrictions, you may use that key. From the dialog displaying the API key, select Restrict key to set an Android restriction on the API key. In the Restrictions section, select Android apps, then enter your app's SHA-1 fingerprint and package name. For example:

BB:0D:AC:74:D3:21:E1:43:67:71:9B:62:91:AF:A1:66:6E:44:5D:75

com.example.android.places-example

Click Save.

Your new Android-restricted API key appears in the list of API keys for your project. An API key is a string of characters, something like this:

AIza*****************1KG0

For more information please check Reference 4.

## 3. Add the key to your app by adding an element to your app manifest.

Add your API key to your app manifest as shown in the following code sample, replacing YOUR_API_KEY with your own API key:

    <application>  
        ...  
       <meta-data  
           android:name="com.google.android.geo.API_KEY"  
            android:value="YOUR_API_KEY"/>  
     </application>`
     
     

<div class="row">
<img src="https://github.com/er-akashgarg/GooglePlacesApi/blob/master/screens/scr1.png" width="195" height="360" />
<img src="https://github.com/er-akashgarg/GooglePlacesApi/blob/master/screens/scr2.png" width="195" height="360" />
<img src="https://github.com/er-akashgarg/GooglePlacesApi/blob/master/screens/scr3.png" width="195" height="360" />
<img src="https://github.com/er-akashgarg/GooglePlacesApi/blob/master/screens/scr4.png" width="195" height="360" />
</div>


# Reference-: 
   * https://developers.google.com/places/android-api/start
