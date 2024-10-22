import { useCallback, useEffect, useState } from 'react';
import { RefreshControl, ScrollView, StyleSheet, Text, View } from 'react-native';
import { useNavigation } from '@react-navigation/native';

import { ButtonPlus } from '../components/Button';
import CardRegistro from '../components/Card';
import { useAuth } from '../contexts/Auth';

import api from '../services/api';

import { convertDateToFormFormat } from '../utils/formatData';


export default function Historico() {    
    const {authData} = useAuth();
    const navigation = useNavigation();
    const [refreshing, setRefreshing] = useState(false);

    const [transacoes, setTransacoes] = useState([]);

    //reloading
    const onRefresh = useCallback(() => {
        setRefreshing(true);
        //atualização dos dados
        setTimeout(() => {

            api.get(`transacoes/listar-todos?cpf=${authData.cpf}`)
            .then((response) => {
            const data = response.data.sort((a, b) => b.id - a.id)
            setTransacoes(data)
            }).catch((err)=>console.log(err));

            setRefreshing(false);
        }, 1000);
    }, []);

  // Retorna os dados que o usuário
    useEffect(() => {
        api.get(`transacoes/listar-todos?cpf=${authData.cpf}`)
        .then((response) => {
        const data = response.data.sort((a, b) => b.id - a.id)
        setTransacoes(data)
        }).catch((err)=>console.log(err));
    }, []);

  return (
    <View style={styles.container}>
      <View style={styles.cardTitle}>
        <Text style={{color:'#fff', fontSize:20, fontWeight:'700'}}>HISTÓRICO</Text>
        <ButtonPlus
            onPress={() => navigation.navigate('CadastroRegistro')}
        />
      </View>

      <View style={styles.session}>

        <ScrollView 
            vertical={true}
            refreshControl={
                <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
            }
        >

            {transacoes.map((transacao, index) => (
                <CardRegistro 
                    key={index}
                    tipoTransacao={transacao.tipoTranscao.codigo}
                    titulo={transacao.titulo}
                    valor={transacao.valor}
                    categoria={transacao.categoria.descricao}
                    data={convertDateToFormFormat(transacao.dataTransacao)}
                /> 
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