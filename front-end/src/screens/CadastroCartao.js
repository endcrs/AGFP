import { useState } from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { InputSelect, InputText } from '../components/InputText';
import { Button } from '../components/Button';

import api from '../services/api';

const dataTipoConta = [
  { label: 'Física', value: '1' },
  { label: 'Digital', value: '2' },
];

const dataBanco = [
  { label: 'Banco 1', value: '1' },
  { label: 'Banco 2', value: '2' },
];

const dataBandeira = [
  { label: 'Bandeira 1', value: '1' },
  { label: 'Bandeira 2', value: '2' },
];

export default function CadastroCartao() {

  /*Variaveis de acesso */
  const [nomeCartao, setNomeCartao] = useState('');
  const [numeroCartao, setNumeroCartao] = useState('');
  const [tipoConta, setTipoConta] = useState('');
  const [banco, setBanco] = useState('');
  const [bandeira, setBandeira] = useState('');
  const [limite, setLimite] = useState('');
  const [vencimento, setVencimento] = useState('');

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
        onChangeText={setNumeroCartao}
          value={numeroCartao}
        placeholder="Número do Cartão"
        placeholderTextColor="#727272"
      />

      <InputSelect
        onChangeText={setTipoConta}
        value={tipoConta}
        data={dataTipoConta}
        placeholder="Tipo de Conta"
        placeholderTextColor="#727272"
      />

      <InputSelect
        onChangeText={setBanco}
        value={banco}
        data={dataBanco}
        placeholder="Banco"
        placeholderTextColor="#727272"
      />

      <InputSelect
        onChangeText={setBandeira}
        value={bandeira}
        data={dataBandeira}
        placeholder="Bandeira"
        placeholderTextColor="#727272"
      />

      <InputText
        onChangeText={setLimite}
        value={limite}
        placeholder="Limite"
        placeholderTextColor="#727272"
      />

      <InputText
        onChangeText={setVencimento}
        value={vencimento}
        placeholder="Vencimento"
        placeholderTextColor="#727272"
      />

      <Button
        style={{marginTop:20, width:150}}
        title='Salvar'
        onPress={() => navigation.goBack()}
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