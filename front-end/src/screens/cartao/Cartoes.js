import { useCallback, useEffect, useState } from 'react';
import { ScrollView, StyleSheet, View,RefreshControl, Text, TouchableOpacity } from 'react-native';
import { useNavigation } from '@react-navigation/native';

import { ButtonPlus } from '../../components/Button';
import { CardCartao } from '../../components/Card';

import { useAuth } from '../../contexts/Auth';
import api from '../../services/api';

import { formatCardNumber, formatValidate } from '../../utils/formatCreditCard';

export default function Cartoes() {
  const {authData} = useAuth();
  const [cartoes, setCartoes] = useState([]);
  const navigation = useNavigation();
  const [refreshing, setRefreshing] = useState(false);

  const onRefresh = useCallback(() => {
    setRefreshing(true);
    setTimeout(() => {
      puxarCartoes();
      setRefreshing(false);
    }, 1000);
  }, []);

  // Retorna os dados que o usuário
  useEffect(() => {
    puxarCartoes();
  }, []);
  

  async function puxarCartoes() {
    await api.get(`/cards/by-user/${authData.id}`)
    .then((response)=> setCartoes(response.data))
    .catch((err)=>console.log(err));
  }

  return (
    <View style={styles.container}>
      <View style={styles.cardTitle}>
        <Text style={{color:'#fff', fontSize:20, fontWeight:'700'}}>CARTÕES</Text>
        <ButtonPlus onPress={() => navigation.navigate('CadastroCartao')}/>
      </View>
      
      <View style={styles.session}>
        <ScrollView 
          vertical={true}
          refreshControl={
            <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
          }
          >
          {cartoes.map((cartao, index) => (
            <TouchableOpacity onPress={() => navigation.navigate('RegistroCartao', {cartao: cartao})}>
              <CardCartao
                  key={index}
                  nomeCartao={cartao.nome}
                  numeroCartao={formatCardNumber(cartao.numero)}
                  validade={cartao.validade} 
              />
            </TouchableOpacity>
          ))}
            
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

