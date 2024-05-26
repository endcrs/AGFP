import { useEffect, useState } from 'react';
import { Alert, StyleSheet, Text, View } from 'react-native';
import { InputSelect, InputText } from '../components/InputText';
import { Button } from '../components/Button';

import { useAuth } from '../contexts/Auth';
import api from '../services/api';
import { formatCardNumber, formatValidate } from '../utils/formatCreditCard';
import { useNavigation } from '@react-navigation/native';

export default function CadastroCartao() {
  const navigation = useNavigation();
  const {authData} = useAuth();

  // Variaveis de acesso
  const [nomeCartao, setNomeCartao] = useState('');
  const [numeroCartao, setNumeroCartao] = useState('');

  // Variaveis Selecionadas
  const [tipoConta, setTipoConta] = useState('');
  const [banco, setBanco] = useState('');
  const [bandeira, setBandeira] = useState('');
  const [saldo, setSaldo] = useState('');
  const [vencimento, setVencimento] = useState('');
  const [cvv, setCvv] = useState('');

  // variáveis de dados dos inputs de select
  const [dataBanco, setDataBanco] = useState([]);
  const [dataTipoConta, setDataTipoConta] = useState([]);
  const [dataBandeira, setDataBandeira] = useState([]);


  // Retorna os dados dos selects
  useEffect(() => {
    api.get('/tipos-bancos')
    .then((response)=> setDataTipoConta(response.data))
    .catch((err)=>console.log(err));

    api.get('/tipos-bandeira')
      .then((response) => setDataBandeira(response.data))
      .catch((err) => console.log(err));
  }, []);

  // Atualiza os bancos sempre que o tipo de conta mudar
  async function getTipoConta(item)
  {
      await api.get('/bancos?tipo=' + item.codigo)
        .then((response) =>
          {
            setDataBanco(response.data);
          }
      )
        .catch((err) => console.log(err));
  }

  //adiciona formatação do numero do cartão
  const adicionarFormatacaoCartao = (text) => {
    const cardCardNumber = formatCardNumber(text);
    setNumeroCartao(cardCardNumber);
  }

    //adiciona formatação do numero do cartão
  const adicionarFormatacaoVencimento = (text) => {
    const cardVencimento = formatValidate(text);
    setVencimento(cardVencimento);
  }

  async function cadastroCartao()
  {
    if (nomeCartao != "" && numeroCartao != "" && tipoConta != "" && banco != "" && bandeira != "" && saldo != "" && vencimento != "")
      {
        const cartaoSemEspaco = numeroCartao.replace(/\D/g, '');
        const vencimentoSemBarra = vencimento.replace(/\D/g, '');

        //realizando cadastro na API
        await api.post("/cartoes",
        {
          idUsuario: authData.token,
          banco: banco,
          tipoBanco: tipoConta,
          bandeira: bandeira,
          vencimento: vencimentoSemBarra,
          saldo: saldo,
          nome: nomeCartao,
          numero: cartaoSemEspaco,
          cvv: cvv
        }
    ).then(function (response) {
        //Informa que o cadastro foi um sucesso e direciona para a pagina de login
        Alert.alert('Cadastro realizado com sucesso!', 'Seu cartão foi inserido com sucesso!');
        navigation.goBack();
    }).catch(function (error){
        //caso o banco retorne um erro, irá aparesentar a mensagem para ajustar no cadastro
        Alert.alert(
            'Cadastro não realizado!',
            error.response.headers.mensagem);
    });
      }
  }

  return(
    <View style={styles.container}>
      <Text style={styles.title}>NOVO CARTÃO</Text>

      <InputText
        onChangeText={setNomeCartao}
          value={nomeCartao}
        placeholder="Nome do Cartão"
        placeholderTextColor="#727272"
      />

      <InputText
        onChangeText={adicionarFormatacaoCartao}
          value={numeroCartao}
        placeholder="Número do Cartão"
        placeholderTextColor="#727272"
        maxLength={19}
        keyboardType="numeric"  
      />

      <InputSelect
        data={dataTipoConta}
        placeholder="Tipo de Conta"
        placeholderTextColor="#727272"
        value={tipoConta}
        onChange={item => {
          setTipoConta(item.codigo)
          setBanco('');
          getTipoConta(item)
        }}
      />

      <InputSelect
        data={dataBanco}
        placeholder="Banco"
        placeholderTextColor="#727272"
        value={banco}
        onChange={item => {
          setBanco(item.codigo)
        }}
      />

      <InputSelect
        value={bandeira}
        data={dataBandeira}
        placeholder="Bandeira"
        placeholderTextColor="#727272"
        onChange={item => {
          setBandeira(item.codigo)
        }}
      />

      <InputText
        onChangeText={setSaldo}
        value={saldo}
        placeholder="Saldo"
        keyboardType="numeric"
        placeholderTextColor="#727272"
      />

      <InputText
        onChangeText={adicionarFormatacaoVencimento}
        value={vencimento}
        placeholder="Vencimento"
        placeholderTextColor="#727272"
        keyboardType="numeric"
      />

      <InputText
        onChangeText={setCvv}
        value={cvv}
        placeholder="cvv"
        placeholderTextColor="#727272"
        maxLength={3}
        keyboardType="numeric"
      />

      <Button
        style={{marginTop:20, width:150}}
        title='Salvar'
        onPress={ () => cadastroCartao()}
      />
    </View>
)
}

const styles = StyleSheet.create({
container: {
  flex: 1,
  backgroundColor: '#000',
  justifyContent: 'center',
  alignItems: 'center',
  
},
title: {
  marginBottom: 50,
  fontSize:22,
  color: 'white',
  fontWeight: 'bold',
}
});