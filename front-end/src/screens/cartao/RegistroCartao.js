import { useCallback, useEffect, useState } from 'react';
import { Alert, FlatList, RefreshControl, ScrollView, StyleSheet, Text, View } from 'react-native';
import { useNavigation } from '@react-navigation/native';

import CardRegistro, { CardCartao } from '../../components/Card';
import { formatCardNumber } from '../../utils/formatCreditCard';
import api from '../../services/api';

export default function RegistroCartao({ route }) {

    const [transacoes, setTransacoes] = useState([]);
    const [refreshing, setRefreshing] = useState(false);

    //reloading
    const onRefresh = useCallback(() => {
      setRefreshing(true);
      // atualização dos dados
      setTimeout(() => {
        puxarRegistrosPorCartao();
        setRefreshing(false);
      }, 1000);
    });

    useEffect(() => {
      puxarRegistrosPorCartao();
    }, []);

    //Buscando registros da compra
    async function puxarRegistrosPorCartao() {
      await api.get(`/cards/transactions/credit-card/${route.params.cartao.id}`)
        .then((response) => {
        const data = response.data.sort((a, b) => b.id - a.id);
        setTransacoes(data);
        }).catch((err)=>console.log(err));
    }

    return (
          <View style={styles.container}>
            <ScrollView 
              vertical={true}
              refreshControl={
                <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
              }
              >
              <View style={styles.sessionCard}>
                  <CardCartao
                      key={route.params.cartao.id}
                      nomeCartao={route.params.cartao.nome}
                      numeroCartao={formatCardNumber(route.params.cartao.numero)}
                      validade={route.params.cartao.validade} 
                  />
                </View>
                </ScrollView>
                <View style={styles.sessionRegistros}>
                  <View style={styles.titleCard}>
                      <Text style={{color:'#fff', fontSize:18, fontWeight:'700'}}>Registros</Text>
                  </View>

                  {transacoes.map((transacao, index) => (
                      <CardRegistro 
                          key={index}
                          tipoTransacao={transacao.tipo.codigo}
                          titulo={transacao.titulo}
                          valor={transacao.valor}
                          categoria={transacao.categoria.descricao}
                          data={transacao.dataTransacao}
                          onPress={() => disableTransactionDialog(transacao.id)}
                      /> 
                  ))}
                </View>
          </View>
    )
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
    sessionCard: {
      backgroundColor:'#1f1f1f',
      width: '100%',
      borderRadius: 10,
      marginTop: 10,
      marginBottom: 10,
      padding:10,
    },
    sessionRegistros: {
        backgroundColor:'#1f1f1f',
        width: '100%',
        height:'58%',
        borderRadius: 10,
        marginBottom: 10,
        padding:10,
      },
  });
