import { ScrollView, StyleSheet, Text, View } from 'react-native';
import { ButtonPlus } from '../components/Button';
import CardRegistro from '../components/Card';

export default function Historico() {
  return (
    <View style={styles.container}>
      <View style={styles.cardTitle}>
        <Text style={{color:'#fff', fontSize:20, fontWeight:'700'}}>HISTÓRICO</Text>
        <ButtonPlus/>
      </View>
      <View style={styles.session}>

        <ScrollView vertical={true}>
        <CardRegistro 
              titulo='Compras Virtuais'
              valor='200,00'
              categoria='Educação'
              data='24/04/2024'
            />

            <CardRegistro 
              titulo='Compras Diversas'
              valor='500,00'
              categoria='Educação'
              data='24/04/2024'
            />

            <CardRegistro 
              titulo='Nubank'
              valor='500,00'
              categoria='Educação'
              data='24/04/2024'
            />

            <CardRegistro 
              titulo='Nubank'
              valor='500,00'
              categoria='Educação'
              data='24/04/2024'
            />
             <CardRegistro 
              titulo='Compras Virtuais'
              valor='200,00'
              categoria='Educação'
              data='24/04/2024'
            />

            <CardRegistro 
              titulo='Compras Diversas'
              valor='500,00'
              categoria='Educação'
              data='24/04/2024'
            />

            <CardRegistro 
              titulo='Nubank'
              valor='500,00'
              categoria='Educação'
              data='24/04/2024'
            />

            <CardRegistro 
              titulo='Nubank'
              valor='500,00'
              categoria='Educação'
              data='24/04/2024'
            />
             <CardRegistro 
              titulo='Compras Virtuais'
              valor='200,00'
              categoria='Educação'
              data='24/04/2024'
            />

            <CardRegistro 
              titulo='Compras Diversas'
              valor='500,00'
              categoria='Educação'
              data='24/04/2024'
            />

            <CardRegistro 
              titulo='Nubank'
              valor='500,00'
              categoria='Educação'
              data='24/04/2024'
            />

            <CardRegistro 
              titulo='Nubank'
              valor='500,00'
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
    flex: 1,
    backgroundColor: '#000',
    alignItems: 'center',    
    padding: 10,
  },
  title: {
    fontSize:22,
    color: 'white',
    fontWeight: 'bold',
  },
  cardTitle:{
    width: '95%',
    height: 70,
    justifyContent:'center',
    alignItems:'center',
    flexDirection: 'row',
  },
  session: {
    backgroundColor:'#1f1f1f',
    width: '100%',
    height:'90%',
    borderRadius: 10,
    marginTop: 10,
    marginBottom: 10,
    padding:10,
  },
});