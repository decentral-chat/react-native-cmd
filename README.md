
# react-native-cmd

## Getting started

`$ npm install react-native-cmd --save`

### Mostly automatic installation

`$ react-native link react-native-cmd`
Notes: If you encounter this issue when you do react-native link: Error: Cannot find module 'asap/raw', manually change your node_modules/promise/package.json, search asap, 
Change ~2.0.3 to ~2.0.6, and do npm install again. Then you should be good to go.

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
type Props = {};
export default class App extends Component<Props> {
  constructor() {
    super();
    this.state = {
      textValue: "init state"
    }
    this.onPressExecuteCmd = this.onPressExecuteCmd.bind(this); //bind this
  }
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>{this.state.textValue}</Text>
        <Button onPress={this.onPressExecuteCmd} title='EXECUTECMD'/>
      </View>
    );
  }
  async onPressExecuteCmd() {
    let resultStr = await RNReactNativeCmd.executeCmd('logcat -d');
    console.log("react-native-cmd resultStr length:", resultStr.length, ",type is:", typeof resultStr);
    let substr = resultStr.substring(resultStr.length - 50, resultStr.length);
    this.setState({textValue:substr}); //set the last 50 characters to textValue
  }
}
```
  
## Notes
