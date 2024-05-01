import { StyleSheet, Text, View } from 'react-native';
import  Button from '../components/Button';
import { useAuth } from '../contexts/Auth';

export default function Perfil() {
  const {signOut} = useAuth();

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Perfil</Text>
      <Button style={{backgroundColor:'red'}} title='Sair do App' onPress={() => signOut() } />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  title: {
    fontSize:22,
    fontWeight: 'bold',
  }
});