import { ScrollView, StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import { ButtonPlus } from '../components/Button';
import { useNavigation } from '@react-navigation/native';

import { CardCartao } from '../components/Card';

export default function Cartoes() {

  const navigation = useNavigation();

  return (
    <View style={styles.container}>
      <ButtonPlus onPress={() => navigation.navigate('CadastroCartao')}/>
      
      <View style={[styles.session, {height: '90%'}]}>
        <ScrollView vertical={true}>
          <CardCartao 
            nomeCartao={'Eu sou brabo!'}
            numeroCartao={'1234 5678 9012 3456'}
            validade={'17/06'}
            cvv={'123'}
          />
        </ScrollView>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    padding: 10,
    flex: 1,
    backgroundColor: '#000',
    alignItems: 'center',
    justifyContent: 'center',
  },
  title: {
    fontSize:22,
    fontWeight: 'bold',
  },
  session: {
    backgroundColor:'#1f1f1f',
    width: '100%',
    minHeight: 100,
    height:'auto',
    borderRadius: 10,
    marginTop: 10,
    marginBottom: 10,
    padding:10,
  },
});