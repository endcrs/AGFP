import { useCallback, useEffect, useState } from 'react';
import { ScrollView, StyleSheet, Text, TouchableOpacity, View, FlatList, RefreshControl } from 'react-native';
import { ButtonPlus } from '../components/Button';
import { useNavigation } from '@react-navigation/native';
import api from '../services/api';

import { CardCartao } from '../components/Card';
import { useAuth } from '../contexts/Auth';
import { formatCardNumber, formatValidate } from '../utils/formatCreditCard';

export default function Cartoes() {
  const {authData} = useAuth();
  const [cartoes, setCartoes] = useState([]);
  const navigation = useNavigation();
  const [refreshing, setRefreshing] = useState(false);

  const onRefresh = useCallback(() => {
    setRefreshing(true);
    // Simular a atualização dos dados
    setTimeout(() => {
      api.get('/cartoes?cpf=' + authData.cpf)
        .then((response)=> setCartoes(response.data))
        .catch((err)=>console.log(err));
      setRefreshing(false);
    }, 1000);
  }, []);

  // Retorna os dados que o usuário
  useEffect(() => {
        api.get('/cartoes?cpf=' + authData.cpf)
        .then((response)=> setCartoes(response.data))
        .catch((err)=>console.log(err));
  }, []);
  

  return (
    <View style={styles.container}>
      <ButtonPlus onPress={() => navigation.navigate('CadastroCartao')}/>
      
      <View style={[styles.session, {height: '90%'}]}>
        <ScrollView 
          vertical={true}
          refreshControl={
            <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
          }
          >
          {cartoes.map((cartao, index) => (
            <CardCartao 
                key={index}
                nomeCartao={cartao.nome}
                numeroCartao={formatCardNumber(cartao.numero)}
                validade={formatValidate(cartao.vencimento)}
                cvv={cartao.cvv}
            />
          ))}
            
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