import { StyleSheet, TouchableOpacity } from "react-native";
import { useNavigation } from "@react-navigation/native";
import { Feather, Entypo, Ionicons } from '@expo/vector-icons';

export function IconBack() {
  
	const navigation = useNavigation();

  return(
    <TouchableOpacity style={styles.iconBack} onPress={() => navigation.goBack()}>
				<Ionicons name='arrow-back' color={'#fff'} size={34} />
			</TouchableOpacity>
  )
}

const styles = StyleSheet.create({
	iconBack:{
		marginLeft: 25,
	},
});