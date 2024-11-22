import { useCallback, useEffect, useState } from 'react';
import { Alert, FlatList, StyleSheet, Text, View } from 'react-native';
import { useNavigation } from '@react-navigation/native';

import { CardCartao } from '../../components/Card';
import { formatCardNumber } from '../../utils/formatCreditCard';

export default function RegistroCartao({ route }) {

    const [transacoes, setTransacoes] = useState([]);
    // const [refreshing, setRefreshing] = useState(false);

    // //reloading
    //   const onRefresh = useCallback(() => {
    //     setRefreshing(true);
    //     // atualização dos dados
    //     setTimeout(() => {
    //       puxarContasPorUsuario();
    //       setRefreshing(false);
    //     }, 1000);
    // });

    // useEffect(() => {
    //   puxarContasPorUsuario();
    // });

    // //Buscando registros da compra
    // async function puxarContasPorUsuario() {
    //   await api.get(`/cards/transictions/credit-card/${route.params.cartao.id}`)
    //   console.log(response.data)
    //   .then((response) => setTransacoes(response.data))
    //   .catch((err) => console.log(err));
    // }

    return (
        <View style={styles.container}>
            <View style={styles.sessionCard}>
                <CardCartao
                    key={route.params.cartao.id}
                    nomeCartao={route.params.cartao.nome}
                    numeroCartao={formatCardNumber(route.params.cartao.numero)}
                    validade={route.params.cartao.validade} 
                />
              </View>
              <View style={styles.sessionRegistros}>
                <View style={styles.titleCard}>
                    <Text style={{color:'#fff', fontSize:18, fontWeight:'700'}}>Registros</Text>
                </View>

                <FlatList
                    data={transacoes}
                    keyExtractor={(item, index) => index.toString()}
                    renderItem={({ item }) => (
                        <CardRegistro
                        tipoTransacao={item.tipo.codigo}
                        titulo={item.titulo}
                        valor={item.valor}
                        categoria={item.categoria.descricao}
                        data={item.dataTransacao}
                        onPress={() => disableTransactionDialog(item.id)}
                        /> 
                    )}
                />
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
