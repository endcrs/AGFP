import { useCallback, useEffect, useState } from 'react';
import { ScrollView, StyleSheet, View,RefreshControl, Text, Alert } from 'react-native';

import { ButtonPlus } from "../../components/Button";
import { useAuth } from "../../contexts/Auth";
import { useNavigation } from "@react-navigation/native";
import api from '../../services/api';
import { CardConta } from '../../components/Card';
import AsyncStorage from '@react-native-async-storage/async-storage';



export default function Contas() {
  const {authData} = useAuth();
  const navigation = useNavigation();
  const [refreshing, setRefreshing] = useState(false);

  const [contas, setContas] = useState([]);

  const onRefresh = useCallback(() => {
    setRefreshing(true);
    setTimeout(() => {
      puxarContasPorUsuario();
      setRefreshing(false);
    }, 1000);
  }, []);

  useEffect(() => {
    puxarContasPorUsuario();
  });

  //Buscando contas do usuário logado
  async function puxarContasPorUsuario() {
    await api.get(`/accounts/by-user/${authData.id}`)
    .then((response) => setContas(response.data))
    .catch((err) => console.log(err));
  } 

  return (
    <View style={styles.container}>
      <View style={styles.cardTitle}>
        <Text style={{color:'#fff', fontSize:20, fontWeight:'700'}}>CONTAS</Text>
        <ButtonPlus onPress={() => navigation.navigate('CadastroConta')}/>
      </View>
      
      <View style={styles.session}>
        <ScrollView 
          vertical={true}
          refreshControl={
            <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
          }
          >
           {contas.map((conta, index) => (
            <CardConta   
            key={index}
              banco={conta.banco.descricao}
              saldo={conta.saldo}
             />
           ))

          }
            
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