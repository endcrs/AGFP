import { useCallback, useEffect, useState } from 'react';
import { Alert, StyleSheet, Text, View } from 'react-native';
import { useNavigation } from '@react-navigation/native';

import { InputSelect, MaskedInput } from '../../components/InputText';
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
  const [banco, setBanco] = useState('');
  const [bandeira, setBandeira] = useState('');
  const [limite, setlimite] = useState('');
  const [validade, setvalidade] = useState('');
  const [vencimento, setVencimento] = useState('');

  // variáveis de dados dos inputs de select
  const [dataBanco, setDataBanco] = useState([]);
  //const [dataTipoConta, setDataTipoConta] = useState([]);
  const [dataBandeira, setDataBandeira] = useState([]);


  // Retorna os dados dos selects
  useEffect(() => {
    Alert.alert('Atenção!',
      'Verifique e coloque as informações com cuidado, pois não será possivel realizar a alteração futuramente!');

    api.get(`/accounts/by-user/${authData.id}`)
    .then((response)=> setDataBanco(response.data))
    .catch((err)=>console.log(err));

    api.get('/flag-types')
    .then((response) => setDataBandeira(response.data))
    .catch((err) => console.log(err));
  }, []);

  // // Atualiza os bancos sempre que o tipo de conta mudar
  // async function getTipoConta(item)
  // {
  //   await api.get('/bancos?tipo=' + item.codigo)
  //     .then((response) =>
  //     {
  //       setDataBanco(response.data);
  //     })
  //     .catch((err) => console.log(err));
  // }

  async function cadastroCartao()
  {
    if (numeroCartao != ""  && banco != "" && bandeira != "" && limite != "" && validade != "")
    {
      const cartaoSemEspaco = numeroCartao.replace(/\D/g, '');
      const limiteSemFormatacao = limite.replace(/,00$/, "").replace(/\D/g, '');
      //realizando cadastro na API
      await api.post("/cards",
      {
        idConta: banco,
        bandeira: bandeira,
        validade: validade,
        limite: limiteSemFormatacao,
        numero: cartaoSemEspaco,
        vencimento: vencimento
      }
      ).then(function (response) {
        //Informa que o cadastro foi um sucesso e direciona para a pagina dos cartões
        Alert.alert('Sucesso!', 'Seu cartão foi inserido com sucesso!');
        navigation.goBack();
        
      }).catch(function (error){
        //caso o banco retorne um erro, irá aparesentar a mensagem para ajustar no cadastro
        Alert.alert(
            'Cadastro não realizado!');

            console.log(error.toJSON());
      });

    }else{
      Alert.alert('Preencha todos os campos!', 'Tenha atenção ao preencher, ele não poderá ser alterado!');
    }
  }

  return(
    <View style={styles.container}>
      <Text style={styles.title}>NOVO CARTÃO DE CRÉDITO</Text>

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
        data={dataBanco}
        placeholder="Selecione o banco referente ao cartão"
        placeholderTextColor="#727272"
        value={banco}
        labelField="banco"
      	valueField="id"
        onChange={item => {
          setBanco(item.id)
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
				value={limite}
        keyboardType="numeric"
				onChangeText={text => setlimite(text)}
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
        onChangeText={text => setVencimento(text)}
        placeholder="Digite o dia do vencimento da fatura do cartão"
        placeholderTextColor="#727272"
        options={{
          mask: '99'  // Formato de cartão de crédito
        }}
        keyboardType="numeric"  // Apenas números
      />

      <MaskedInput
        type="custom"
        value={validade}
        onChangeText={text => setvalidade(text)}
        placeholder="Digite a data de validade do cartão"
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
