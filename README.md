
# react-native-cmd

## Getting started

`$ npm install react-native-cmd --save`

### Mostly automatic installation

`$ react-native link react-native-cmd`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.decentralchat.reactnative.RNReactNativeCmdPackage;` to the imports at the top of the file
  - Add `new RNReactNativeCmdPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-cmd'
  	project(':react-native-cmd').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-cmd/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-cmd')
  	```


## Usage
```javascript
import RNReactNativeCmd from 'react-native-cmd';

// TODO: What to do with the module?
RNReactNativeCmd;
```
  
