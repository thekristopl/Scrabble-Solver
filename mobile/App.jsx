import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import CameraView from "./components/CameraView";
import EditBoardView from "./components/EditBoardView";

export default function App() {
  return (
    <View style={styles.container}>
      <CameraView/>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    height: "100%",
    width: "100%",
    backgroundColor: '#000',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
