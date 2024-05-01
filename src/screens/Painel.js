import { Alert, ScrollView, StyleSheet, Text, TouchableOpacity, View } from 'react-native';

import { ButtonPlus } from '../components/Button';
import { useNavigation } from '@react-navigation/native';
import CardRegistro from '../components/Card';

export default function Painel() {

  const navigation = useNavigation();


  return (
    <View style={styles.container}>
      
      {/*Sessão Valores*/}
      <View style={styles.session}>
        <ScrollView  horizontal={true} >
            <View style={[styles.cardValor, {backgroundColor:'#0093D1'}]} >
              <Text style={styles.itemValor}>Receita</Text>
              <Text style={[styles.itemValor, {fontSize:27}]}>R$ 1.995,00</Text>
            </View>
            
            <View style={[styles.cardValor, {backgroundColor:'#FF0000'}]}>
              <Text style={styles.itemValor}>Despesa</Text>
              <Text style={[styles.itemValor, {fontSize:27}]}>R$ 1.287,00</Text>
            </View>

            <View style={[styles.cardValor, {backgroundColor:'#00d649'}]}>
              <Text style={styles.itemValor}>Lucro</Text>
              <Text style={[styles.itemValor, {fontSize:27}]}>R$ 708,00</Text>
            </View>
        </ScrollView>
      </View>

      {/*Sessão Categorias*/}
      <View style={styles.session}>
        <ScrollView horizontal={true}>
          <View style={styles.CardCategoria}>
            <View style={styles.pregressCategoria}>

            </View>
            <Text style={styles.textCategoria}>Alimentação</Text>
          </View>
          <View style={styles.CardCategoria}>
            <View style={styles.pregressCategoria}>

            </View>
            <Text style={styles.textCategoria}>Educação</Text>
          </View>
          <View style={styles.CardCategoria}>
            <View style={styles.pregressCategoria}>

            </View>
            <Text style={styles.textCategoria}>Lazer</Text>
          </View>
          <View style={styles.CardCategoria}>

          </View>

        </ScrollView>
        
      </View>

      {/*Sessão Gastos recentes */}

      <View style={[styles.session, {height: 355}]}>
        <Text style={{color:'#fff', fontSize:18, fontWeight:'700'}}>GASTOS RECENTES</Text>

        <ScrollView>

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
        </ScrollView>
      </View>

      <ButtonPlus onPress={() => navigation.navigate('CadastroRegistro')}/>

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#000',
    alignItems:'center',
    padding: 5,
  },
  session: {
    backgroundColor:'#1f1f1f',
    width: '95%',
    minHeight: 100,
    height:'auto',
    borderRadius: 10,
    marginTop: 10,
    marginBottom: 10,
    padding:10,
  },
  cardValor: {
    width: 200,
    height: 80,
    marginLeft: 5,
    marginRight: 5,
    backgroundColor:'#000',
    borderRadius: 5,
    paddingRight: 15,
    alignItems:'flex-end'
  },
  itemValor:{
    color: '#fff',
    fontSize: 18,
    fontWeight: '500',
    marginTop: 5,
  },
  CardCategoria:{
    width: 150,
    height: 200,
    margin: 5,
    justifyContent:'center',
    alignItems:'center',
  },
  pregressCategoria:{
    width: 130,
    height: 130,
    borderRadius: 100,
    borderColor: "#00E34D",
    borderWidth: 10,
  },
  textCategoria:{
    fontSize: 20,
    marginTop: 20,
    fontWeight: 'bold',
    color:'#fff',
  },

});