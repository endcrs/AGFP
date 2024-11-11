import { useEffect, useState } from 'react';
import { Alert, StyleSheet, Text, View } from 'react-native';
import { useNavigation } from '@react-navigation/native';

import { InputSelect, InputText, MaskedInput } from '../../components/InputText';
import { Button } from '../../components/Button';

import { useAuth } from '../../contexts/Auth';
import api from '../../services/api';

import { formatCardNumber, formatValidate } from '../../utils/formatCreditCard';


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

  // variáveis de dados dos inputs de select
  const [dataBanco, setDataBanco] = useState([]);
  const [dataTipoConta, setDataTipoConta] = useState([]);
  const [dataBandeira, setDataBandeira] = useState([]);


  // Retorna os dados dos selects
  useEffect(() => {
    api.get('/banks')
    .then((response)=> setDataTipoConta(response.data))
    .catch((err)=>console.log(err));

    api.get('/flag-types')
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
      })
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
      await api.post("/cards",
      {
        idUsuario: authData.id,
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
      <Text style={styles.title}>NOVO CARTÃO DE CRÉDITO</Text>

      <InputText
        onChangeText={setNomeCartao}
        value={nomeCartao}
        placeholder="Digite um apelido para o cartão"
        placeholderTextColor="#727272"
      />

      <MaskedInput
        type="custom"
        value={numeroCartao}
        onChangeText={setNumeroCartao}
        placeholder="Digite o número do cartão"
        placeholderTextColor="#727272"
        options={{
          mask: '9999 9999 9999 9999'  // Formato de cartão de crédito
        }}
        keyboardType="numeric"  // Apenas números
      />

      <InputSelect
        data={dataTipoConta}
        placeholder="Tipo de Conta"
        placeholderTextColor="#727272"
        value={tipoConta}
        labelField="descricao"
      	valueField="codigo"
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
        labelField="descricao"
      	valueField="codigo"
        onChange={item => {
          setBanco(item.codigo)
        }}
      />

      <InputSelect
        value={bandeira}
        data={dataBandeira}
        placeholder="Bandeira"
        placeholderTextColor="#727272"
        labelField="descricao"
      	valueField="codigo"
        onChange={item => {
          setBandeira(item.codigo)
        }}
      />

      <MaskedInput
				type={'money'}
				value={saldo}
        keyboardType="numeric"
				onChangeText={text => setSaldo(text)}
				style={styles.input}
				placeholder="Digite o limite do cartão"
        placeholderTextColor="#727272"
				options={{
					precision: 2,
					separator: ',',
					delimiter: '.',
					unit: 'R$ ',
					suffixUnit: ''
				}}
			/>

      <MaskedInput
        type="custom"
        value={vencimento}
        onChangeText={setVencimento}
        placeholder="Digite a data de vencimento do cartão"
        placeholderTextColor="#727272"
        options={{
          mask: '99/99'  // Formato de cartão de crédito
        }}
        keyboardType="numeric"  // Apenas números
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
