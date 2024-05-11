import { ScrollView, StyleSheet, Text, View } from 'react-native';
import { ButtonPlus } from '../components/Button';
import { CardCartao } from '../components/Card';

export default function Cartoes() {
  return (
    <View style={styles.container}>
      <ButtonPlus/>
      
      <View style={[styles.session, {height: '90%'}]}>
        <ScrollView vertical={true}>
          <CardCartao 
            titulo='Compras Virtuais'
            valor='200,00'
            categoria='Educação'
            data='24/04/2024'
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