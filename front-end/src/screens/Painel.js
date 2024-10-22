import { useCallback, useEffect, useState } from 'react';
import { Alert, FlatList, RefreshControl, ScrollView, StyleSheet, Text, View } from 'react-native';
import CircularProgress from 'react-native-circular-progress-indicator';
import { useNavigation } from '@react-navigation/native';

import { ButtonPlus } from '../components/Button';
import CardRegistro from '../components/Card';

import { useAuth } from '../contexts/Auth';
import api from '../services/api';

import { convertDateToFormFormat } from '../utils/formatData';
import { formatValue } from '../utils/formatValue';


export default function Painel() {
  const {authData} = useAuth();
  const navigation = useNavigation();

  const [saldo, setSaldo] = useState('');
  const [receita, setReceita] = useState(''); 
  const [despesa, setDespesa] = useState('');
  const [lucro, setLucro] = useState('');

  const [transacoes, setTransacoes] = useState([]);
  const [categoria, setCategoria] = useState([]);
  const [refreshing, setRefreshing] = useState(false);

  //reloading
  const onRefresh = useCallback(() => {
      setRefreshing(true);
      // atualização dos dados
      setTimeout(() => {
        puxarTransacoesMensais();
        puxarCategorias();
        puxarDadosFinanceiro();
        setRefreshing(false);
      }, 1000);
  }, []);

// Retorna os dados que o usuário
  useEffect(() => {
      puxarTransacoesMensais();
      puxarCategorias();
      puxarDadosFinanceiro();
  }, []);

  async function puxarTransacoesMensais(){
      await api.get(`/transacoes/mensais?cpf=${authData.cpf}`)
            .then((response) => {
              const data = response.data.sort((a, b) => b.id - a.id)
              setTransacoes(data)
            }).catch((err)=>console.log(err));
  }

  async function puxarCategorias(){
      await api.get(`/transacoes/listar-percentagem-gasto-categoria?cpf=${authData.cpf}`)
        .then((response) => {
          setCategoria(response.data)
        }).catch((err)=>console.log(err));
  }

  async function puxarDadosFinanceiro(){
      await api.get(`/usuarios/${authData.token}`)
        .then((response) => {
          setSaldo(response.data.saldo);
          setReceita(response.data.receita);
          setDespesa(response.data.despesas);
          setLucro(response.data.lucro);
        }).catch((err)=>console.log(err));
    }
    
  const formatarTextoCategoria = (categoria) => {

    switch (categoria) {
      case "alimentacao":
        return "Alimentação";
      case "belezaEstetica":
        return "Beleza e Estética";
      case "esporteLazer":
        return "Esporte e Lazer";
      case "educacao":
        return "Educação";
      case "saude":
        return "Saúde";
      case "transporte":
        return "Transporte";
      default:
        return categoria;
    }
  };

  const obterCorPorCategoria = (categoria) => {

    switch (categoria) {
      case "alimentacao":
        return "#f33900"; // Vermelho
      case "belezaEstetica":
        return "#9C27B0"; // Roxo 
      case "esporteLazer":
        return "#4CAF50"; // Verde
      case "educacao":
        return "#badc58"; // Amarelo
      case "saude":
        return "#00c6f3"; // Azul
      case "transporte":
        return "#795548"; // Marrom
      default:
        return "#000"; // Cor padrão (preto)
    }
  };

  return (

      <View style={styles.container}>
        {/*Sessão Valores*/}

        <ScrollView
          style={styles.scrollView}
          vertical={true}
          refreshControl={
              <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
          }
        >
          <View style={styles.session}>
            <ScrollView  horizontal={true} >
                <View style={[styles.cardValor, {backgroundColor:'#5100ff'}]} >
                  <Text style={styles.itemValor}>Saldo</Text>
                  <Text style={[styles.itemValor, {fontSize:27}]}>{formatValue(saldo)}</Text>
                </View>

                <View style={[styles.cardValor, {backgroundColor:'#0093D1'}]} >
                  <Text style={styles.itemValor}>Receita</Text>
                  <Text style={[styles.itemValor, {fontSize:27}]}>{formatValue(receita)}</Text>
                </View>
                
                <View style={[styles.cardValor, {backgroundColor:'#FF0000'}]}>
                  <Text style={styles.itemValor}>Despesa</Text>
                  <Text style={[styles.itemValor, {fontSize:27}]}>{formatValue(despesa)}</Text>
                </View>

                <View style={[styles.cardValor, {backgroundColor:'#00d649'}]}>
                  <Text style={styles.itemValor}>Lucro</Text>
                  <Text style={[styles.itemValor, {fontSize:27}]}>{formatValue(lucro)}</Text>
                </View>
            </ScrollView>
          </View>

          {/*Sessão Categorias*/}
          <View style={styles.session}>
            <ScrollView horizontal={true}>
              
                {Object.entries(categoria).map(([categoria, {percentagem}]) => (
                  <View kay={categoria} style={styles.CardCategoria}>
                    <CircularProgress 
                      activeStrokeColor={obterCorPorCategoria(categoria)}
                      value={Math.round(percentagem)}
                      valueSuffix={'%'}
                    />
                    <Text style={styles.textCategoria}>{formatarTextoCategoria(categoria) }</Text>  
                    </View>
                ))}
                
            </ScrollView>
            
          </View>
        </ScrollView>


        {/*Sessão Gastos recentes */}

        <View style={[styles.session, {height: '49%'}]}>
          <View style={styles.titleCard}>
            <Text style={{color:'#fff', fontSize:18, fontWeight:'700'}}>GASTOS RECENTES</Text>
            <ButtonPlus onPress={() => navigation.navigate('CadastroRegistro')}/>
          </View>

          <FlatList
            data={transacoes}
            keyExtractor={(item, index) => index.toString()}
            renderItem={({ item }) => (
                <CardRegistro
                  tipoTransacao={item.tipoTranscao.codigo}
                  titulo={item.titulo}
                  valor={item.valor}
                  categoria={item.categoria.descricao}
                  data={convertDateToFormFormat(item.dataTransacao)}
                /> 
            )}
          />
          
        </View>
      </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#000',
    alignItems:'center',
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
  scrollView: {
    width: '100%',
    minHeight: 100,
    height:'auto',
    marginLeft: 22,
    marginTop: 0,
  },
  titleCard: {
    width: '100%',
    height: 70,
    justifyContent:'center',
    alignItems:'center',
    flexDirection: 'row',
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
    fontSize: 18,
    marginTop: 20,
    fontWeight: 'bold',
    color:'#fff',
  },

});
